package com.hkicl.ncmu.service;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hkicl.core.model.User;
import com.hkicl.ncmu.dao.UserDAO;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	protected Logger logger = LogManager.getLogger(CustomUserDetailsService.class);
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userDAO.getUser(username);
		this.logger.info("loadUserByUsername\t username {}, password {}", user.getUsername(), user.getPassword());
		Collection<GrantedAuthority> authorities = this.userDAO.getRoles(username);
		user.setAuthorities(authorities);
		UserDetails userDetial = (UserDetails) new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), user.getAuthorities());
		return userDetial;
	}
}
