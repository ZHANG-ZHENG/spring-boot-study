package com.robot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class QuestionUtil {
	public static String getAnswer(String questionString){
		//String question = "福州天气";
		String questionJson = "{"+
	"'reqType':0,"+
    "'perception': {"+
        "'inputText': {"+
            "'text': '"+questionString+"'"+
        "}"+
    "},"+
    "'userInfo': {"+
        "'apiKey': '9352d48da3c849819a25d7fd3802f740',"+
        "'userId': '406311'"+
    "}"+
"}";
		String result =  HttpUtils.submitPostJsonData("http://openapi.tuling123.com/openapi/api/v2", questionJson, "UTF-8");
        AnswerBean answerBean = JSON.parseObject(result, new TypeReference<AnswerBean>() {});
        //System.out.println(answerBean.getResults());
		//System.out.println("result "+answerBean.getResults().get(0).getValues().get("text"));
		//{"emotion":{"robotEmotion":{"a":0,"d":0,"emotionId":0,"p":0},"userEmotion":{"a":0,"d":0,"emotionId":0,"p":0}},"intent":{"actionName":"","code":10004,"intentName":""},"results":[{"groupType":1,"resultType":"text","values":{"text":"什么1，不懂呢"}}]}
		String resultAnswer = answerBean.getResults().get(0).getValues().get("text");
		return resultAnswer;		
	}

}
