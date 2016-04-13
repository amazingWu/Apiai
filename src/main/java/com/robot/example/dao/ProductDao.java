package com.robot.example.dao;

import java.sql.SQLException;
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
	 * 根据产品id查找typeId
	 * @return 返回值为-1时表示没查找到
	 */
	public long getTypeId(final long id){
		@SuppressWarnings("unchecked")
		List<Object> result=(List<Object>)getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql="select typeId from product where productId=:productId";
				Query query=session.createQuery(hql);
				query.setParameter("productId", id);
				query.setMaxResults(1);
				return query.list();
			}
		});
		if(result.size()>0){
			return Long.valueOf(result.get(0).toString());
		}else
			return -1;
	};
	
	/**
	 * 根据产品name查找typeId列表,之所以返回列表,是因为name不是唯一的
	 * @return 返回的是一个Object的列表,使用时需要把数据转换为long
	 */
	public List<Object> getTypeId(final String name){
		@SuppressWarnings("unchecked")
		List<Object> result=(List<Object>)getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql="select typeId from product where productName like:productName";
				Query query=session.createQuery(hql);
				query.setParameter("productName", "%"+name+'%');
				query.setMaxResults(1);
				return query.list();
			}
		});
		return result;
	};
	
	public Product getProductById(final long id){
		return getHibernateTemplate().load(Product.class, id);
	}
	
	/**
	 * 由产品型号得到产品列表
	 * @param name
	 * @return
	 */
	public List<Product> getProductByName(final String name){
		@SuppressWarnings("unchecked")
		List<Product> result=(List<Product>)getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql="from com.robot.example.entity.Product where productName=:productName";
				Query query=session.createQuery(hql);
				query.setParameter("productName", name);
				return query.list();
			}
		});
		return result;
	}
}
