//Created By Vikas Kuamr

package com.driku.ood.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<UserLogin> {
	@Override
	public UserLogin mapRow(ResultSet rs, int i) throws SQLException {
		
		UserLogin p=new UserLogin();
		p.setEmail_address(rs.getString("mail_id"));
		p.setName(rs.getString("name"));
		p.setPassword(rs.getString("password"));
		p.setPhone(rs.getBigDecimal("phone"));
		p.setCity(rs.getString("city"));
		p.setLogintype(rs.getString("login_type"));
		p.setTimestamp(rs.getTimestamp("timestamp"));
		return p;
	}

}
