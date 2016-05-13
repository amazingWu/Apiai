package com.robot.example.service;

import java.util.List;

import javax.annotation.Resource;

import com.robot.example.dao.RecordDao;
import com.robot.example.entity.Record;

public class RecordService {
	private RecordDao recordDao;

	//属性注入
	@Resource(name="recordDao")
	public void setRecordDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}
	/**
	 * 插入新记录
	 * @param record
	 */
	public void insert(Record record){
		recordDao.insert(record);
	}
	/**
	 * 查询记录
	 * @param userName
	 */
	public List<Record> list(String userName,int start,int offset){
		return recordDao.list(userName, start, offset);
	}

}
