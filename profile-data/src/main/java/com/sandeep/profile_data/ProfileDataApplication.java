package com.sandeep.profile_data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class ProfileDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfileDataApplication.class, args);
	}

}
