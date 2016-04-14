package com.robot.example.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.JsonObject;
import com.robot.example.dao.MedicineDao;
import com.robot.example.entity.Medicine;

public class MedicineService {

	private MedicineDao medicineviewDao;



//	private Medicineview getMedicinebyId(long id){
//		return medicineviewDao.getMedicinebyId(id);
//	}
//	private List<Medicineview> getMedicineListbyName(String name){
//		return medicineviewDao.getMedicineByName(name);
//	}
//	
//	/**
//	 * 根据id得到产品的最基本信息
//	 * @param id
//	 * @return
//	 */
//	public Product getProductById(long id){
//		return productDao.getProductById(id);
//	}
//	
//	/**
//	 * 根据name返回产品的最基本信息
//	 * @param name
//	 * @return
//	 */
//	public  List<Product> getProductByName(String name){
//		return productDao.getProductByName(name);
//	}
//	
//	public List<Medicineview> getMedicineByName(String name){
//		return medicineviewDao.getMedicineByName(name);
//	}
//	/**
//	 * 根据产品名称返回typeid列表
//	 * @param name
//	 * @return
//	 */
//	public List<String> getTypeIdList(String name){
//		List<Object> list=productDao.getTypeId(name);
//		List<String> result=new ArrayList<String>();
//		for(int i=0;i<list.size();i++){
//			result.set(i, list.get(i).toString());
//		}
//		return result;
//	}
	@Resource
	public void setMedicineviewDao(MedicineDao medicineviewDao) {
		this.medicineviewDao = medicineviewDao;
	}
	/**
	 * 获取药品价格
	 * @param result
	 * @return
	 */
	public String getPricebyName(JsonObject result){
		JsonObject params=result.getAsJsonObject("parameters");
		String productName=params.get("medicine-name").getAsString();
		List<Medicine> lists=medicineviewDao.getMedicineByName(productName);
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
	public String getParamsbyName(JsonObject result){
		JsonObject params=result.getAsJsonObject("parameters");
		String productName=params.get("medicine-name").getAsString();
		List<Medicine> lists=medicineviewDao.getMedicineByName(productName);
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
	 * 获取产品全部基本信息
	 * @param result
	 * @return
	 */
	public String getMessagebyName(JsonObject result){
		JsonObject params=result.getAsJsonObject("parameters");
		String productName=params.get("medicine-name").getAsString();
		List<Medicine> lists=medicineviewDao.getMedicineByName(productName);
		String back="";
		for(int i=0;i<lists.size();i++){
			if(i<lists.size()-1){
				back+="产品:"+lists.get(i).getProductName()
					+"\n规格参数:"+lists.get(i).getProductParameter()
					+"\n供应商:"+lists.get(i).getProductBrand()
					+"\n价格:"+lists.get(i).getProductPrice()
					+"\n";
			}
			else{
				back+="产品:"+lists.get(i).getProductName()
						+"\n规格参数:"+lists.get(i).getProductParameter()
						+"\n供应商:"+lists.get(i).getProductBrand()
						+"\n价格:"+lists.get(i).getProductPrice();
			}
		}
		return back;
	}
	/**
	 * 获取药品功效
	 * @param result
	 * @return
	 */
	public String getEffectbyName(JsonObject result){
		JsonObject params=result.getAsJsonObject("parameters");
		String productName=params.get("medicine-name").getAsString();
		//List<String> views=productService.getTypeIdList(productName);
		List<Medicine> medicines=medicineviewDao.getMedicineByName(productName);
		String back="";
		for(int i=0;i<medicines.size();i++){
			if(i<medicines.size()-1){
				back+=medicines.get(i).getProductName()
						+":\n功效:"+medicines.get(i).getProductEffect()+"\n";
			}else{
				back+=medicines.get(i).getProductName()
						+":\n功效:"+medicines.get(i).getProductEffect();
			}
		}
		return back;
	}
	/**
	 * 获取药品副作用
	 * @param result
	 * @return
	 */
	public String getSideEffectbyName(JsonObject result){
		JsonObject params=result.getAsJsonObject("parameters");
		String productName=params.get("medicine-name").getAsString();
		//List<String> views=productService.getTypeIdList(productName);
		List<Medicine> medicines=medicineviewDao.getMedicineByName(productName);
		String back="";
		for(int i=0;i<medicines.size();i++){
			if(i<medicines.size()-1){
				back+=medicines.get(i).getProductName()
						+":\n副作用:"+medicines.get(i).getProductSideEffect();
			}else{
				back+=medicines.get(i).getProductName()
						+":\n副作用:"+medicines.get(i).getProductSideEffect();
			}
		}
		return back;
	}
	
	/**
	 * 获取药品用法
	 * @param result
	 * @return
	 */
	public String getDirectionbyName(JsonObject result){
		JsonObject params=result.getAsJsonObject("parameters");
		String productName=params.get("medicine-name").getAsString();
		//List<String> views=productService.getTypeIdList(productName);
		List<Medicine> medicines=medicineviewDao.getMedicineByName(productName);
		String back="";
		for(int i=0;i<medicines.size();i++){
			if(i<medicines.size()-1){
				back+=medicines.get(i).getProductName()
						+":用法\n:"+medicines.get(i).getProductDirection();
			}else{
				back+=medicines.get(i).getProductName()
						+":\n用法:"+medicines.get(i).getProductDirection();
			}
		}
		return back;
	}
}
