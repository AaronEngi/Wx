package cn.tyrael.wx;

public class Test {
	public static void main(String[] args){
//		new WxAdapter().requestToken();
		JsApiTicketManager.getInstance().getTicket();
	}
}
