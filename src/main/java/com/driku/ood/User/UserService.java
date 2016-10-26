//Created By Vikas Kumar and Pavas Navaney

package com.driku.ood.User;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;



@Service
public class UserService {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	//get user by id
	public String getUserById(String id, String password)
	{
		try
		{
			String name;
			String username;
			BigDecimal phone;
			String login_type;
			String city;
			UserLogin user=jdbcTemplate.queryForObject(UserConstants.UserIdQuery,new Object[]{id,password},new UserMapper());
			name=user.getName();
			username=user.getEmail_address();
			phone=user.getPhone();
			login_type=user.getLogintype();
			city=user.getCity();
			return name+"^"+username+"^"+phone+"^"+city+"^"+login_type;
			
		}
		catch(EmptyResultDataAccessException e)
		{
			e.printStackTrace();	
			return UserConstants.Failure;
		}
	}
	
	
	//insert user
	public String insertUser( String mail, String password, String name, BigDecimal phone, String city)
	{
		
		try
		{
			jdbcTemplate.execute(UserConstants.CreateUserTable);
			
		}
		catch(Exception e)
		{
		}
		
		try
		{
			Timestamp timestamp;
			Date date=new Date();
			timestamp=new Timestamp(date.getTime());
			String insertSql=UserConstants.InsertUser;
			jdbcTemplate.update(insertSql,new Object[]{mail,password,name,phone,city,"user",timestamp});
			return UserConstants.Created;
		}
		catch(Exception e)
		{
			return UserConstants.Duplicate;
		}
	}
	
	
	//get user from user table
	public String getUser(String id)
	{
		try
		{
			UserLogin user=jdbcTemplate.queryForObject(UserConstants.IdUserQuery,new UserMapper(),id);
			return user.getName();
		}catch(EmptyResultDataAccessException e)
		{	
			return UserConstants.Failure;
		}
	}	
	
	
	//change of password
	public String changePassword(String mail_id,String password)
	{
		try
		{
			jdbcTemplate.update(UserConstants.ChangePasswordUpdate,new Object[]{password,mail_id});
			return UserConstants.Success;
		}catch(EmptyResultDataAccessException e)
		{
			return UserConstants.Failure;
		}
		
	}
}
