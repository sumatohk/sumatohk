package com.hkicl.ncmu.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component("myLogoutSuccessHandler")
public class MyLogoutSuccessHandler implements LogoutSuccessHandler{
	protected Logger logger = LogManager.getRootLogger();
	@Override
    public void onLogoutSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session != null){
            session.removeAttribute("user");
        }
    }
}
