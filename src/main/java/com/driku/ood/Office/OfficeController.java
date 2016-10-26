//Office Controller class

//Created by Vikas Kumar and Pavas Navaney

package com.driku.ood.Office;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.driku.ood.Office.OfficeService;

@Controller
public class OfficeController {

	@Autowired
	OfficeService os;
	@Autowired
	JavaMailSender mailSender;
	
	String reference_id;
	
	
	//For loading the jsp page
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String gethello() {
		return Constants.jsp;
		
	}
	
	//for loading the static page
	
	@RequestMapping(value = "/staticPage", method = RequestMethod.GET)
	   public String redirect() {
	      return Constants.redirect;
	   }
	
	//for getting all offices
	
	@RequestMapping(value="offices/all", method=RequestMethod.GET)
	@ResponseBody
	public List <Customer> getAll() {
		return os.getalloffices();
	}
	
	//for loading the offices according to username
	
	@RequestMapping(value="offices/{username}", method=RequestMethod.GET)
	@ResponseBody
	public List<Customer> getofficebyuser(@PathVariable String username , ModelMap map) {
		username=username.replaceAll("`", ".");
		List<Customer> c=os.getofficesbyuser(username);
		return c;
	}
	
	//for getting the offices according to the id of office
	
	@RequestMapping(value="offices/id/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Customer getofficebyid(@PathVariable int id , ModelMap map) {
		return os.getofficesbyid(id);
	}
	
	//for getting offices according to approved status
	
	@RequestMapping(value="offices/approved", method=RequestMethod.GET)
	@ResponseBody
	public List<Customer> getApprovedOffices(ModelMap map) {
		
		return os.getofficesbyapproval();
	}
	
	//for getting offices according to approved and engaged status
	
	@RequestMapping(value="offices/approv", method=RequestMethod.GET)
	@ResponseBody
	public List<Customer> getApprovOffices(ModelMap map) {
		
		return os.getofficesbyapprov();
	}
	
	//offices with rejected status
	
	@RequestMapping(value="offices/rejected", method=RequestMethod.GET)
	@ResponseBody
	public List<Customer> getRejectedOffices(ModelMap map) {
		return os.getofficesbyrejected();
	}
	
	//offices with waiting status
	
	@RequestMapping(value="offices/waiting", method=RequestMethod.GET)
	@ResponseBody
	public List<Customer> getWaitingOffices(ModelMap map) {
		return os.getofficesbywaiting();
	}
	
	//approving an office
	
	@RequestMapping(value="/approve/{id}")
	@ResponseBody
	public String ApproveOffice(@PathVariable int id)
	{
		return os.ApproveOffice(id);	
	}
	
	//rejecting an office
	
	@RequestMapping(value="/reject/{id}")
	@ResponseBody
	public String RejectOffice(@PathVariable int id)
	{
		return os.RejectOffice(id);
	}
	
	//deleting an office
	
	@RequestMapping(value="/delete/{id}")
	@ResponseBody
	public String DeleteOffice(@PathVariable int id)
	{
		return os.DeleteOffice(id);
	}
	
	//engage an office
	
	@RequestMapping(value="/engage/{id}")
	@ResponseBody
	public String EngageOffice(@PathVariable int id)
	{
		return os.EngageOffice(id);
	}
	
	//seeing request for an user
	
	@RequestMapping(value="requests/{username}", method=RequestMethod.GET)
	@ResponseBody
	public List<Requests> getrequestbyid(@PathVariable String username , ModelMap map) {
		username=username.replaceAll("`", ".");
		List<Requests> c=os.getrequestsbyid(username);
		return c;
	}
	
	//seeing bookings for an user
	
	@RequestMapping(value="bookings/{username}", method=RequestMethod.GET)
	@ResponseBody
	public List<Requests> getbookingbyid(@PathVariable String username , ModelMap map) {
		username=username.replaceAll("`", ".");
		List<Requests> c=os.getbookingsbyid(username);
		return c;
	}
	
	//uploading an office
	
	@RequestMapping(value="/addOffice", method=RequestMethod.POST)
	@ResponseBody
	public String addOffice(@RequestParam("name") String name, @RequestParam("mail") String mail, @RequestParam("phone") BigDecimal phone, @RequestParam("home") String home, @RequestParam("office") String office,@RequestParam("city") String city, @RequestParam("price") int price,@RequestParam("people") int people,@RequestParam("start_time") String start,@RequestParam("end_time") String end,@RequestParam("file[]") MultipartFile[] files,@RequestParam(value="water", defaultValue="false") boolean water,@RequestParam(value="washroom", defaultValue="false") boolean washroom,@RequestParam(value="ac", defaultValue="false") boolean ac,@RequestParam(value="computer", defaultValue="false") boolean computer,@RequestParam(value="maid", defaultValue="false") boolean maid,@RequestParam("description") String description)
	{
		
		Time start_time=Time.valueOf(start);
		Time end_time=Time.valueOf(end);
		return os.insertOffice(name, mail, phone, home, office, city, price, people, start_time, end_time, files, water,washroom,ac,computer,maid,description);
		
	}
	
	//update an office
	
