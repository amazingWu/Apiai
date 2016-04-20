package com.robot.example.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.omg.PortableInterceptor.SUCCESSFUL;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.robot.example.dao.MedicineDao;
import com.robot.example.entity.Medicine;
import com.robot.example.entity.json.RobotBackJson;
import com.robot.example.entity.json.TypeCollection;

public class MedicineService {

	private String NEWS_SOURCE="小智药店";
	private MedicineDao medicineDao;



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
	//自动注入属性，该属性已经在spring的配置文件beans.xml中配置
	@Resource(name="medicineDao")
	public void setMedicineviewDao(MedicineDao medicineDao) {
		this.medicineDao = medicineDao;
	}
	
	/**
	 * 数据库中查询medicine
	 * @param result
	 * @return
	 */
	private List<Medicine> medicineSelect(JsonObject result){
		JsonObject params=result.getAsJsonObject("parameters");
		String productName=params.get("medicine-name").getAsString();
		//查找数据库
		List<Medicine> lists=medicineDao.getMedicineByName(productName);
		return lists;
	}
	
	/**
	 * 获取药品价格byName
	 * @param result
	 * @return
	 */
	public String getPricebyName(JsonObject result){
		//初始化返回消息的pojo类
		RobotBackJson backPojo=new RobotBackJson();
		//查找数据库
		List<Medicine> lists=medicineSelect(result);
		String content="";
		if(lists.size()>0){
			backPojo.status="success";
			//消息封装成html形式
			for(int i=0;i<lists.size();i++){
				if(i<lists.size()-1){
					content+=lists.get(i).getProductName()+":"+lists.get(i).getProductPrice()+"元<br/>";
				}
				else{
					content+=lists.get(i).getProductName()+":"+lists.get(i).getProductPrice()+"元";
				}
			}
		}else{
			backPojo.status="false";
		}
		//添加pojo中的信息
		backPojo.content=content;
		backPojo.source=NEWS_SOURCE;
		backPojo.type=TypeCollection.Type_Back_String;
		//序列化成json返回
		Gson gson=new Gson();
		return gson.toJson(backPojo);
		
	}
	/**
	 * 获取产品规格参数
	 * @param result
	 * @return 规格参数字符串
	 */
	public String getParamsbyName(JsonObject result){
		//初始化返回消息的pojo类
		RobotBackJson backPojo=new RobotBackJson();
		//查询数据库
		List<Medicine> lists=medicineSelect(result);
		String back="";
		if(lists.size()>0){
			backPojo.status="success";
			//消息封装成html形式
			for(int i=0;i<lists.size();i++){
				if(i<lists.size()-1){
					back+=lists.get(i).getProductName()+":<br/>"+lists.get(i).getProductParameter()+"br/";
				}
				else{
					back+=lists.get(i).getProductName()+":<br/>"+lists.get(i).getProductParameter();
				}
			}
		}else{
			backPojo.status="false";
		}
		//补全信息
		backPojo.content=back;
		backPojo.source=NEWS_SOURCE;
		backPojo.type=TypeCollection.Type_Back_String;
		//反序列化成json返回
		Gson gson=new Gson();	
		return gson.toJson(backPojo);
	}
	
