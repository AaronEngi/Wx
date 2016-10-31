package cn.tyrael.wx;

public class WxUrl {
	public static final String TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	public static final String JS_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
}
