package com.robot.example.dao;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import com.robot.example.entity.Price;

@Component
public class PriceDao extends HibernateDaoSupport{

	public Price getPricebyId(long id)
	{
		Price price=new Price();
		if(this.getHibernateTemplate()==null)
			System.out.println("meiyou");
		else
			System.out.println("you");
		price=this.getHibernateTemplate().load(Price.class, id);
		return price;
	}
	public Price getPricebyName(final String name)
	{
		Price price;
		price=(Price)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				String hql="from Price where name=:name";
				Query query=session.createQuery(hql);
				query.setParameter("name", name);
				// TODO Auto-generated method stub		
				if(query.list().size()>0)
					return query.list().get(0);
				else
					return null;
			}
		});
		return price;
	}
}
