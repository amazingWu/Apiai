package com.robot.example.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.robot.example.dao.PriceDao;
import com.robot.example.entity.Price;

@Component
public class PriceService {
	
	private PriceDao priceDao;
	
	
	public PriceDao getPriceDao() {
		return priceDao;
	}

	@Resource(name="priceDao")
	public void setPriceDao(PriceDao priceDao) {
		this.priceDao = priceDao;
	}
	public Price getPricebyID(long id){
		return priceDao.getPricebyId(id);
	}
	
	
	public Price getPricebyName(String name){
		return priceDao.getPricebyName(name);
	}
	
}
