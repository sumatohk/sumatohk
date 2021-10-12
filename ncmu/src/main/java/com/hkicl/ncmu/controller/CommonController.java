package com.hkicl.ncmu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController implements ErrorController{
	protected Logger logger = LogManager.getLogger(CommonController.class);
	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletRequest req, HttpServletResponse res,Exception ex) {
		logger.error("Request: " + req.getRequestURL() + " raised " + ex);
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex.getStackTrace());
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("error");
		req.getHttpServletMapping();
		mav.addObject("STATUS", res.getStatus());
		return mav;
	}
}
