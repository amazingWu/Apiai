package com.robot.example.helper;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.hibernate.mapping.KeyValue;

public class HttpHelper {
	
	/**
	 * 模拟发�?�get请求
	 * @param url 基地�?,基地�?后面不需要跟 "?".
	 * @param param 参数列表,格式应该为kay1=value1&key2=value2
	 * @return 返回字符�?
	 */
	public static String SendGet(String url,String param){
		
		String urlstring=url+"?"+param;
		System.out.println(urlstring);
		String result="";
		// 创建HttpClient实例     
        HttpClient httpclient = new DefaultHttpClient();
        // 创建Get方法实例     
        HttpGet httpgets=new HttpGet(urlstring);  
        httpgets.setHeader("Content-Type", "text/html;charset=UTF-8");
       //执行GET方法
        try {
			HttpResponse httpresponse = httpclient.execute(httpgets);
			HttpEntity entity=httpresponse.getEntity();
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(),"utf-8"));   
		    String line = "";  
		    while ((line = reader.readLine()) != null) {  
		    	result+=line;
		    }  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			httpgets.abort();
		}
        return result;
	}
	/**
	 * 模拟发�?�post请求
	 * @param url 基地�?.
	 * @param param 参数列表,格式应该为kay1=value1&key2=value2
	 * @return 返回字符�?
	 */
	
	/**
	 * 模拟post请求
	 * @param url 基地址
	 * @param postBody 要发送的字符串信息，可以是json字符串
	 * @param headers 头信息
	 * @return
	 */
	public static String SendPost(String url,String postbody,List<NameValuePair> headers){
		String result="";
		
		DefaultHttpClient httpclient=new DefaultHttpClient();
		HttpPost post=new HttpPost(url);
		post.setHeader("Content-Type", "application/json;charset=utf-8");
		if(headers!=null){
			//添加头信息
			for(int i=0;i<headers.size();i++){
				post.addHeader(headers.get(i).getName(), headers.get(i).getValue());
			}
		}
		
		try{
			StringEntity s=new StringEntity(postbody,"utf-8");
			post.setEntity(s);
			
			//执行Post方法
			HttpResponse httpresponse=httpclient.execute(post);
			//获取返回信息中的内容
			HttpEntity entity=httpresponse.getEntity();
			BufferedReader reader=new BufferedReader(new InputStreamReader(entity.getContent(),"utf-8"));
			String line="";
	         while ((line = reader.readLine()) != null) {
	             result+=line;
	         }
	         reader.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			post.abort();
		}
		return result;
	}

	
}
