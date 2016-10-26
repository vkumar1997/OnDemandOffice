//Created By Vikas Kumar...... Mapper class for mapping images with the office ID 

package com.driku.ood.Office;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ImageMapper implements RowMapper<Images>{

	@Override
	public Images mapRow(ResultSet rs, int i) throws SQLException {
		Images image=new Images();
		image.setOffice_id(rs.getInt("office_id"));
		image.setImage_id(rs.getInt("image_id"));
		image.setPath(rs.getString("image_path"));
		return image;
	}
}
