package com.TechTron.customerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})// to disable database
@SpringBootApplication
public class CustomerBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerBackApplication.class, args);

	}

}
