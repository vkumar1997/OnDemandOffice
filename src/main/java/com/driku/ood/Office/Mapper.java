//Mapper for offices
//Created by Pavas Navaney

package com.driku.ood.Office;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Mapper implements RowMapper<Customer>{
	@Override
	public Customer mapRow(ResultSet rs, int i) throws SQLException {
		
		Customer p=new Customer();
		p.setOffice_id(rs.getInt("office_id"));
		p.setName(rs.getString("name"));
		p.setMail_id(rs.getString("mail_id"));
		p.setPhone_no(rs.getBigDecimal("phone_no"));
		p.setAddress(rs.getString("address"));
		p.setOffice_address(rs.getString("office_address"));
		p.setCity(rs.getString("city"));
		p.setRent_price(rs.getInt("rent_price"));
		p.setEstimated_people(rs.getInt("estimated_people"));
		p.setOpening_time(rs.getTime("opening_time"));
		p.setClosing_time(rs.getTime("closing"));
		p.setWater_facility(rs.getBoolean("water_facility"));
		p.setWashroom_facility(rs.getBoolean("washroom_facility"));
		p.setAc_facility(rs.getBoolean("ac_facility"));
		p.setComputer_facility(rs.getBoolean("computer_facility"));
		p.setOffice_maid(rs.getBoolean("office_maid"));
		p.setDescription(rs.getString("description"));
		p.setPull_requests(rs.getInt("pull_requests"));
		p.setTimestamp(rs.getTimestamp("timestamp"));
		p.setApproval(rs.getString("approval"));
		return p;
	}

}
