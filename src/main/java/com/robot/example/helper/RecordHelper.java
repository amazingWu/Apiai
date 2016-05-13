package com.robot.example.helper;

import java.text.SimpleDateFormat;

import com.robot.example.entity.utility.UriUnility;

public class RecordHelper {
	//需要传给服务器的内容如下：
//	{
//	    "content":"药品的价格",//用户查询的内容
//	    "source":"robot"//查询信息来源：用户查询使用的应用名
//	}
	private String content;
	private String source;
	private String userName;
	
	public RecordHelper(String content,String userName){
		//设置信息查询的来源地为robot应用
		this.source="robot";
		this.content=content;
		this.userName=userName;
	}
	//包装post内容的json格式
	private String MakePost(){
		return "{\""+"content\":\""+content+"\",\"source\":\""+source+"\",\"userName\":\""+userName+"\"}";
	}
	public void sendRecord(){
		HttpHelper.SendPost(UriUnility.RECORD_URI, MakePost(), null);
	}

}
