package com.hkicl.ncmu;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
	public static void main(String[] args)
	{
		BCryptPasswordEncoder aa = new BCryptPasswordEncoder(11);
		String cypher=aa.encode("123456");
		System.out.println(cypher);
	}
}
