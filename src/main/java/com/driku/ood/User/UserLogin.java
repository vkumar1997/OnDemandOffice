//Created By Vikas Kumar

package com.driku.ood.User;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class UserLogin {
	
	String email_address;
	String password;
	String name;
	BigDecimal phone;
	String city;
	String logintype;
	Timestamp timestamp;
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPhone() {
		return phone;
	}
	public void setPhone(BigDecimal phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLogintype() {
		return logintype;
	}
	public void setLogintype(String logintype) {
		this.logintype = logintype;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
