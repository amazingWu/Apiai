package com.robot.example.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.robot.example.dao.MedicineDao;
import com.robot.example.dao.PriceDao;
import com.robot.example.dao.ProductDao;
import com.robot.example.entity.Medicine_View;
import com.robot.example.entity.Product;

public class ProductService {

	private ProductDao productDao;
	private MedicineDao medicineDao;
	
	
	public MedicineDao getMedicineDao() {
		return medicineDao;
	}

	@Resource(name="medicineDao")
	public void setMedicineDao(MedicineDao medicineDao) {
		this.medicineDao = medicineDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	@Resource(name="productDao")
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}


	private Medicine_View getMedicinebyId(long id){
		return medicineDao.getMedicinebyId(id);
	}
	private List<Medicine_View> getMedicineListbyName(String name){
		return medicineDao.getMedicineByName(name);
	}
	
	/**
	 * 根据id得到产品的最基本信息
	 * @param id
	 * @return
	 */
	public Product getProductById(long id){
		return productDao.getProductById(id);
	}
	
	/**
	 * 根据name返回产品的最基本信息
	 * @param name
	 * @return
	 */
	public  List<Product> getProductByName(String name){
		return productDao.getProductByName(name);
	}
	
	public List<Medicine_View> getMedicineByName(String name){
		return medicineDao.getMedicineByName(name);
	}
	/**
	 * 根据产品名称返回typeid列表
	 * @param name
	 * @return
	 */
	public List<String> getTypeIdList(String name){
		List<Object> list=productDao.getTypeId(name);
		List<String> result=new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			result.set(i, list.get(i).toString());
		}
		return result;
	}
}
