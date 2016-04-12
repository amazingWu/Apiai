package com.robot.example.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import com.robot.example.entity.Product;

@Component
public class ProductDao extends HibernateDaoSupport{

	
	/**
	 * 根据名字获取产品列表
	 * @param name ��Ʒ����
	 * @return 产品列表
	 */
	public List<Product> getProductbyName(final String name){
		
		
		@SuppressWarnings("unchecked")
		List<Product> products=(List<Product>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public List<Object> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql="from Product where productName=:productName";
				Query query=session.createQuery(hql);
				query.setParameter("productName", name);
				return query.list();
			}
		});
		return products;
	}
	
}
