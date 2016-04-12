package com.robot.example.entity;
/**
 * 百度天气服务辅助类
 * @author wq
 *
 */
public class WeatherModel {
	 private String currentCity;
	 private String pm25;
	 private Weather_data[]weather_data;
	public String getCurrentCity() {
		return currentCity;
	}
	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}
	public String getPm25() {
		return pm25;
	}
	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}
	public Weather_data[] getWeather_data() {
		return weather_data;
	}
	public void setWeather_data(Weather_data[] weather_data) {
		this.weather_data = weather_data;
	}
}
