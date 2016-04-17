package com.robot.example.entity.model;

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
