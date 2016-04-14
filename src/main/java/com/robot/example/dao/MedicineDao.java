package com.robot.example.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import com.robot.example.entity.Medicine;

@Component
public class MedicineDao extends HibernateDaoSupport{
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public Medicine getMedicinebyId(final long id)
	{
		Medicine medicine;
		medicine=(Medicine)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				String hql="from Medicine where productId=:productId";
				Query query=session.createQuery(hql);
				query.setParameter("productId", id);
				// TODO Auto-generated method stub		
				if(query.list().size()>0)
					return query.list().get(0);
				else
					return null;
			}
		});
		return medicine;
	}
	
	/**
	 * 由名字得到药品列表
	 * @param name
	 * @return
	 */
	public List<Medicine> getMedicineByName(final String name){
		@SuppressWarnings({ "unchecked" })
		List<Medicine> result=(List<Medicine>)getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql="from Medicine where productName like:productName";
				Query query=session.createQuery(hql);
				query.setParameter("productName", "%"+name+"%");
				return query.list();
			}
		});
		return result;
	}
}
