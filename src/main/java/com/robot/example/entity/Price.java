package com.robot.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
//支持json的序列化
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, 
getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Price {

	long id;
	String name;
	double prices;
	
	@Id
	@JsonProperty
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@JsonProperty
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty
	public double getPrices() {
		return prices;
	}
	public void setPrices(double prices) {
		this.prices = prices;
	}
	
}