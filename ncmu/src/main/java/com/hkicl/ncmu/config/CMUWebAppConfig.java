package com.hkicl.ncmu.config;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
@Import({ SecurityConfig.class, MultithreadScheduleTask.class, ScheduleConfiguration.class })
public class CMUWebAppConfig {
	protected Logger logger = LogManager.getRootLogger();
	@Autowired
	Application application;

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(application.jdbc_driver);
		driverManagerDataSource.setUrl(application.jdbc_url);
		driverManagerDataSource.setUsername(application.jdbc_user);
		driverManagerDataSource.setPassword(application.jdbc_pass);
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
		connectionFactory.setBrokerURL(application.broker_url);
		connectionFactory.setPassword(application.broker_user);
		connectionFactory.setUserName(application.broker_pass);
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
