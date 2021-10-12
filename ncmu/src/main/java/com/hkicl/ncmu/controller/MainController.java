package com.hkicl.ncmu.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hkicl.ncmu.util.NcmuUtil;

@Controller
public class MainController {
	protected Logger logger = LogManager.getLogger(MainController.class);
	@Autowired
    protected HttpSession session;
	@RequestMapping(value = { "/", "/welcome" })
	public ModelAndView defaultPage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is default page!");
		model.setViewName("hello");
		return model;

	}
	@RequestMapping(value = { "/add/{msg}"})
	@ResponseBody
	public String add(@PathVariable("msg")String msg) {
		this.logger.info("Hello {}",msg);
		return String.format("Hello %s", msg);
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminPage() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");
		return model;

	}

	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
			System.out.println("error\t" + error);
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
			System.out.println("model\t" + model);
		}
		model.setViewName("login");
		this.session.setAttribute("needverifycode", "true");
		return model;

	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}

		model.setViewName("403");
		return model;

	}

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return this.login(null, "logout");
	}

	@RequestMapping("/users")
	public String getLoggedUsers(Locale locale, Model model) {
		model.addAttribute("user", NcmuUtil.AUTH_USER);
		return "users";
	}
}