package com.robot.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

//entity表示与数据库中的表或视图对应，hibernate将会根据类名查找数据库
@Entity
@JsonAutoDetect
public class Medicine{
	private long productId;//产品编号
	private String productName;//产品型号
	private double productPrice;//产品价格
	private String productBrand;//产品品牌
	private String productParameter;//产品规格
	private String productType;//产品类型
	private String productEffect;//产品作用
	private String productDirection;//使用说明
	private String productSideEffect;//副作用
	//id表示在数据库中是主键
	@Id
	@GeneratedValue
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
