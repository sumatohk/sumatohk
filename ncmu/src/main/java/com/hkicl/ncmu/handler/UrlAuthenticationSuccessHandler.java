package com.hkicl.ncmu.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.hkicl.ncmu.model.ActiveUserStore;

@Component
public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	protected Logger logger = LogManager.getRootLogger();
	@Autowired
	ActiveUserStore activeUserStore;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		this.logger.info("onAuthenticationSuccess START username\t{}", authentication.getName());
		this.logger.info("onAuthenticationSuccess START auths\t{}", authentication.getAuthorities());
		HttpSession session = request.getSession(true);
		session.setAttribute("_username", authentication.getName());
		this.logger.info("onAuthenticationSuccess END");
		try {
			response.sendRedirect("/welcome");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}