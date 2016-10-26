//Created by Pavas Navaney

package com.driku.ood.Office;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RequestMapper implements RowMapper<Requests>{
	
	@Override
	public Requests mapRow(ResultSet rs, int i) throws SQLException {
	
		Requests p=new Requests();
		p.setOffice_address(rs.getString("office_address"));
		p.setSender_name(rs.getString("sender_name"));
		p.setOwner_name(rs.getString("owner_name"));
		p.setSender_mail(rs.getString("sender_mail"));
		p.setOwner_mail(rs.getString("owner_mail"));
		p.setPhone(rs.getBigDecimal("sender_phone"));
		p.setOwner_phone(rs.getBigDecimal("owner_phone"));
		p.setEntering_time(rs.getTime("start_time"));
		p.setLeaving_time(rs.getTime("end_time"));
		p.setStart_date(rs.getDate("start_date"));

		p.setTimestamp(rs.getString("timestamp"));
		p.setPrice(rs.getInt("rent_price"));
		p.setStatus(rs.getString("status"));
		p.setRef_id(rs.getString("ref_id"));
		return p;
	}

}
