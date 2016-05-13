package com.robot.example.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.hibernate4.HibernateCallback;
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
	
	public List<Record> list(final String userName,final int start,final int offset){
		List<Record> lists=this.getHibernateTemplate().execute(new HibernateCallback<List<Record>>() {

			@Override
			public List<Record> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql="from Record where userName=:userName order by id desc";
				Query query=session.createQuery(hql);
				query.setFirstResult(start);
				query.setMaxResults(offset);
				query.setParameter("userName", userName);
				return query.list();
			}
			
		});
		return lists;
	}

}
