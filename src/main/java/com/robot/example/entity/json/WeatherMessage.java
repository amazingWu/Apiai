package com.robot.example.entity.json;

/**
 * 百度天气查询返回的json串对应的pojo类，用来序列化json成对象
 * @author wuqi-pc
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
	
	/**
	 * 内部类
	 * @author wuqi-pc
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
	/**
	 * 内部类
	 * @author wuqi-pc
	 *
	 */
	public class Weather_data {
		private String date;
		private String dayPictureUrl;
		private String nightPictureUrl;
		private String weather;
		private String wind;
		private String temperature;
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getDayPictureUrl() {
			return dayPictureUrl;
		}
		public void setDayPictureUrl(String dayPictureUrl) {
			this.dayPictureUrl = dayPictureUrl;
		}
		public String getNightPictureUrl() {
			return nightPictureUrl;
		}
		public void setNightPictureUrl(String nightPictureUrl) {
			this.nightPictureUrl = nightPictureUrl;
		}
		public String getWeather() {
			return weather;
		}
		public void setWeather(String weather) {
			this.weather = weather;
		}
		public String getWind() {
			return wind;
		}
		public void setWind(String wind) {
			this.wind = wind;
		}
		public String getTemperature() {
			return temperature;
		}
		public void setTemperature(String temperature) {
			this.temperature = temperature;
		}
	}
}  
