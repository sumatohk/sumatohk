package com.hkicl.ncmu.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling // 1.开启定时任务
@EnableAsync // 2.开启多线程
public class MultithreadScheduleTask {
	protected Logger logger = LogManager.getRootLogger();

	@Async
	@Scheduled(fixedDelay = 60000) // 间隔60秒
	public void first() throws InterruptedException {
//		this.logger.info("first\tthread\\t{},datetime\t{}", Thread.currentThread().getName(),
//				LocalDateTime.now().toLocalTime());
	}

	@Async
	@Scheduled(fixedDelay = 120000)
	public void second() {
//		this.logger.info("first\tthread\\t{},datetime\t{}", Thread.currentThread().getName(),
//				LocalDateTime.now().toLocalTime());
	}
}