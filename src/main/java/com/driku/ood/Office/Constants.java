

//Created by Vikas Kumar


package com.driku.ood.Office;

public class Constants {
	
	
	
	
	//Change this for image path
	public static String filepath="D:/Srver/apache-tomcat-8.0.37/webapps/ROOT/office_images/"; //Filepath for saving images(/ at last necessary)
	public static String sqlpath="http://localhost:8050/office_images/"; //Domain path for laoding images in HTML (/ at last necessary)
	public static String mkdir="D:/Srver/apache-tomcat-8.0.37/webapps/ROOT/office_images";	//Make directory automatically if non existing
	public static String extension=".png"; //extension of image
	public static String ImageType="png"; //extension of image

	
	
	//Query, Update, Insert, Create
	public static String ImageQuery="SELECT * FROM images where office_id=?"; //for extracting images from images table 
	public static String AllOfficesQuery="SELECT * FROM office;"; //for selection of everything from office table 
	public static String UserOfficeQuery="SELECT * FROM office where mail_id=?"; //for selection of offices according to mail id
	public static String IdOfficeQuery="SELECT * FROM office where OFFICE_ID=?;"; //for selection of offices using office id
	public static String ApprovedOfficeQuery="SELECT * FROM office where approval='Approved';"; //for selection of offices using approved status
	public static String ApprovOfficeQuery="SELECT * FROM office where approval='Approved' || approval='Engaged';"; // for selection of approved offices in the admin panel (both should be shown)
	public static String RejectedOfficeQuery="SELECT * FROM office where approval='Rejected';"; //for selection of offices according to rejected status
	public static String WaitingOfficeQuery="SELECT * FROM office where approval='Waiting';"; //for selection of offices according to waiting status
	public static String ApproveOfficeUpdate="Update office set approval='Approved' where office_id=?;"; //for approving an office
	public static String RejectOfficeUpdate="Update office set approval='Rejected' where office_id=?;"; //for rejecting an office
	public static String EngageOfficeUpdate="Update office set approval='Engaged' where office_id=?;"; //for engaging an office
	public static String DeleteOfficeUpdate="Delete from office where office_id=?;"; //for deleting an office
	public static String RequestHistoryQuery="select office_address,sender_name,owner_name,sender_mail,owner_mail,sender_phone,owner_phone,start_time,end_time,start_date,requests.timestamp,rent_price,status,ref_id from office inner join requests where office.office_id=requests.office_id and office.mail_id=?;"; //for request history 
	public static String BookingHistoryQuery="select office_address,sender_name,owner_name,sender_mail,owner_mail,sender_phone,owner_phone,start_time,end_time,start_date,requests.timestamp,rent_price,status,ref_id from office inner join requests where office.office_id=requests.office_id and requests.sender_mail=?;";//for booking history
	public static String CreateOfficeTable="create table office(office_id int primary key not null auto_increment, name varchar(50) not null, mail_id varchar(50) not null, phone_no decimal(10,0) not null, address varchar(100) not null, office_address varchar(100) not null, city varchar(20) not null, rent_price int not null, estimated_people int not null, opening_time time not null, closing time not null, water_facility boolean not null, washroom_facility boolean not null, ac_facility boolean not null, computer_facility boolean not null, office_maid boolean not null, description varchar(250) not null, pull_requests int not null default 0, timestamp datetime not null, approval varchar(20) not null default 'Waiting');";//creation of office table
	public static String CreateImageTable="create table images(image_id int not null primary key auto_increment,office_id int not null, image_path varchar(150) not null);";//for creating an images table
	public static String InsertOfficeTable="insert into office(NAME,MAIL_ID,PHONE_NO,ADDRESS,office_address,CITY,RENT_PRICE,ESTIMATED_PEOPLE,opening_time,closing,water_facility,washroom_facility,ac_facility,computer_facility,office_maid, description,timestamp) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";//for inserting in an office table
	public static String UpdateOfficeTable="update office set NAME=?, MAIL_ID=?, PHONE_NO=? ,ADDRESS=? ,office_address=?, CITY=?, RENT_PRICE=?,ESTIMATED_PEOPLE=?,opening_time=?,closing=?,water_facility=?,washroom_facility=?,ac_facility=?,computer_facility=?,office_maid=?, description=?,timestamp=? where office_id=?;";//for updating an office
	
	public static String DeleteFromImages="delete from images where office_id=?";//deleting from images
	public static String CreateRequestTable="create table requests (office_id int not null,owner_name varchar(50) not null,sender_name varchar(50) not null,owner_mail varchar(50) not null,sender_mail varchar(50) not null,owner_phone numeric(10,0) not null,sender_phone numeric(10,0) not null,space_requested int not null,office_price int not null,start_time time not null,end_time time not null,start_date date not null,timestamp datetime not null,status varchar(20) not null default 'Active', ref_id varchar(20) not null);";//for requests table
	public static String InsertImageTable="insert into images (office_id,image_path) values (?,?)";//insert into images
	public static String InsertRequestsTable="insert into requests(office_id,owner_name,sender_name,owner_mail,sender_mail,owner_phone,sender_phone,space_requested,office_price,start_time,end_time,start_date,timestamp,ref_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";//insert into requests
	public static String UpdateLikes="update office set pull_requests=pull_requests+1 where office_id=?;";//update office likes
	public static String ChangeRequestStatus="Update requests set status=? where ref_id=?;";//change request status(Active when time counter not started.) But replaced with earnings when time counted is stopped
	
	
	//Flags(Do not change)
	public static String Approved="Approved";
	public static String Rejected="Rejected";
	public static String Deleted="Deleted";
	public static String Success="Success";
	public static String Failure="Failure";
	public static String Engaged="Engaged";
	
	//MaxId
	public static String SelectMaxId="Select MAX(OFFICE_ID) from office;";
	public static String SelectMaxImageId="Select MAX(IMAGE_ID) from images;";
	
	
	//for loading the first html page
	public static String jsp="index";
	public static String redirect="redirect:/resources/login.html";
	
}