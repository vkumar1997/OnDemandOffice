//created by pavas navaney

package com.driku.ood;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.driku.ood")
public class WebConfiguration extends WebMvcConfigurerAdapter {
	
	//sql connection
	@Bean(name = "dataSource")
	   public DriverManagerDataSource dataSource() {
	        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	        driverManagerDataSource.setDriverClassName(ConfigConstants.DriverName);
	        driverManagerDataSource.setUrl(ConfigConstants.DatabaseName);
	        driverManagerDataSource.setUsername(ConfigConstants.setUsername);
	        driverManagerDataSource.setPassword(ConfigConstants.setPassword);
	        return driverManagerDataSource;
	    }
	
	//for images
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
	    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    return resolver;
	}
	
	
	//for loding index.jsp
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        
    }
	
	
	//for sending mails
	@Bean
    public JavaMailSender getMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
         
        //Using gmail
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(ConfigConstants.gmailUsername);
        mailSender.setPassword(ConfigConstants.gmailPassword);
         
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");//Prints out everything on screen
         
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
	
	
	
	@Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
	
	 
}
