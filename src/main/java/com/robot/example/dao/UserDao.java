package com.robot.example.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.robot.example.entity.RobotUser;

public class UserDao extends HibernateDaoSupport{
	private HibernateTemplate hibernateTemplate;
	
	public List<RobotUser> GetUser(String userName,String userPwd){
		String [] params={userName,userPwd};
		//使用hql语言，这里的RobotUser是entity层的表名
		String hql="from RobotUser where userName=? and userPwd=?";
		List<RobotUser> lists=(List<RobotUser>) this.getHibernateTemplate().find(hql, params);
		return lists;
	}
	
	/**
	 * 查询用户名是否可用
	 * @return 返回整型
	 */
	public int CheckUserName(final String userName){
		int num=this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				//sql语言
				String sql="select count(*) from RobotUser where userName=:userName";
				Query query=session.createQuery(sql);
				query.setParameter("userName", userName);
				List<RobotUser> lists=query.list();
				if(lists.size()>0){
					return lists.size();
				}
				else{
					return 0;
				}
			}
			
		});
		return num;
	}
	public boolean Save(RobotUser robotUser){
		//关闭读写方式检查，不然会报错，应该是由于spring mvc在bean上的注解的原因，使bean为只读
		this.getHibernateTemplate().setCheckWriteOperations(false);
		try {
			this.getHibernateTemplate().save(robotUser);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
