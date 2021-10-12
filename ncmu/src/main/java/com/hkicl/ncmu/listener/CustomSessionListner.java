package com.hkicl.ncmu.listener;

import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hkicl.ncmu.component.LoggedUser;
import com.hkicl.ncmu.model.ActiveUserStore;

@WebListener
public class CustomSessionListner implements HttpSessionBindingListener {
	protected Logger logger = LogManager.getRootLogger();
	@Autowired
	private ActiveUserStore activeUserStore;

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		this.logger.info("valueBound");
		List<String> users = activeUserStore.getUsers();
		LoggedUser user = (LoggedUser) event.getValue();
		if (!users.contains(user.getUsername())) {
			users.add(user.getUsername());
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		this.logger.info("valueUnbound");
		List<String> users = activeUserStore.getUsers();
		LoggedUser user = (LoggedUser) event.getValue();
		if (users.contains(user.getUsername())) {
			users.remove(user.getUsername());
		}
	}	
}
