package com.robot.example.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.robot.example.entity.Record;
import com.robot.example.service.MedicineService;
import com.robot.example.service.RecordService;
import com.robot.example.service.WeatherService;

@Controller
@RequestMapping(value = "/api")
public class ApiController {
	private WeatherService weatherService=new WeatherService();
	private MedicineService medicineService;

	
	//属性注入
	@Resource(name="medicineService")
	public void setMedicineService(MedicineService medicineService) {
		this.medicineService = medicineService;
	}
	
	

	/**
	 * 药品查询请求的入口
	 * @param body json字符串
	 * @return
	 */
	//value表示spring框架设置的接口访问地址，consumes表示接收的字符串为json，produces表示返回的字符串采用utf-8编码且为json格式
	@RequestMapping(value="/webhook",
			method = RequestMethod.POST,consumes="application/json",produces = {"application/json;charset=UTF-8"})
	public @ResponseBody String webhook(@RequestBody String body){
		
		//json动态化处理工具
		JsonParser parser=new JsonParser();
		JsonObject jsonObj=parser.parse(body).getAsJsonObject();
		//从json中拿到result结点
		JsonObject result=jsonObj.getAsJsonObject("result");
		String actionType=result.get("action").getAsString();
		//根据result结点下的action类型进行不同的处理
		if(actionType.equals("weatherForecast")){//天气服务
			return getWeather(result);
		}else if(actionType.equals("medicinePrice")){//药品价格
			return medicineService.getPricebyName(result);
		}else if(actionType.equals("medicineParameter")){//药品属性
			return medicineService.getParamsbyName(result);
		}else if(actionType.equals("medicineMessage")){//药品基本完整信息
			return medicineService.getMessagebyName(result);
		}else if(actionType.equals("medicineEffect")){//药品作用
			return medicineService.getEffectbyName(result);
		}else if(actionType.equals("medicineDirection")){//药品的用法
			return medicineService.getDirectionbyName(result);
		}else if(actionType.equals("medicineSideEffect")){//药品的副作用
			return medicineService.getSideEffectbyName(result);
		}
		return "";
	}
	
	
	
	/**
	 * 处理天气
	 * @param result
	 * @return
	 */
	private String getWeather(JsonObject result){
		JsonObject params=result.getAsJsonObject("parameters");
		String geoCity=params.get("geo-city").getAsString();
		return weatherService.getWeather(geoCity);
	}
	
	

}
