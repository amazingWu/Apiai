package com.robot.example.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.robot.example.entity.Price;
import com.robot.example.entity.Product;
import com.robot.example.service.PriceService;
import com.robot.example.service.ProductService;
import com.robot.example.service.WeatherService;

@Controller
@RequestMapping(value = "api")
public class ApiController {
	private WeatherService weatherService=new WeatherService();
	private PriceService priceService;
	private ProductService productService;
	
	public ProductService getProductService() {
		return productService;
	}
	@Resource
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public PriceService getPriceService() {
		return priceService;
	}
	
	@Resource(name="priceService")
	public void setPriceService(PriceService priceService) {
		this.priceService = priceService;
	}
	
	/**
	 * 查询天气
	 * @param city城市名称
	 * @return
	 */
	//produces="application/json; charset=utf-8"用来控制输出的编码格式为utf-8
	@RequestMapping(value = "weather/{city}",
			produces="application/json; charset=utf-8")
	public @ResponseBody String weather(@PathVariable String city){
		String result=weatherService.getWeather(city);
		return result;
	}
	/**
	 * ..\api\webhook
	 * @return
	 */
	@RequestMapping(value="webhook",
			method = RequestMethod.POST,
			produces="application/json; charset=utf-8")
	public @ResponseBody String webhook(@RequestBody String body){
		
		//json动态化处理工具
		JsonParser parser=new JsonParser();
		JsonObject jsonObj=parser.parse(body).getAsJsonObject();
		//从json中拿到result结点
		JsonObject result=jsonObj.getAsJsonObject("result");
		String actionType=result.get("action").getAsString();
		//根据result结点下的action类型进行不同的处理
		if(actionType.equals("weatherForecast")){
			return getWeather(result);
		}else if(actionType.equals("productPrice")){
			return getPricebyName(result);
		}else if(actionType.equals("productParameter")){
			return getParamsbyName(result);
		}else if(actionType.equals("productMessage")){
			return getProductMessagebyName(result);
		}
		return null;
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
	
	/**
	 * 获取产品价格
	 * @param result
	 * @return
	 */
	private String getPricebyName(JsonObject result){
		JsonObject params=result.getAsJsonObject("parameters");
		String productName=params.get("product-name").getAsString();
		List<Product> lists=productService.getProductbyName(productName);
		String back="";
		for(int i=0;i<lists.size();i++){
			if(i<lists.size()-1){
				back+=lists.get(i).getProductName()+":"+lists.get(i).getProductPrice()+"元\n";
			}
			else{
				back+=lists.get(i).getProductName()+":"+lists.get(i).getProductPrice()+"元";
			}
		}
		return back;
	}
	
	/**
	 * 获取产品规格参数
	 * @param result
	 * @return 规格参数字符串
	 */
	private String getParamsbyName(JsonObject result){
		JsonObject params=result.getAsJsonObject("parameters");
		String productName=params.get("product-name").getAsString();
		List<Product> lists=productService.getProductbyName(productName);
		String back="";
		for(int i=0;i<lists.size();i++){
			if(i<lists.size()-1){
				back+=lists.get(i).getProductName()+":\n"+lists.get(i).getProductParameter()+"\n";
			}
			else{
				back+=lists.get(i).getProductName()+":\n"+lists.get(i).getProductParameter();
			}
		}
		return back;
	}
	
	/**
	 * 获取产品全部信息
	 * @param result
	 * @return
	 */
	private String getProductMessagebyName(JsonObject result){
		JsonObject params=result.getAsJsonObject("parameters");
		String productName=params.get("product-name").getAsString();
		List<Product> lists=productService.getProductbyName(productName);
		String back="";
		for(int i=0;i<lists.size();i++){
			if(i<lists.size()-1){
				back+="产品:"+lists.get(i).getProductName()
					+"\n类型:"+lists.get(i).getProductType()
					+"\n规格参数:"+lists.get(i).getProductParameter()
					+"\n供应商:"+lists.get(i).getProductBrand()
					+"\n价格:"+lists.get(i).getProductPrice()
					+"\n";
			}
			else{
				back+="产品:"+lists.get(i).getProductName()
						+"\n类型:"+lists.get(i).getProductType()
						+"\n规格参数:"+lists.get(i).getProductParameter()
						+"\n供应商:"+lists.get(i).getProductBrand()
						+"\n价格:"+lists.get(i).getProductPrice();
			}
		}
		return back;
	}
	/**
	 * 测试
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "price/{name}",
			produces="application/json; charset=utf-8")
	public @ResponseBody double price(@PathVariable String name){
		Price result=getPriceService().getPricebyName(name);
		return result.getPrices();
	}
}
