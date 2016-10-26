//Created by Vikas Kumar

package com.driku.ood.Office;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class Requests {
	
	String office_address;
	String sender_name;
	String owner_name;
	String sender_mail;
	String owner_mail;
	BigDecimal phone;
	BigDecimal owner_phone;
	Time entering_time;
	Time leaving_time;
	int price;
	Date start_date;
	String status;
	String ref_id;
	
	
	String timestamp;
	public String getOffice_address() {
		return office_address;
	}
	public void setOffice_address(String office_address) {
		this.office_address = office_address;
	}
	public String getSender_name() {
		return sender_name;
	}
	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}
	public String getSender_mail() {
		return sender_mail;
	}
	public void setSender_mail(String sender_mail) {
		this.sender_mail = sender_mail;
	}
	public BigDecimal getPhone() {
		return phone;
	}
	public void setPhone(BigDecimal phone) {
		this.phone = phone;
	}
	public Time getEntering_time() {
		return entering_time;
	}
	public void setEntering_time(Time entering_time) {
		this.entering_time = entering_time;
	}
	public Time getLeaving_time() {
		return leaving_time;
	}
	public void setLeaving_time(Time leaving_time) {
		this.leaving_time = leaving_time;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public String getOwner_mail() {
		return owner_mail;
	}
	public void setOwner_mail(String owner_mail) {
		this.owner_mail = owner_mail;
	}
	public BigDecimal getOwner_phone() {
		return owner_phone;
	}
	public void setOwner_phone(BigDecimal owner_phone) {
		this.owner_phone = owner_phone;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRef_id() {
		return ref_id;
	}
	public void setRef_id(String ref_id) {
		this.ref_id = ref_id;
	}
	
	
	
	
	

}
