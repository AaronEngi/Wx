package cn.tyrael.data.wx.net;

import cn.tyrael.data.wx.BaseResponse;

public class JsConfig extends BaseResponse{
	public String appId;
	public long timestamp;
	public String nonceStr;
	public String signature;
}