	/**
	 * 获取产品全部基本信息
	 * @param result
	 * @return
	 */
	public String getMessagebyName(JsonObject result){
		//初始化返回消息的pojo类
		RobotBackJson backPojo=new RobotBackJson();
		//查询数据库
		List<Medicine> lists=medicineSelect(result);
		String back="";
		if(lists.size()>0){
			backPojo.status="success";
			//消息封装成html形式
			for(int i=0;i<lists.size();i++){
				if(i<lists.size()-1){
					back+="产品:"+lists.get(i).getProductName()
						+"<br/>规格参数:"+lists.get(i).getProductParameter()
						+"<br/>供应商:"+lists.get(i).getProductBrand()
						+"<br/>价格:"+lists.get(i).getProductPrice()
						+"<br/>功效:"+lists.get(i).getProductEffect()
						+"<br/>副作用:"+lists.get(i).getProductSideEffect()
						+"<br/>使用说明:"+lists.get(i).getProductDirection()
						+"<br/>";
				}
				else{
					back+="产品:"+lists.get(i).getProductName()
							+"<br/>规格参数:"+lists.get(i).getProductParameter()
							+"<br/>供应商:"+lists.get(i).getProductBrand()
							+"<br/>价格:"+lists.get(i).getProductPrice()
							+"<br/>功效:"+lists.get(i).getProductEffect()
							+"<br/>副作用:"+lists.get(i).getProductSideEffect()
							+"<br/>使用说明:"+lists.get(i).getProductDirection();
				}
			}
		}else{
			backPojo.status="fasle";
		}
		backPojo.type=TypeCollection.Type_Back_String;
		backPojo.source=NEWS_SOURCE;
		backPojo.content=back;
		//反序列化成json返回
		Gson gson=new Gson();	
		return gson.toJson(backPojo);
	}
	/**
	 * 获取药品功效
	 * @param result
	 * @return
	 */
	public String getEffectbyName(JsonObject result){
		//构建pojo类
		RobotBackJson backPojo=new RobotBackJson();
		List<Medicine> medicines=medicineSelect(result);
		String back="";
		if(medicines.size()>0){
			backPojo.status="success";
			for(int i=0;i<medicines.size();i++){
				if(i<medicines.size()-1){
					back+=medicines.get(i).getProductName()
							+":<br/>功效:"+medicines.get(i).getProductEffect()+"<br/>";
				}else{
					back+=medicines.get(i).getProductName()
							+":<br/>功效:"+medicines.get(i).getProductEffect();
				}
			}
		}else{
			backPojo.status="false";
		}
		backPojo.source=NEWS_SOURCE;
		backPojo.content=back;
		backPojo.type=TypeCollection.Type_Back_String;
		//反序列化成json返回
		Gson gson=new Gson();	
		return gson.toJson(backPojo);
	}
	/**
	 * 获取药品副作用
	 * @param result
	 * @return
	 */
	public String getSideEffectbyName(JsonObject result){
		RobotBackJson backPojo=new RobotBackJson();
		List<Medicine> medicines=medicineSelect(result);
		String back="";
		if(medicines.size()>0){
			backPojo.status="success";
			for(int i=0;i<medicines.size();i++){
				if(i<medicines.size()-1){
					back+=medicines.get(i).getProductName()
							+":<br/>副作用:"+medicines.get(i).getProductSideEffect();
				}else{
					back+=medicines.get(i).getProductName()
							+":<br/>副作用:"+medicines.get(i).getProductSideEffect();
				}
			}
		}else{
			backPojo.status="false";
		}
		backPojo.type=TypeCollection.Type_Back_String;
		backPojo.content=back;
		backPojo.source=NEWS_SOURCE;
		//反序列化成json返回
		Gson gson=new Gson();	
		return gson.toJson(backPojo);
	}
	
	/**
	 * 获取药品用法
	 * @param result
	 * @return
	 */
	public String getDirectionbyName(JsonObject result){
		RobotBackJson backPojo=new RobotBackJson();
		List<Medicine> medicines=medicineSelect(result);
		String back="";
		if(medicines.size()>0){
			backPojo.status="success";
			for(int i=0;i<medicines.size();i++){
				if(i<medicines.size()-1){
					back+=medicines.get(i).getProductName()
							+":<br/>用法:"+medicines.get(i).getProductDirection();
				}else{
					back+=medicines.get(i).getProductName()
							+":<br/>用法:"+medicines.get(i).getProductDirection();
				}
			}
		}
		else{
			backPojo.status="false";
		}
		backPojo.source=NEWS_SOURCE;
		backPojo.content=back;
		backPojo.type=TypeCollection.Type_Back_String;
		//反序列化成json返回
		Gson gson=new Gson();	
		return gson.toJson(backPojo);
	}
}
