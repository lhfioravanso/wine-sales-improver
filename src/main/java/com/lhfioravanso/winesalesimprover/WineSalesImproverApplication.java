package com.lhfioravanso.winesalesimprover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableCaching
@EnableScheduling
@SpringBootApplication
public class WineSalesImproverApplication {

	public static void main(String[] args) {
		SpringApplication.run(WineSalesImproverApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
