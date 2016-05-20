package com.robot.example.entity.utility;

/**
 * 各服务的接口地址
 * @author wuqi-pc
 *
 */
public class UriUnility {
	/**
	 * API.AI服务的地址
	 */
	public static String API_URI="https://api.api.ai/v1/query";
	/**
	 * 自己的服务的地址
	 */
	//public static String ROBOT_URI="http://localhost:8080/Apiai/api/webhook";
	public static String ROBOT_URI="/api/webhook";
	/**
	 * 查询记录上传的地址
	 */
	//public static String RECORD_URI="http://localhost:8080/Apiai/record/insert";
	public static String RECORD_URI="/record/insert";
	/**
	 * 图灵机器人的地址
	 */
	public static String TULING_URI="http://www.tuling123.com/openapi/api";

}
