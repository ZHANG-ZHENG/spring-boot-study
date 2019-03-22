package com;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.robot.HttpUtils;
import com.ruijie.icweixin.config.WeixinConfig;


@Controller
@RequestMapping(value = "icweixin")
public class HtmlController {  
   
    @Autowired
    private WeixinConfig weixinConfig;
    @Autowired
    private JdbcTemplate jdbcTemplate;
	
	@GetMapping("auth/main")
	ModelAndView test(HttpServletRequest request) throws UnsupportedEncodingException {

		String redirectUri="http://"+weixinConfig.getWeixinAuthDomain()+"/icweixin/auth/index";
		System.out.println("redirectUri "+redirectUri);
		String redirecturiEncode = URLEncoder.encode(redirectUri, "UTF-8");
		String oauthURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope={2}&state=123#wechat_redirect";
		String url = MessageFormat.format(oauthURL, weixinConfig.getAppid(), redirecturiEncode, "snsapi_userinfo");//snsapi_base snsapi_login snsapi_userinfo
		System.out.println("跳转到微信 "+url);
		return new ModelAndView("redirect:"+url);

	}  
	//String pathAT = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
	@GetMapping("auth/index")
	String index(HttpServletRequest request,String code) {
		try{
			System.out.println("index ");		
			
			String authAccesstokenURL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";
			String url = MessageFormat.format(authAccesstokenURL, weixinConfig.getAppid(), weixinConfig.getAppSecret(), code);
			String result = HttpUtils.sendGet(url);
			JSONObject jsonObject = JSON.parseObject(result);
			String openid = jsonObject.getString("openid");
			request.setAttribute("key", result);
			HttpSession session = request.getSession();
			session.setAttribute("openid", openid); 
			System.out.println(session.getAttribute("openid").toString());

		
			return "index";			
		}catch(Exception e){
			
		}
		return "index";	
	}
	
	@GetMapping("auth/main2")
	ModelAndView test2(HttpServletRequest request) throws UnsupportedEncodingException {

		String redirectUri="http://"+weixinConfig.getWeixinAuthDomain()+"/icweixin/auth/index2";
		System.out.println("redirectUri "+redirectUri);
		String redirecturiEncode = URLEncoder.encode(redirectUri, "UTF-8");
		String oauthURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope={2}&state=123#wechat_redirect";
		String url = MessageFormat.format(oauthURL, weixinConfig.getAppid(), redirecturiEncode, "snsapi_userinfo");//snsapi_base snsapi_login snsapi_userinfo
		System.out.println("跳转到微信 "+url);
		return new ModelAndView("redirect:"+url);

	}  
	@GetMapping("auth/index2")
	String index2(HttpServletRequest request,String code) {
		try{
			HttpSession session = request.getSession();
			//oW9FF5401yRdtKHSg96fnka8CVCs
			System.out.println("index 2");
			String pathAT = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";
			String urlAT = MessageFormat.format(pathAT, weixinConfig.getAppid(),weixinConfig.getAppSecret(),code);
			String resultAT = HttpUtils.sendGet(urlAT);
			System.out.println("url "+urlAT);
			JSONObject jsonObjectAT = JSON.parseObject(resultAT);
			String accessToken = jsonObjectAT.getString("access_token");
			System.out.println("access_token:" + accessToken);
			
			//String openid = "oW9FF5401yRdtKHSg96fnka8CVCs";
			String openid = session.getAttribute("openid").toString();
			//String uidPath = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={0}&openid={1}&lang=zh_CN";
			String uidPath = "https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}&lang=zh_CN";
			String uidUrl = MessageFormat.format(uidPath, accessToken, openid);
			String resultUid = HttpUtils.sendGet(uidUrl);
			System.out.println("resultUid "+resultUid);
			request.setAttribute("key", resultUid);
			return "index2";			
		}catch(Exception e){
			System.out.println(e);
		}
		return "index2";	
	}
	
	@GetMapping("auth/testdb")
	String testdb(HttpServletRequest request,String code) {
        String sql = "select * from student";
        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
        StringBuffer resultStr = new StringBuffer();
        for (Map<String, Object> map : list) {
            Set<Entry<String, Object>> entries = map.entrySet( );
                if(entries != null) {
                    Iterator<Entry<String, Object>> iterator = entries.iterator( );
                    while(iterator.hasNext( )) {
                    Entry<String, Object> entry =(Entry<String, Object>) iterator.next( );
                    Object key = entry.getKey( );
                    Object value = entry.getValue();
                    System.out.println(key+":"+value);
                    resultStr.append(value+" ");
                }
            }
            resultStr.append(",   ");
        }	
        request.setAttribute("key", resultStr);
		return "testdb";	
	}
	
	
    public static String urlEnodeUTF8(String str){
        String result = str;
        try {
            result = URLEncoder.encode(str,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
   
}
