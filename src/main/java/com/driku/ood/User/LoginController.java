//Created by Vikas Kumar and Pavas Navaney

package com.driku.ood.User;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LoginController {
	
	@Autowired
	UserService user;
	@Autowired
	JavaMailSender mailSender; 
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public String getUserByid(@RequestParam("user_name") String id ,@RequestParam("pass_word") String password) {
		return user.getUserById(id, password);
	}
	
	
	
	
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	@ResponseBody
	public String addUser(@RequestParam("mail") String mail, @RequestParam("enter_password") String password,@RequestParam("name") String name, @RequestParam("phone") BigDecimal phone,@RequestParam("city") String city)
	{
		return user.insertUser(mail, password, name, phone, city);
	}
	
	@RequestMapping(value="/otp", method=RequestMethod.POST)
	@ResponseBody
	public String addUser(@RequestParam("mail") String mail, @RequestParam("name") String name, @RequestParam("one_time") String otp)
	{
		return sendmail(mail, name, otp);
	}
	
	@RequestMapping(value="/getUser/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String getUser(@PathVariable String id , ModelMap map)
	{
		id=id.replaceAll("`", ".");
		return user.getUser(id);
	}
	
	@RequestMapping(value="/change", method=RequestMethod.POST)
	@ResponseBody
	public String changePassword(@RequestParam("mail") String mail,@RequestParam("n_password") String password)
	{
		return user.changePassword(mail,password);
	}
	

		
	public String sendmail(String mail, String name, String otp)
	{
		SimpleMailMessage email = new SimpleMailMessage();  
		email.setTo(mail);
		email.setSubject("OTP from On Demand Office");
		email.setText("Hi "+name+",\n\nYour one time password is "+otp+"\n\n\nThis is an auto generated mail. Please do not reply back.");
		try
		{
			mailSender.send(email);
			return UserConstants.Success;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return UserConstants.Failure;
		}
	}
	
	
	

}
