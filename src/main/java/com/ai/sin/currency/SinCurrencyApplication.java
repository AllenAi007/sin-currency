package com.ai.sin.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SinCurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SinCurrencyApplication.class, args);
	}

}
