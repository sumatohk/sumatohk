package com.hkicl.ncmu.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.hkicl.ncmu.service.ActiveMQSendService;

@Configuration
@EnableScheduling
public class ScheduleConfiguration {
	protected Logger logger = LogManager.getLogger(ScheduleConfiguration.class);
	@Autowired
	ActiveMQSendService activeMQSendService;
	@Autowired
	public JmsTemplate jmsTemplate;
	@Scheduled(fixedDelay = 1000)
	public void printMsg() {
		//System.out.println("HELLO WORLD!");
		//read table...
	}
	@Scheduled(fixedDelay = 5000)
	public void sendMQMessage() {
		activeMQSendService.sendMessage("mytest",this.jmsTemplate);
	}
}
