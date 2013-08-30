package com.zhaowei.redis.model;

import java.util.Date;

public class User {
	
	private String userId;
	private String userName;
	private String nickName;
	private String pwd;
	private String email;
	private String phone;
	private String city;
	private int source;
	private String imei;
	private Date createTime;
	private String sid;
	

	public User() {
		super();
	}
	
	public User(String userId, String userName, int source, Date createTime, String sid) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.source = source;
		this.createTime = createTime;
		this.sid = sid;
	}
	public User(String userId, String userName, String nickName, String email, String phone, String city,
			int source, String imei, Date createTime, String sid) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.nickName = nickName;
		this.email = email;
		this.phone = phone;
		this.city = city;
		this.source = source;
		this.imei = imei;
		this.createTime = createTime;
		this.sid = sid;
	}
	// getter and setter
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}

}
