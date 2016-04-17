package com.robot.example.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.robot.example.entity.json.ApiJson;

public class ApiHelper {
	//接口地址
    private final String API_URL = "https://api.api.ai/v1/query";
    private final String API_TOKEN_CLIENT = "5c0a39075f6a456cada50c335eeca729";
    //要提交的postbody
    private String postbody;

    /**
     * 返回post请求结果
     * @param postbody
     * @return
     */
    private String GetApiHttp(String postbody)
    {
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("Authorization", "Bearer " + API_TOKEN_CLIENT));
        String message = HttpHelper.SendPost(API_URL, postbody, list);
        return message;
    }

    /**
     * 得到Api返回结果，并封装成带有html的形式（以方便显示，前台支持html标签）
     * @param postbody
     * @return
     */
    public String GetApiPost(String postbody)
    {
    	/*{
    		  "id": "b5b184ac-4ac3-486d-b984-20c876aefc3b",
    		  "timestamp": "2016-04-02T05:03:04.795Z",
    		  "result": {
    		    "source": "agent",
    		    "resolvedQuery": "weather in 北京",//被识别出来的你发送的文本
    		    "action": "yahooWeatherForecast",//被匹配的action
    		    "actionIncomplete": false,//相应的action是否顺利完成
    		    "parameters": {
    		      "geo-city": "北京"//文本中被匹配的变量
    		    },
    		    "contexts": [],//上下文
    		    "metadata": {//匹配的Intent
    		      "intentId": "28519659-b0d8-40ef-b22f-818e36f9da65",
    		      "intentName": "Weather"
    		    },
    		    "fulfillment": {//speechResponse，api.ai返回的消息
    		      "speech": "你好"
    		    },
    		    "score": 1
    		  },
    		  "status": {//执行状态码
    		    "code": 206,
    		    "errorType": "partial_content",
    		    "errorDetails": "Webhook call failed. Status code 500. Error:500 INTERNAL SERVER ERROR"
    		  }
    		}*/
    	
        //从api获取消息
        String httptext = GetApiHttp(postbody);
        System.out.println(httptext);
        //序列化成pojo类
        JsonParser parser=new JsonParser();
        JsonObject jsonObj=parser.parse(httptext).getAsJsonObject();
        //获取status节点
        JsonObject status=jsonObj.getAsJsonObject("status");
        int code=status.get("code").getAsInt();
        switch (code)
        {
            case 200:
                //如果匹配到action就向自己的服务器请求
               try{
            	   if (jsonObj.getAsJsonObject("result").get("action").getAsString()==""){
            		   return "尊敬的用户你好，您的问题暂时无法回答";
                   }
                   else
                   {
                       //如果从API.AI返回的消息中有action的值，那么就把从api.ai获取的消息原封不动发送到自己开发的服务器上去查询
                       RobotHelper robotHelper = new RobotHelper();
                       String robotback=robotHelper.getRobotPost(httptext);
                       if (robotback != "")
                       {
                           //如果查询的结果不为空，就返回，该值会被controller层访问
                           return robotback;
                       }
                   }
               }catch(Exception ee){
            	   return "尊敬的用户你好，您的问题暂时无法回答";
               }
            default:
                return status.get("errorDetails").getAsString();
        }
    }
}
