package com.capgemini.lenscart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients


@SpringBootApplication
public class OrderManagement1Application {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagement1Application.class, args);
	}

}
