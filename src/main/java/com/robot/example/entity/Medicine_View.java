package com.robot.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, 
getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Medicine_View{
	private long productId;//产品编号
	private String productName;//产品型号
	private double productPrice;//产品价格
	private String productBrand;//产品品牌
	private String productParameter;//产品规格
	
	@Id
	@JsonProperty
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	
	@JsonProperty
	@NotNull
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@JsonProperty
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	@JsonProperty
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	@JsonProperty
	public String getProductParameter() {
		return productParameter;
	}
	public void setProductParameter(String productParameter) {
		this.productParameter = productParameter;
	}
	private String productType;
	private String productEffect;
	private String productDirection;
	private String productSideEffect;
	@JsonProperty
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	@JsonProperty
	public String getProductEffect() {
		return productEffect;
	}
	public void setProductEffect(String productEffect) {
		this.productEffect = productEffect;
	}
	@JsonProperty
	public String getProductDirection() {
		return productDirection;
	}
	public void setProductDirection(String productDirection) {
		this.productDirection = productDirection;
	}
	@JsonProperty
	public String getProductSideEffect() {
		return productSideEffect;
	}
	public void setProductSideEffect(String productSideEffect) {
		this.productSideEffect = productSideEffect;
	}
	
	
}
