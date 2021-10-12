package com.hkicl.ncmu.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class NcmuUtil {
	public static String AUTH_USER;
	static {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AUTH_USER = auth.getName();
	}
	
}
