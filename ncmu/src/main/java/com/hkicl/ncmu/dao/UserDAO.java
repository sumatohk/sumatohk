package com.hkicl.ncmu.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.hkicl.ncmu.model.User;

@Repository
public class UserDAO {
	protected Logger logger = LogManager.getLogger(UserDAO.class);
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public User getUser(String username) {
		this.logger.info("getUser");
		String sql = "select * from users where username=?";
		Map<String, Object> row = this.jdbcTemplate.queryForMap(sql, username);
		this.logger.info("getUser row={}",row);
		User user = new User();
		user.setPassword(row.get("password").toString());
		user.setUsername(row.get("username").toString());
		return user;
	}

	public Collection<GrantedAuthority> getRoles(String username) {
		this.logger.info("getRoles");
		String sql = "select role from user_roles where username =? ";
		Collection<GrantedAuthority> authorities = new Vector<GrantedAuthority>();
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,username);
		rows.forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.get("role").toString()));
		});
		this.logger.info("getRoles role size is {}",authorities.size());
		authorities.forEach(a->{
			this.logger.info("authority {}",a.getAuthority());
		});
		return authorities;
	}
}
