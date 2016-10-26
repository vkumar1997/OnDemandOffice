//Created By Vikas Kumar

package com.driku.ood.User;

public class UserConstants {
	
	public static String UserIdQuery="SELECT * FROM users where mail_id=? and password=?";
	public static String CreateUserTable="create table users(mail_id varchar(40) primary key not null,password varchar(50) not null,name varchar(40) not null,phone numeric(10,0) not null,city varchar(20) not null,login_type varchar(10) not null,timestamp datetime not null);";
	public static String InsertUser="insert into users(MAIL_ID,password,NAME,PHONE,CITY,login_type,timestamp) values(?,?,?,?,?,?,?);";
	public static String IdUserQuery="SELECT * FROM users where mail_id=?";
	public static String ChangePasswordUpdate="Update users set password=? where mail_id=?;";
	
	
	//Flags (Do not Change)
	public static String Created="Created";
	public static String Duplicate="Duplicate";
	public static String Success="Success";
	public static String Failure="Failure";
}
