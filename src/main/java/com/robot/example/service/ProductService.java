package com.robot.example.service;

import java.util.List;

import javax.annotation.Resource;

import com.robot.example.dao.PriceDao;
import com.robot.example.dao.ProductDao;
import com.robot.example.entity.Product;

public class ProductService {

	private ProductDao productDao;
	
	
	public ProductDao getProductDao() {
		return productDao;
	}

	@Resource(name="productDao")
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}


	public List<Product> getProductbyName(String name){
		return productDao.getProductbyName(name);
	}
}
