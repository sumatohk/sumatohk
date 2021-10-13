package com.hkicl.ncmu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Application {
	@Value("${queue}")
	public String queue;
	@Value("${topic}")
	public String topic;
	@Value("${broker_url}")
	public String broker_url;
	@Value("${broker_user}")
	public String broker_user;
	@Value("${broker_pass}")
	public String broker_pass;
	//
	@Value("${jdbc_url}")
	public String jdbc_url;
	@Value("${jdbc_user}")
	public String jdbc_user;
	@Value("${jdbc_pass}")
	public String jdbc_pass;
	@Value("${jdbc_driver}")
	public String jdbc_driver;
}
