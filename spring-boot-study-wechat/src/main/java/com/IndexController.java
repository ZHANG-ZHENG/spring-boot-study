package com;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.robot.AnswerBean;
import com.robot.QuestionUtil;
import com.ruijie.icweixin.config.WeixinConfig;

@RestController
@RequestMapping(value = "icweixin")
public class IndexController{

    @Autowired
    private WeixinConfig weixinConfig;
	
    @RequestMapping(value = "server")
    public void auth(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	System.out.println("auth");

    	request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();


        // 签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败

        SignatureUtil.TOKEN = weixinConfig.getMyServiceAuth();
        if (!SignatureUtil.checkSignature(signature, timestamp, nonce)) {
        	System.out.println("checkSignature error");
        	return;
        }
        // 随机字符串
        String echostr = request.getParameter("echostr");
        if(echostr != null){
            System.out.println("success");
            out.write(echostr);   
            return;
        }
        
        
        String str = null;
        try {
        	Map<String, String> map = MessgaeUtils.xmlToMap(request);

            String ToUserName = map.get("ToUserName");
            String FromUserName = map.get("FromUserName");
            String CreateTime = map.get("CreateTime");
            String MsgType = map.get("MsgType");
            String Content = map.get("Content");
            String MsgId = map.get("MsgId");
            //这里只处理文本消息
            if (MsgType.equalsIgnoreCase("text")){
                Message message=new Message();
                message.setFromUserName(ToUserName);
                message.setToUserName(FromUserName);
                //message.setContent("您发送的消息是text文本消息:"+Content);
 
                message.setContent(QuestionUtil.getAnswer(Content));
                message.setMsgId(MsgId);
                message.setMsgType("text");
                message.setCreateTime(new Date().getTime());
                str=MessgaeUtils.objectToXml(message);
            }

             System.out.println(str);

        }catch (Exception e){
        	e.printStackTrace();
        }
        out.print(str);
        out.close();
    }
//	@GetMapping("/servicefile")
//	String servicefile(HttpServletRequest request) {
//		System.out.println("servicefile");
//        return "7xCWZCDXEGfRMf6q";
//    }     
}
