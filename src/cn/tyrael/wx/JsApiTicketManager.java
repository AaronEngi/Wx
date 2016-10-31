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
	private static final TokenManager sTokenManager = TokenManager.getInstance();
	private static final JsApiTicketManager sTicketManager = new JsApiTicketManager();
	
		
	private JsApiTicketResponse ticketResponse;
	private long timeRefresh;
	
	public static JsApiTicketManager getInstance(){
		return sTicketManager;
	}
	
	private JsApiTicketManager(){
		requestToken(); 
	}
	
	public void requestToken(){
		LogAdapter.d(TAG,  "requestToken");
		String token = sTokenManager.getToken();
		String url = String.format(WxUrl.JS_TICKET, token);
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
		JsApiTicketResponse t = new Gson().fromJson(s, JsApiTicketResponse.class);
		ticketResponse = t;
		timeRefresh = now + t.expires_in * 1000 - TIME_AHEAD;
	}
	
	public String getTicket(){
		return ticketResponse.ticket;
	}
}
