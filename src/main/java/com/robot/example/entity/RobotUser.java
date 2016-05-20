package com.robot.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用户信息表实体
 * @author wuqi-pc
 *
 */
@Entity
@Table(name="Robotuser")
public class RobotUser {

	private long id;
	@NotEmpty(message="用户名不能为空")
	@Length(max=20,message="用户名长度过长")
	private String userName;//用户名
	
	@NotEmpty(message="密码不能为空")
	@Length(max=20,message="密码长度过长")
	private String userPwd;//密码
	
	@Email(message="邮箱格式不正确")
	private String userEmail;//email
	private String userIcon;//头像
	
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	
}
