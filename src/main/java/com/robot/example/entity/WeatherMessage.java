package com.robot.example.entity;

/**
 * 百度天气服务返回的json对应的类
 * @author wq
 *
 */
public class WeatherMessage {
	private String error;
	private String status;
	private String date;
	private WeatherModel[]results;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public WeatherModel[] getResults() {
		return results;
	}
	public void setResults(WeatherModel[] results) {
		this.results = results;
	}
	
}  
