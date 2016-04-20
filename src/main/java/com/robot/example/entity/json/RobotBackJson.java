package com.robot.example.entity.json;

/**
 * 返回给客户端的json对应的类，方便json的构造
 * 
 * @author wuqi-pc
 *
 */
public class RobotBackJson {
	public String status;//表示返回状态，succces表示查找成功，false表示查找失败
	public String type;//返回消息的类型，string表示返回的消息为string，方便返回消息在客户端的组织
	public String content;//返回消息的正文内容
	public String source;//返回消息的来源
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
}
