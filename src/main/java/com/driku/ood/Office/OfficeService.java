//Created By Vikas Kumar and Pavas Navaney

package com.driku.ood.Office;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class OfficeService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	List<Customer> customer;
	
	
	
	//Add images list to any image query and provides with the final list
	public List<Customer> FinalList(List<Customer> customer)
	{
		for(Customer c: customer)
		{
			int office_id=c.getOffice_id();
			c.setFiles(getImages(office_id));
			
		}
		return customer;
	}
	
	
	//Add images to any one office
	public Customer FinalObject(Customer customer)
	{
		
		int office_id=customer.getOffice_id();
		customer.setFiles(getImages(office_id));
		return customer;
	}
	
	
	
	
	
	//to perform a join from the images and office table so that all the images can be put into one array and attached with the json response
	public ArrayList<String> getImages(int office_id)
	{
		ArrayList<String> files=new ArrayList<String>();
		List<Images> images=jdbcTemplate.query(Constants.ImageQuery,new ImageMapper(),office_id);
        
		for(Images image: images)
		{
			try
			{
				String ImagePath=image.getPath()+image.getImage_id()+Constants.extension;			
	        	files.add(ImagePath);
			}
			catch(Exception e){e.printStackTrace();}
			
		}
		return files;
	}
	
	
	
	
	
	//get all offices
	public List<Customer> getalloffices() {
		try
		{
			customer=jdbcTemplate.query(Constants.AllOfficesQuery,new Mapper());
			return FinalList(customer);
		}catch(EmptyResultDataAccessException e)
		{
	        return null;
		}
	}
	
	//get all offices by user
	public List<Customer> getofficesbyuser(String username)
	{
		try
		{
			customer=jdbcTemplate.query(Constants.UserOfficeQuery,new Mapper(),username);
			return FinalList(customer);
		
		}catch(EmptyResultDataAccessException e)
		{
			return null;
		}	
	}
	
	
	//get an offices by id
	public Customer getofficesbyid(int id)
	{
		try
		{
			Customer customer=jdbcTemplate.queryForObject(Constants.IdOfficeQuery,new Mapper(),id);
			return FinalObject(customer);
		}catch(EmptyResultDataAccessException e)
		{	
			return null;
		}
	}	
	
	
	
	
	
	
	
	//get offices by approval
	public List<Customer> getofficesbyapproval()
	{
		try
		{
			customer=jdbcTemplate.query(Constants.ApprovedOfficeQuery,new Mapper());
			return FinalList(customer);
		}catch(EmptyResultDataAccessException e)
		{
			return null;
		}
		
	
	}
	
	
	//get office by approval and engaged status
	public List<Customer> getofficesbyapprov()
	{
		try
		{
			customer=jdbcTemplate.query(Constants.ApprovOfficeQuery,new Mapper());
			return FinalList(customer);
		}catch(EmptyResultDataAccessException e)
		{
			return null;
		}
		
	
	}
	
	
	//get office by rejected status
	public List<Customer> getofficesbyrejected()
	{
		try
		{
			customer=jdbcTemplate.query(Constants.RejectedOfficeQuery,new Mapper());
			return FinalList(customer);
		}catch(EmptyResultDataAccessException e)
		{	
			return null;
		}
	
	
	}
	
	
	//get office by waiting status
	public List<Customer> getofficesbywaiting()
	{
		try
		{
			customer=jdbcTemplate.query(Constants.WaitingOfficeQuery,new Mapper());
			return FinalList(customer);
		}catch(EmptyResultDataAccessException e)
		{
			return null;
		}
		
	
	}
	
	
	//approve office
	public String ApproveOffice(int id)
	{
		try
		{
			jdbcTemplate.update(Constants.ApproveOfficeUpdate,id);
			return Constants.Approved;
		}catch(EmptyResultDataAccessException e)
		{	
			return Constants.Failure;
		}
		
	}
	
	
	//reject office
	public String RejectOffice(int id)
	{
		try
		{
			jdbcTemplate.update(Constants.RejectOfficeUpdate,id);
			return Constants.Rejected;
		}catch(EmptyResultDataAccessException e)
		{
			return Constants.Failure;
		}
		
	}
	
	
	//delete office
	public String DeleteOffice(int id)
	{
		try
		{
			jdbcTemplate.update(Constants.DeleteOfficeUpdate, id);
			return Constants.Deleted;
		}catch(EmptyResultDataAccessException e)
		{
			return Constants.Failure;
		}
		
	}
	
	
	//engage office
	public String EngageOffice(int id)
	{
		try
		{
			jdbcTemplate.update(Constants.EngageOfficeUpdate, id);
			return Constants.Engaged;
		}catch(EmptyResultDataAccessException e)
		{
			return Constants.Failure;
		}
		
	}
	
	
	//request history
	public List<Requests> getrequestsbyid(String username)
	{
		try
		{
			List<Requests> requests=jdbcTemplate.query(Constants.RequestHistoryQuery,new RequestMapper(),username);
			return requests;
		}catch(EmptyResultDataAccessException e)
		{	
			return null;
		}
	}
	
	
	//booking history
	public List<Requests> getbookingsbyid(String username)
	{
		try
		{
			List<Requests> requests=jdbcTemplate.query(Constants.BookingHistoryQuery,new RequestMapper(),username);
			return requests;
		}catch(EmptyResultDataAccessException e)
		{
			return null;
		}
	}
	
	
	//change status from active time counter to total earnings
	public String ChangeStatus(String ref_id, String price)
	{
		try
		{
			jdbcTemplate.update(Constants.ChangeRequestStatus,new Object[]{price,ref_id});
			return Constants.Rejected;
		}catch(EmptyResultDataAccessException e)
		{
			return Constants.Failure;
		}
	}
	
	
	//insert office
	public String insertOffice( String name, String mail, BigDecimal phone, String home, String office, String city, int price, int people,Time start_time, Time end_time, MultipartFile[] files, boolean water, boolean washroom, boolean ac, boolean computer, boolean maid, String description)
	{
		
		try
		{
			jdbcTemplate.execute(Constants.CreateOfficeTable);
			jdbcTemplate.execute(Constants.CreateImageTable);
		}
		catch(Exception e)
		{	}
		
		
		// Set office_id to auto increment and views and requests to 0 by default in MySql
		try
		{
			Timestamp timestamp;
			Date date=new Date();
			timestamp=new Timestamp(date.getTime());
			String insertSql=Constants.InsertOfficeTable;
			jdbcTemplate.update(insertSql,new Object[]{name,mail,phone,home,office,city,price,people,start_time,end_time,water,washroom,ac,computer,maid,description,timestamp});
			int office_id=jdbcTemplate.queryForObject(Constants.SelectMaxId,Integer.class);
			if (files.length!=0)
			{
				if(!(new File(Constants.mkdir).exists()))
				{
					new File(Constants.mkdir).mkdir();
				}
				
				//Please change in constants
				insertSql=Constants.InsertImageTable;
				String path=Constants.filepath;
				String sqlpath=Constants.sqlpath;
				String extension=Constants.extension;
				for(MultipartFile file: files)
				{
					jdbcTemplate.update(insertSql,new Object[]{office_id,sqlpath});
					
					int image_id=jdbcTemplate.queryForObject(Constants.SelectMaxImageId,Integer.class);
					BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
					File destination = new File(path+image_id+extension);
					ImageIO.write(src, Constants.ImageType, destination);
					
				}
			}
			return Constants.Success;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return Constants.Failure;
		}
	}
	
	
	//update office
	public String updateOffice( String name, String mail, BigDecimal phone, String home, String office, String city, int price, int people,Time start_time, Time end_time, MultipartFile[] files, boolean water, boolean washroom, boolean ac, boolean computer, boolean maid, String description, int office_id)
	{
		
		try
		{
			Timestamp timestamp;
			Date date=new Date();
			timestamp=new Timestamp(date.getTime());
			String insertSql=Constants.UpdateOfficeTable;
			List<Images> images=jdbcTemplate.query(Constants.ImageQuery,new ImageMapper(),office_id);
			for(Images image: images)
			{
				try
				{
					File Image=new File(Constants.filepath+image.getImage_id()+Constants.extension);			
		        	Image.delete();
				}
				catch(Exception e){e.printStackTrace();}
				
			}
			
			jdbcTemplate.update(Constants.DeleteFromImages,office_id);
			jdbcTemplate.update(insertSql,new Object[]{name,mail,phone,home,office,city,price,people,start_time,end_time,water,washroom,ac,computer,maid,description,timestamp,office_id});
			
			
			if (files.length!=0)
			{
				if(!(new File(Constants.mkdir).exists()))
				{
					new File(Constants.mkdir).mkdir();
				}
				insertSql=Constants.InsertImageTable;
				String path=Constants.filepath;
				String sqlpath=Constants.sqlpath;
				String extension=Constants.extension;
				for(MultipartFile file: files)
				{
					jdbcTemplate.update(insertSql,new Object[]{office_id,sqlpath});
					
					int image_id=jdbcTemplate.queryForObject(Constants.SelectMaxImageId,Integer.class);
					BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
					File destination = new File(path+image_id+extension);
					ImageIO.write(src, Constants.ImageType, destination);
					
				}
			}
			return Constants.Success;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return Constants.Failure;
		}
	}
	
	
	//new office
	public String newrequest(int office_id,String owner_name,String sender_name,String owner_mail,String sender_mail,BigDecimal owner_phone,BigDecimal sender_phone, int space_requested,int office_price, Time start_time, Time end_time,Date start,String reference_id)
	{
		try
		{
			jdbcTemplate.execute(Constants.CreateRequestTable);
			
		}
		catch(Exception e)
		{
		}
		try
		{
			Timestamp timestamp;
			Date date=new Date();
			timestamp=new Timestamp(date.getTime());
			String insertSql=Constants.InsertRequestsTable;
			jdbcTemplate.update(insertSql,new Object[]{office_id,owner_name,sender_name,owner_mail,sender_mail,owner_phone,sender_phone,space_requested,office_price,start_time,end_time,start,timestamp,reference_id});
			jdbcTemplate.update(Constants.UpdateLikes, office_id);
			return Constants.Success;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return Constants.Failure;
		}
	}
}
