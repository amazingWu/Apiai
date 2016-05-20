package com.robot.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.robot.example.auth.AuthPassport;
import com.robot.example.entity.Record;
import com.robot.example.service.RecordService;
/**
 * 用户查询记录的操作的接口
 * @author wuqi-pc
 *
 */
@Controller
@RequestMapping(value="record")
public class RecordController {

	private RecordService recordService;
	@Resource(name="recordService")
	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}
	
	/**
	 * 插入记录接口
	 * @param body json字符串
	 */
	@RequestMapping(value="insert",method = RequestMethod.POST)
	public @ResponseBody int insertReccord(@RequestBody String body){
		//从提交的json字符串中提取数据
		try{
			JsonParser parser=new JsonParser();
			JsonObject json=parser.parse(body).getAsJsonObject();
			String content=json.get("content").getAsString();
			String source=json.get("source").getAsString();
			String userName="";
			try{
				userName=json.get("userName").getAsString();
			}catch(Exception e){
				
			}
			//设置日期的格式
			SimpleDateFormat sdf=new  SimpleDateFormat( "yyyy-MM-dd HH:mm" );
			Date postTime=new Date();
			//获取当前时间
			postTime=sdf.parse(sdf.format(postTime.getTime()));
			//准备插入数据库的映射对象
			Record record=new Record();
			record.setContent(content);
			record.setPostTime(postTime);
			record.setSource(source);
			if(!userName.equals("")){
				record.setUserName(userName);
			}
			recordService.insert(record);
			return 1;//返回1表示成功
		}catch(Exception e){
			return 0;//返回0表示失败
		}
		
	}
	
	@RequestMapping(value="list/{userName}/{start}/{offset}",produces = {"application/json;charset=UTF-8"})
	public @ResponseBody List<Record> RecordList(@PathVariable String userName,@PathVariable int start,@PathVariable int offset){
		//从提交的json字符串中提取数据
		return recordService.list(userName, start, offset);
	}

}
