package cn.tyrael.wx;

import java.io.IOException;

import com.google.gson.Gson;

import cn.tyrael.data.wx.JsApiTicketResponse;
import cn.tyrael.data.wx.TokenResponse;
import cn.tyrael.library.http.HttpDefault;
import cn.tyrael.library.log.LogAdapter;
import okhttp3.Response;

public class JsApiTicketManager {
	private static final String TAG = "JsApiTicketManager";
	private static final long TIME_AHEAD = 10 * 60 * 1000;
		
	private JsApiTicketResponse tokenResponse;
	private long timeRefresh;
	
	public void requestToken(){
		String token = null;
		String url = String.format(WxUrl.JS_TICKET, WxConstant.APP_ID);
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
//		tokenResponse = t;
//		timeRefresh = now + t.expires_in * 1000 - TIME_AHEAD;
	}
	
//	public String getToken(){
//		return tokenResponse.access_token;
//	}
}
