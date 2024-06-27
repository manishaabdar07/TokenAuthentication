package com.token.auth.mail.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.gunadhyasoft.ep.mail")
@ConfigurationPropertiesScan
public class EmailConfig {

	private String hostName;
	private int port;
	private String from;
	private String username;
	private String password;

	public EmailConfig() {
		super();
	}

	public String getFrom() {
		return from;
	}

	public String getHostName() {
		return hostName;
	}

	public String getPassword() {
		return password;
	}

	public int getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	@PostConstruct
	public void printEmailConfig() {
		System.out.println(toString());
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "EmailConfig [hostName=" + hostName + ", port=" + port + ", from=" + from + ", username=" + username
				+ ", password=" + password + "]";
	}

}
