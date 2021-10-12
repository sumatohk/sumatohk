package com.hkicl.ncmu.component;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hkicl.ncmu.model.ActiveUserStore;

@Component
public class LoggedUser implements HttpSessionBindingListener {

	protected Logger logger = LogManager.getRootLogger();
	private String username; 
    @Autowired
    protected ActiveUserStore activeUserStore;
    
    public LoggedUser(String username, ActiveUserStore activeUserStore) {
        this.username = username;
        this.activeUserStore = activeUserStore;
    }
    
    public LoggedUser() {}

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        List<String> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (!users.contains(user.getUsername())) {
            users.add(user.getUsername());
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        List<String> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (users.contains(user.getUsername())) {
            users.remove(user.getUsername());
        }
    }
    // standard getter and setter

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}