	@RequestMapping(value="/updateOffice", method=RequestMethod.POST)
	@ResponseBody
	public String updateOffice(@RequestParam("name") String name, @RequestParam("mail") String mail, @RequestParam("phone") BigDecimal phone, @RequestParam("home") String home, @RequestParam("office") String office,@RequestParam("city") String city, @RequestParam("price") int price,@RequestParam("people") int people,@RequestParam("start_time") String start,@RequestParam("end_time") String end,@RequestParam("file[]") MultipartFile[] files,@RequestParam(value="water", defaultValue="false") boolean water,@RequestParam(value="washroom", defaultValue="false") boolean washroom,@RequestParam(value="ac", defaultValue="false") boolean ac,@RequestParam(value="computer", defaultValue="false") boolean computer,@RequestParam(value="maid", defaultValue="false") boolean maid,@RequestParam("description") String description,@RequestParam("office_id") int office_id)
	{
		
		Time start_time=Time.valueOf(start);
		Time end_time=Time.valueOf(end);
		return os.updateOffice(name, mail, phone, home, office, city, price, people, start_time, end_time, files, water,washroom,ac,computer,maid,description,office_id);
		
	}
	
	//
	
	@RequestMapping(value="/status", method=RequestMethod.POST)
	@ResponseBody
	public String ChangeStatus(@RequestParam("reference") String ref_id,@RequestParam("full_price") String price)
	{
		return os.ChangeStatus(ref_id,price);
	}
	
	
	
	//for sending a request
	@RequestMapping(value="/request", method=RequestMethod.POST)
	@ResponseBody
	public String sendrequest(@RequestParam("office_id") int office_id, @RequestParam("owner_name") String owner_name, @RequestParam("sender_name") String sender_name, @RequestParam("owner_mail") String owner_mail,@RequestParam("sender_mail") String sender_mail, @RequestParam("owner_phone") BigDecimal owner_phone,@RequestParam("sender_phone") BigDecimal sender_phone,@RequestParam("space_requested")  int space_requested,@RequestParam("office_address")  String office_address,@RequestParam("office_price") int office_price ,@RequestParam("start_time") String start,@RequestParam("end_time") String end,@RequestParam("start_date") String start_date)
	{
		Time start_time=Time.valueOf(start);
		Time end_time=Time.valueOf(end);
		String check=sendmail(owner_name,sender_name,owner_mail,sender_mail,owner_phone,sender_phone,space_requested,office_address,office_price,start_time,end_time,start_date);
		
		
		if(check==Constants.Failure)
		{
			return check;
		}
		
		Date st=Date.valueOf(start_date);
		
		return os.newrequest(office_id, owner_name, sender_name, owner_mail, sender_mail, owner_phone, sender_phone, space_requested,office_price, start_time, end_time,st,reference_id);
	
	}
	
	
	
	
	
	//for sending mails to a user
	public String sendmail(String owner_name,String sender_name,String owner_mail,String sender_mail,BigDecimal owner_phone,BigDecimal sender_phone, int space_requested,String office_address,int office_price, Time start_time, Time end_time,String start_date)
	{
		
		//generation of reference id
		int ref_id=(int)(Math.random()*99999+89999);
		reference_id=String.valueOf(ref_id);
		
		//for estimation of price
		int hour_difference=(int)(((end_time.getTime()-start_time.getTime())/(3600000))%24);
		int minute_difference=(end_time.getMinutes()-start_time.getMinutes())%60;
		
		
		if(minute_difference<0 || (minute_difference==+0
				&& hour_difference<0))
		{
			minute_difference=60+minute_difference;
		}
		float time_difference=(float)(hour_difference+minute_difference/(float)60);
		if(time_difference<0)
		{
			time_difference=(float)23+time_difference;
		}
		
		
		//sending mail to the uploader
		SimpleMailMessage O_email = new SimpleMailMessage();  
		O_email.setTo(owner_mail);
		O_email.setSubject("Request for Office Space");
		O_email.setText("Hi "+owner_name+"\n\nWe are glad to inform you that a new request has been made for your office space. The details are...\n\nOffice address:   "+office_address+"\nSender Name:   "+sender_name+"\nEmail Address:   "+sender_mail+"\nPhone Number: "+sender_phone+"\nNumber of Employees:  "+space_requested+"\nEstimated entering time:   "+start_time+"\nEstimated leaving time:   "+end_time+"\nStarting date:  "+start_date+"\nEstimated Earnings:  Rs "+((int)(time_difference*(float)office_price))+"\n\nPlease contact the person or wait for him to contact you.\nThank you.\n\nPS: This is an auto generated mail. Please do not reply back");
		
		
		//sending mail to the user
		SimpleMailMessage s_email = new SimpleMailMessage();  
		s_email.setTo(sender_mail);
		s_email.setSubject("Request sent for Office Space");
		s_email.setText("Hi "+sender_name+"\n\nThe request for a new office space has been sent to the owner. The details are...\n\nOffice address:   "+office_address+"\nOwner Name:   "+owner_name+"\nEmail Address:   "+owner_mail+"\nPhone Number: "+owner_phone+"\nEstimated entering time:   "+start_time+"\nEstimated leaving time:   "+end_time+"\nStarting date:  "+start_date+"\nEstimated Cost: Rs "+(int)((float)time_difference*(float)office_price)+"\nReference Id:   "+ref_id+"\n\nPlease contact the person or wait for him to contact you.\nThank you.\n\nPS: This is an auto generated mail. Please do not reply back");
		
		
		try
		{
			mailSender.send(s_email);
			mailSender.send(O_email);
			
			return Constants.Success;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return Constants.Failure;
		}
		
	
	}
}
