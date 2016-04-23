package com.robot.example.dao;

import javax.annotation.Resource;

import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.robot.example.entity.Record;

/**
 * record数据库访问层
 * @author wuqi-pc
 *
 */

public class RecordDao extends HibernateDaoSupport{

	/**
	 * 插入记录
	 * @param record
	 */
	public void insert(Record record){
		this.getHibernateTemplate().save(record);
	}

}
