package com.robot.example.service;

import java.util.List;

import javax.annotation.Resource;

import com.robot.example.dao.UserDao;
import com.robot.example.entity.RobotUser;

public class UserService{
	private UserDao userDao;

	@Resource(name="userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * 查询用户信息
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public RobotUser GetUser(String userName,String userPwd){
		List<RobotUser> users=userDao.GetUser(userName, userPwd);
		if(users.size()>0){
			return users.get(0);
		}
		else{
			return null;
		}
	}
	
	/**
	 * 查询用户名称是否可用
	 * @param userName
	 * @return
	 */
	public boolean CheckUserName(String userName){
		int num=userDao.CheckUserName(userName);
		if(num>0){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean Save(RobotUser robotUser){
		return userDao.Save(robotUser);
	}

}
