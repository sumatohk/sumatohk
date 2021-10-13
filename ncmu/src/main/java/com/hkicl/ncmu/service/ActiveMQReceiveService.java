package com.hkicl.ncmu.service;

import javax.jms.Connection;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.hkicl.core.model.CmuMessage;
import com.hkicl.ncmu.config.Application;

@Service
public class ActiveMQReceiveService {
	@Autowired
	JmsTemplate jmsTemplate;
	@Autowired
	Connection jmsConnection;
	@Autowired
	Application application;

	@JmsListener(destination = "${queue}")
	public void receive() throws Exception {
		Session jmsSession = this.jmsConnection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		Queue queue = jmsSession.createQueue(application.queue);
		this.jmsTemplate.setDefaultDestination(queue);
		this.jmsTemplate.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
		this.jmsTemplate.setSessionTransacted(false);
		Object obj = this.jmsTemplate.receiveAndConvert();

		if (obj instanceof TextMessage) {

		}
		if (obj instanceof ObjectMessage) {
			ObjectMessage om = (ObjectMessage) this.jmsTemplate.receiveAndConvert();
			if (om instanceof CmuMessage) {
				CmuMessage msg = (CmuMessage) om;
				msg.printMsg();
				om.acknowledge();
			} else {
				System.out.println("no process");
			}
		}
	}
}
