package com.springwebapp.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="database")
@Component
public class Database {

	private String userName;
	private String password;
	private String url;
	
	public Database()
	{}
	
	public Database(String userName, String password, String url) {
		super();
		this.userName = userName;
		this.password = password;
		this.url = url;
	}

	public String getUserName() {
		return "USERNAME: "+userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return "URL: "+ url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
