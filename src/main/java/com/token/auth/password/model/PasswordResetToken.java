package com.token.auth.password.model;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import com.token.auth.registration.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class PasswordResetToken {

	private static final int EXPIRATION_TIME = 2880;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false, unique = true)
	private String token;

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	private User user;

	@Column(nullable = false)
	private LocalDateTime expirationTime;

	public PasswordResetToken() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(LocalDateTime expirationTime) {
		this.expirationTime = expirationTime;
	}

	public boolean isExpired(PasswordResetToken passwordResetToken) {
		return LocalDateTime.now().isAfter(passwordResetToken.getExpirationTime());
	}

	public Date getTokenExpirationTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
		return new Date(calendar.getTime().getTime());
	}

	@Override
	public String toString() {
		return "PasswordResetToken [id=" + id + ", token=" + token + ", user=" + user + ", expirationTime="
				+ expirationTime + "]";
	}
}
