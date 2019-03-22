package com.ruijie.icweixin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


//@ConfigurationProperties(locations = "classpath:config/my-web.properties", prefix = "web")
//@PropertySource("classpath:config/my-web.properties")
@ConfigurationProperties(prefix = "weixin")
@Component
public class WeixinConfig {

    private String appid;
    private String appSecret;
    private String weixinAuth;
    private String myServiceAuth;
    private String weixinAuthDomain;
    
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getWeixinAuth() {
		return weixinAuth;
	}
	public void setWeixinAuth(String weixinAuth) {
		this.weixinAuth = weixinAuth;
	}
	public String getMyServiceAuth() {
		return myServiceAuth;
	}
	public void setMyServiceAuth(String myServiceAuth) {
		this.myServiceAuth = myServiceAuth;
	}
	public String getWeixinAuthDomain() {
		return weixinAuthDomain;
	}
	public void setWeixinAuthDomain(String weixinAuthDomain) {
		this.weixinAuthDomain = weixinAuthDomain;
	}


}
