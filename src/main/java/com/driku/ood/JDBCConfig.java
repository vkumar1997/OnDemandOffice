//created by vikas kumar

package com.driku.ood;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JDBCConfig {

	 @Autowired
	    DataSource dataSource;
	    @Bean
	    public JdbcTemplate getJdbcTemplate() {
	    	return new JdbcTemplate(dataSource);
	    }

}
