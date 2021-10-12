package com.hkicl.ncmu.model;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
public class User implements Serializable{
	  private String username;
	  private String password;
	  private Collection<GrantedAuthority> authorities;
	  public User() {
		  }
	  public User(String username, String password, Collection<GrantedAuthority> authorities) {
	    this.username = username;
	    this.password = password;
	    this.authorities = authorities;
	  }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
}