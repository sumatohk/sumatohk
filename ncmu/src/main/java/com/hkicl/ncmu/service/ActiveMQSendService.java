package com.hkicl.ncmu.service;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.hkicl.ncmu.model.CmuMessage;
import com.hkicl.ncmu.model.User;

@Component
public class ActiveMQSendService {

	protected Logger logger = LogManager.getLogger(ActiveMQSendService.class);
	@Autowired
	Connection jmsConnection;

	@SuppressWarnings("serial")
	public void sendMessage(String queueName) {
		try {
			Session session = this.jmsConnection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue(queueName);
			ActiveMQMessageProducer producer = (ActiveMQMessageProducer) session.createProducer(queue);
			ObjectMessage objectMessage = session.createObjectMessage();
			User user = new User();
			user.setUsername("arnodl");
			user.setPassword("AAA");
			Collection<GrantedAuthority> authorities = new Vector<GrantedAuthority>();
			authorities.add(new GrantedAuthority() {
				@Override
				public String getAuthority() {
					return "AA";
				}
			});
			user.setAuthorities(authorities);
			objectMessage.setObject(user);
			//
			producer.send(objectMessage, new AsyncCallback() {
				@Override
				public void onSuccess() {

					logger.info("success {}", new Date());
				}

				@Override
				public void onException(JMSException e) {
					logger.error("failed{},at {} exception", new Date(), e);
				}
			});
			session.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String queueName, JmsTemplate jmsTemplate) {
		try {
			CmuMessage msg = new CmuMessage();
			msg.setMessageId("1234");
			msg.setMessageTitle("title");
			msg.setMessageContent("content");
			jmsTemplate.convertAndSend(queueName, msg);
			jmsTemplate.convertAndSend(queueName, "TEST");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
