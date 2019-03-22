package com;

import java.text.MessageFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.robot.HttpUtils;
import com.ruijie.icweixin.config.WeixinConfig;

@RestController
@RequestMapping(value = "icweixin")
public class ConfigController{

    @Autowired
    private WeixinConfig weixinConfig;
	
    @RequestMapping(value = "config/setmenu")
    public String setmenu(HttpServletRequest request,HttpServletResponse response){
//    	https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxb2c8767dd7ed63a8&secret=55788866f78c132c99d447f257ef9b7d
//    	{"access_token":"19_CwhzSdhzef8D8lWs3LNm1F5SNl5AAUV4gRKmLOPBd3Z6qArdQt9eZK47LkPgx_EaXvfzdsv4nwMnwcFOtRCtB8OgY0n-mvNftXUdpoR-QSoOoVShY_8JTM--ERKh6cvsunvmesFYFBWO4WlxSGZaAHAJXQ","expires_in":7200}
		String path = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
		String url = MessageFormat.format(path, weixinConfig.getAppid(),weixinConfig.getAppSecret());
		System.out.println(url);
		String result = HttpUtils.sendGet(url);
		//{"access_token":"19_7os3mowTUlVE9bf0ibiuQu7rkXA9cqoUhoV16M8QCCHeeXHTnGzHrQ-awsnIlS0K3Jg94deUJ49_ZyO5uui3XkgLU6rzDTWLQyklYX76pIYEypBecEBs7CrafBNXOVyfpF0_ieCQfsPvhgbHHIBiAEANOH","expires_in":7200}
		JSONObject jsonObject = JSON.parseObject(result);
		System.out.println("access_token:" + jsonObject.getString("access_token"));
    	return "setmenu";
    		
    }
     
}
