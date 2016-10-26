//Created by Vikas Kumar... Model class for offices. Setters and Getters

package com.driku.ood.Office;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;


public class Customer {
	
	int office_id;
	String name;
	String mail_id;
	BigDecimal phone_no;
	String address;
	String office_address;
	String city;
	int rent_price;
	int estimated_people;
	Time opening_time;
	Time closing_time;
	ArrayList<String> files;
	boolean water_facility;
	boolean washroom_facility;
	boolean ac_facility;
	boolean computer_facility;
	boolean office_maid;
	String description;
	Timestamp timestamp;
	int pull_requests;
	String approval;
	
	
	
	public int getOffice_id()
	{
		return office_id;
	}
	
	public void setOffice_id(int office_id) {
		this.office_id = office_id;
	}
	
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public void setMail_id(String mail_id) {
		this.mail_id = mail_id;
	}
	
	
	
	public void setPhone_no(BigDecimal phone_no) {
		this.phone_no = phone_no;
	}
	
	
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	public void setOffice_address(String office_address) {
		this.office_address = office_address;
	}
	
	
	
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	public void setRent_price(int rent_price) {
		this.rent_price = rent_price;
	}
	
	
	
	public void setEstimated_people(int estimated_people) {
		this.estimated_people = estimated_people;
	}
	
	
	
	public void setOpening_time(Time opening_time) {
		this.opening_time = opening_time;
	}
	
	
	
	public void setClosing_time(Time closing_time) {
		this.closing_time = closing_time;
	}
	


	public void setWater_facility(boolean water_facility) {
		this.water_facility = water_facility;
	}
	
	
	
	public void setWashroom_facility(boolean washroom_facility) {
		this.washroom_facility = washroom_facility;
	}
	
	
	
	public void setAc_facility(boolean ac_facility) {
		this.ac_facility = ac_facility;
	}
	
	
	
	public void setComputer_facility(boolean computer_facility) {
		this.computer_facility = computer_facility;
	}
	
	
	
	public void setOffice_maid(boolean office_maid) {
		this.office_maid = office_maid;
	}
	
	
	
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	public void setPull_requests(int pull_requests) {
		this.pull_requests = pull_requests;
	}
	
	
	public void setApproval(String approval) {
		this.approval = approval;
	}

	public String getName() {
		return name;
	}

	public String getMail_id() {
		return mail_id;
	}

	public BigDecimal getPhone_no() {
		return phone_no;
	}

	public String getAddress() {
		return address;
	}

	public String getOffice_address() {
		return office_address;
	}

	public String getCity() {
		return city;
	}

	public int getRent_price() {
		return rent_price;
	}

	public int getEstimated_people() {
		return estimated_people;
	}

	public Time getOpening_time() {
		return opening_time;
	}

	public Time getClosing_time() {
		return closing_time;
	}

	public boolean isWater_facility() {
		return water_facility;
	}

	public boolean isWashroom_facility() {
		return washroom_facility;
	}

	public boolean isAc_facility() {
		return ac_facility;
	}

	public boolean isComputer_facility() {
		return computer_facility;
	}

	public boolean isOffice_maid() {
		return office_maid;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public int getPull_requests() {
		return pull_requests;
	}

	public String getApproval() {
		return approval;
	}

	public ArrayList<String> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<String> files) {
		this.files = files;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
