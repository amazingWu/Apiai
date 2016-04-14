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
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.hibernate.mapping.KeyValue;

public class httphelper {
	
	/**
	 * 模拟发�?�get请求
	 * @param url 基地�?,基地�?后面不需要跟 "?".
	 * @param param 参数列表,格式应该为kay1=value1&key2=value2
	 * @return 返回字符�?
	 */
	public static String sendGet(String url,String param){
		
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
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));   
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
	/*
	public static String sendPost(String url,HashMap<String, String> hash){
		
		String result="";
		// 创建HttpClient实例     
        HttpClient httpclient = new HttpClient();
        // 创建POST方法实例     
        PostMethod postmethod=new PostMethod(url);
        Set set=hash.keySet();
        for(Iterator iter=set.iterator();iter.hasNext();){
        	String key=(String)iter.next();
        	postmethod.setParameter(key,hash.get(key));
        }
        
       //执行POST方法
        try {
			int statusCode = httpclient.executeMethod(postmethod);
			if(statusCode!=HttpStatus.SC_OK){
				System.out.println("get请求出错");
			}
			result=postmethod.getResponseBodyAsString();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			postmethod.releaseConnection();
		}
        return result;
	}*/
	
}
