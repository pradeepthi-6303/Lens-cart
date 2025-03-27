//package com.capgemini.lenscart;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class OrderManagement1Application {
//
//	public static void main(String[] args) {
//		SpringApplication.run(OrderManagement1Application.class, args);
//	}
//
//}
package com.capgemini.lenscart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients // This enables the Feign clients in your application
public class  OrderManagement1Application{
    public static void main(String[] args) {
        SpringApplication.run(OrderManagement1Application.class, args);
    }
}
