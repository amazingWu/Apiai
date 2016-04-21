package com.robot.example.entity.model;


/**
 * 前台辅助类，聊天界面的前台和后台交互用使用的类，用来方便controller层返回消息到view层
 * @author wuqi-pc
 *
 */
public class ChatViewModel {

	 private String role;//信息的角色属性
     private String content;//信息的内容
     private String time;//发送时间
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
     
}
