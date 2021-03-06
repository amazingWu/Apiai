package com.robot.example.service;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.robot.example.entity.json.RobotBackJson;
import com.robot.example.entity.json.TypeCollection;
import com.robot.example.entity.json.WeatherMessage;
import com.robot.example.entity.json.WeatherModel;
import com.robot.example.entity.json.Weather_data;
import com.robot.example.helper.HttpHelper;

@Component
public class WeatherService {
	//基地�?
	private String weather_url="http://api.map.baidu.com/telematics/v3/weather";
	//参数
	private String param="output=json&ak=6tYzTvGZSOpYB5Oc2YGGOKt8&location=";
	
	public String getWeather(String location){
		String content="";
		//将要放回的json文本对应的pojo类
		RobotBackJson backPojo=new RobotBackJson();
		backPojo.source="百度天气";
		//获取天气
		String result=HttpHelper.SendGet(weather_url, param+location);
		//结果序列化称对象
		Gson gson=new Gson();
		WeatherMessage weather=gson.fromJson(result, WeatherMessage.class);
		//获取天气内容
		if(weather.getStatus().equals("success")){

			//前台采用是支持html标签的，所有用<br/>表示换行，如果不支持html标签，可以在客户端进行该符号的处理
			WeatherModel weatherModel=weather.getResults()[0];
			content =content + weatherModel.getCurrentCity() + "<br/>";  
            Weather_data[] weather_datas = weatherModel.getWeather_data();  
            for (int i =0; i <weather_datas.length; i++)  
            {  
                content =content + weather_datas[i].getDate() + "<br/>";  
                content =content + weather_datas[i].getWeather() + "<br/>";  
                content =content + weather_datas[i].getTemperature() + "<br/>";  
                content =content + weather_datas[i].getWind() + "<br/>";  
            }
			//设置查询到了内容
			backPojo.status="success";
			//把查询结果添加进去
            backPojo.content=content;
		}else{
			//设置查询失败，用来给客户端检验
			backPojo.status="false";
			//设置内容为空
			content="";
		}
		backPojo.type=TypeCollection.Type_Back_String;
		System.out.println(gson.toJson(backPojo));
		return gson.toJson(backPojo);
	}
	
}
