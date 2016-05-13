package com.robot.example.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.robot.example.entity.json.ApiSentJson;
import com.robot.example.entity.utility.UriUnility;

/**
 * 前台类，在该类中主要进行了与api.ai网站进行交互的封装
 * @author wuqi-pc
 *
 */
public class ApiHelper {
    //api.ai网站的agent的token值
    private final String API_TOKEN_CLIENT = "69e75845d3934663a1fe05243f21df4c";
    //下面这两个属性与该类无关，只是存储在这里方便没有查到消息之后，调用图灵机器人
    //用来存储用户唯一id
    private String userid;
    //用户发送的原内容
    private String content;

    public ApiHelper(String content,String userid){
    	//存储用户唯一id，方便在该类中调用TulingHelper
    	this.userid=userid;
    	this.content=content;
    }
    /**
     * 返回post请求结果
     * @param postbody
     * @return
     */
    private String GetApiHttp(String postbody)
    {
    	this.userid=userid;
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("Authorization", "Bearer " + API_TOKEN_CLIENT));
        //模拟post请求
        String message = HttpHelper.SendPost(UriUnility.API_URI, postbody, list);
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
		  "id": "72008269-040e-426f-a440-569d164b694d",
		  "timestamp": "2016-04-25T09:33:40.884Z",
		  "result": {
			  "source": "agent",
			  "resolvedQuery": "陈奕迅的歌",
			  "speech": "请问你要查哪首歌曲",
			  "action": "",
			  "parameters": {
			  "singer": "陈奕迅"    
			  }, 
			  "metadata": {
				  "intentId": "03121308-fa56-497c-975f-264bad386ddc",
				  "webhookUsed": "false",      
				  "inputContexts": [],      
				  "outputContexts": [],      
				  "contexts": [        "@singer"      ],
				  "intentName": "查歌1"    
			  },    
			  "score": 1.0  
		  },
		  "status": 
		  {    
			  "code": 200,
			  "errorType": "success"  
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
            	   if(jsonObj.getAsJsonObject("result").get("action").getAsString().equals("")){
            		  // return "尊敬的用户你好，您的问题暂时无法回答!";
            		   //调用图灵机器人
            		   TulingHelper tulingHelper=new TulingHelper(content, userid);
            		   String s=tulingHelper.GetMessage();
            		   //System.out.println("tuling:"+s);
            		   return s;
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
               }catch(Exception ee){//如果捕捉到异常，是jsonObj.getAsJsonObject("result").get("action")出现异常
            	 //调用图灵机器人
        		   TulingHelper tulingHelper=new TulingHelper(content, userid);
        		   String s=tulingHelper.GetMessage();
        		   return s;
               }
            default:
                return status.get("errorDetails").getAsString();
        }
    }

}
