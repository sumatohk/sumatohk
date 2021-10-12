package com.hkicl.ncmu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = { "com.hkicl" })

public class NCMUApp {
	public static void main(String[] args) {
		SpringApplication.run(NCMUApp.class, args);
	}
}
