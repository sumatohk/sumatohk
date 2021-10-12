package com.hkicl.cmuweb.controller;

import com.hkicl.ncmu.model.User;

public class UserController {
	public void getAll()
	{
		User user = new User();
		user.getAuthorities();
	}
}
