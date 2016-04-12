package com.robot.example.service;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.robot.example.entity.WeatherMessage;
import com.robot.example.entity.WeatherModel;
import com.robot.example.entity.Weather_data;
import com.robot.example.helper.httphelper;

@Component
public class WeatherService {
	//基地�?
	private String weather_url="http://api.map.baidu.com/telematics/v3/weather";
	//参数
	private String param="output=json&ak=6tYzTvGZSOpYB5Oc2YGGOKt8&location=";
	
	public String getWeather(String location){
		String content="";
		System.out.println(location);
		String result=httphelper.sendGet(weather_url, param+location);
		System.out.println("url:"+result);
		Gson gson=new Gson();
		WeatherMessage weather=gson.fromJson(result, WeatherMessage.class);
		if(weather.getStatus().equals("success")){
			WeatherModel weatherModel=weather.getResults()[0];
			content =content + weatherModel.getCurrentCity() + "\n";  
            Weather_data[] weather_datas = weatherModel.getWeather_data();  
            for (int i =0; i <=weather_datas.length - 1; i++)  
            {  
                content =content + weather_datas[i].getDate() + "\n";  
                content =content + weather_datas[i].getWeather() + "\n";  
                content =content + weather_datas[i].getTemperature() + "\n";  
                content =content + weather_datas[i].getWind() + "\n";  
            }
		}else{
			content="sorry";
		}
		return content;
	}
	
}
