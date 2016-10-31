package cn.tyrael.wx;

import java.io.IOException;

import com.google.gson.Gson;

import cn.tyrael.data.wx.TokenResponse;
import cn.tyrael.library.http.HttpDefault;
import cn.tyrael.library.log.LogAdapter;
import okhttp3.Response;

//TODO 启动一个定时任务区刷新token
public class TokenManager {
	private static final String TAG = "TokenManger";
	private static final long TIME_AHEAD = 10 * 60 * 1000;
	private static final TokenManager token = new TokenManager();
		
	private TokenResponse tokenResponse;
	private long timeRefresh;
	private int status;
	
	public static TokenManager getInstance(){
		return token;
	}
	
	private TokenManager(){
		requestToken();
	}
	
	public void requestToken(){
		LogAdapter.d(TAG, "requestToken");
		String url = String.format(WxUrl.TOKEN, WxConstant.APP_ID, WxConstant.APP_SECRET);
		long now = System.currentTimeMillis();
		Response r = HttpDefault.get(url);
		String s = null;
		try {
			s = r.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LogAdapter.d(TAG, s);
		TokenResponse t = new Gson().fromJson(s, TokenResponse.class);
		tokenResponse = t;
		timeRefresh = now + t.expires_in * 1000 - TIME_AHEAD;
	}
	
	public String getToken(){
		LogAdapter.d(TAG,  "getToken");
		return tokenResponse.access_token;
	}
}
