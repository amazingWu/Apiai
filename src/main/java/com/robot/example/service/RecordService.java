package com.robot.example.service;

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
	
	public void insert(Record record){
		recordDao.insert(record);
	}

}
