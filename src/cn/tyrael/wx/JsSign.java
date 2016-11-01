package cn.tyrael.wx;

import cn.tyrael.library.cipher.MDUtil;

public class JsSign {
	private static final String FORMAT = "jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s";
	
	public  final String noncestr;
	public  final long timestamp;
	public  final String ticket;
	public  final String url;
	
	public JsSign(String noncestr, long timestamp, String ticket, String url) {
		super();
		this.noncestr = noncestr;
		this.timestamp = timestamp;
		this.ticket = ticket;
		this.url = url;
	}
	
	//http://www.aspku.com/kaifa/java/195349.html
	//http://mp.weixin.qq.com/wiki/11/74ad127cc054f6b80759c40f77ec03db.html#.E9.99.84.E5.BD.951-JS-SDK.E4.BD.BF.E7.94.A8.E6.9D.83.E9.99.90.E7.AD.BE.E5.90.8D.E7.AE.97.E6.B3.95

	public String sign(){
		String s = String.format(FORMAT, ticket, noncestr, timestamp, url);
		return MDUtil.SHA1(s);
	}
}
