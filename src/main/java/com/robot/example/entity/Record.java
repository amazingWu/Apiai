package com.robot.example.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

/**
 * 记录消息的数据库中的表的映射类
 * @author wuqi-pc
 *
 */
@Entity
public class Record {
	private long id;
	private String content;
	private Date postTime;
	private String source;
	//注解建议都表在getter上，如果直接标在属性上，会破坏数据的封装性
	
	//Id表示为主键，GeneratedValue表示主键生成策略为数据库自己自增
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	

}
