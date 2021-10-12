package com.hkicl.ncmu.config;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.hkicl.ncmu.*" })
@Import({ SecurityConfig.class })
public class CMUWebAppConfig {
	protected Logger logger = LogManager.getRootLogger();
	String BROKER_URL = "tcp://139.162.63.146:61616??jms.useAsyncSend=true";
	String BROKER_USERNAME = "admin";
	String BROKER_PASSWORD = "admin";

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/formsdb");
		driverManagerDataSource.setUsername("forms");
		driverManagerDataSource.setPassword("P@ssw0rd");
		return driverManagerDataSource;
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(BROKER_URL);
		connectionFactory.setPassword(BROKER_USERNAME);
		connectionFactory.setUserName(BROKER_PASSWORD);
		connectionFactory.setSendAcksAsync(true);
		return connectionFactory;
	}

	@Bean
	public Connection jmsCollection() {
		Connection connection = null;
		try {
			connection = this.connectionFactory().createConnection();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return connection;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(this.connectionFactory());
		template.setMessageConverter(this.jacksonJmsMessageConverter());
		template.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
		return template;
	}

	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
}
