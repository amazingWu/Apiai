//package com.robot.example.helper;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//import com.google.gson.JsonObject;
//import com.robot.example.entity.Medicineview;
//import com.robot.example.entity.Product;
//import com.robot.example.service.ProductService;
//
//public class MedicineHelper {
//	@Resource(name="productService")
//	private ProductService productService;
//	
//	public ProductService getProductService() {
//		return productService;
//	}
//	
//	public void setProductService(ProductService productService) {
//		this.productService = productService;
//	}
//	/**
//	 * 获取产品价格
//	 * @param result
//	 * @return
//	 */
//	public String getPricebyName(JsonObject result){
//		JsonObject params=result.getAsJsonObject("parameters");
//		String productName=params.get("medicine-name").getAsString();
//		List<Product> lists=productService.getProductByName(productName);
//		String back="";
//		for(int i=0;i<lists.size();i++){
//			if(i<lists.size()-1){
//				back+=lists.get(i).getProductName()+":"+lists.get(i).getProductPrice()+"元\n";
//			}
//			else{
//				back+=lists.get(i).getProductName()+":"+lists.get(i).getProductPrice()+"元";
//			}
//		}
//		return back;
//	}
//	/**
//	 * 获取产品规格参数
//	 * @param result
//	 * @return 规格参数字符串
//	 */
//	public String getParamsbyName(JsonObject result){
//		JsonObject params=result.getAsJsonObject("parameters");
//		String productName=params.get("medicine-name").getAsString();
//		List<Product> lists=productService.getProductByName(productName);
//		String back="";
//		for(int i=0;i<lists.size();i++){
//			if(i<lists.size()-1){
//				back+=lists.get(i).getProductName()+":\n"+lists.get(i).getProductParameter()+"\n";
//			}
//			else{
//				back+=lists.get(i).getProductName()+":\n"+lists.get(i).getProductParameter();
//			}
//		}
//		return back;
//	}
//	
//	/**
//	 * 获取产品全部基本信息
//	 * @param result
//	 * @return
//	 */
//	public String getProductMessagebyName(JsonObject result){
//		JsonObject params=result.getAsJsonObject("parameters");
//		String productName=params.get("medicine-name").getAsString();
//		List<Product> lists=productService.getProductByName(productName);
//		String back="";
//		for(int i=0;i<lists.size();i++){
//			if(i<lists.size()-1){
//				back+="产品:"+lists.get(i).getProductName()
//					+"\n规格参数:"+lists.get(i).getProductParameter()
//					+"\n供应商:"+lists.get(i).getProductBrand()
//					+"\n价格:"+lists.get(i).getProductPrice()
//					+"\n";
//			}
//			else{
//				back+="产品:"+lists.get(i).getProductName()
//						+"\n规格参数:"+lists.get(i).getProductParameter()
//						+"\n供应商:"+lists.get(i).getProductBrand()
//						+"\n价格:"+lists.get(i).getProductPrice();
//			}
//		}
//		return back;
//	}
//	/**
//	 * 获取药品功效
//	 * @param result
//	 * @return
//	 */
//	public String getProductEffectbyName(JsonObject result){
//		JsonObject params=result.getAsJsonObject("parameters");
//		String productName=params.get("medicine-name").getAsString();
//		//List<String> views=productService.getTypeIdList(productName);
//		List<Medicineview> medicines=productService.getMedicineByName(productName);
//		String back="";
//		for(int i=0;i<medicines.size();i++){
//			if(i<medicines.size()-1){
//				back+=medicines.get(i).getProductName()
//						+":\n功效:"+medicines.get(i).getProductEffect()+"\n";
//			}else{
//				back+=medicines.get(i).getProductName()
//						+":\n功效:"+medicines.get(i).getProductEffect();
//			}
//		}
//		return back;
//	}
//}
