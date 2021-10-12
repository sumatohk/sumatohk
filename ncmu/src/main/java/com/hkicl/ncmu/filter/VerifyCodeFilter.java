package com.hkicl.ncmu.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class VerifyCodeFilter extends OncePerRequestFilter {
	protected Logger logger = LogManager.getRootLogger();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {	
		if ("/login".equals(request.getRequestURI())) {
			try {
				if (validate(request, response)) {
					filterChain.doFilter(request, response);
				} else {
					this.reLogin(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			filterChain.doFilter(request, response);
		}
	}

	protected boolean validate(HttpServletRequest request, HttpServletResponse response) {
		this.logger.info("validate");
		String requestCode = request.getParameter("requestCode");
		HttpSession session = request.getSession();
		if (session.getAttribute("index_code") == null)
			return true;
		Object savedCode = session.getAttribute("index_code");
		if (requestCode == null)
			return false;
		if (savedCode == null)
			return false;
		if (requestCode.trim() == null)
			return false;
		if (savedCode.toString().trim() == null)
			return false;
		if (!savedCode.toString().equalsIgnoreCase(requestCode))
		{
			this.logger.info("savedCode {} vs requestCode {}",savedCode,requestCode);
			return false;
		}
			
		return true;

	}

	protected void reLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestDispatcher rd = request.getRequestDispatcher("/login");
			request.setAttribute("error", "very code in correct!");
			request.setAttribute("username", request.getParameter("username"));
			request.setAttribute("password", request.getParameter("password"));
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
