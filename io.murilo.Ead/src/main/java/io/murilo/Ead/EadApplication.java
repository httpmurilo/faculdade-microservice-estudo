package io.murilo.Ead;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EadApplication {

	public static void main(String[] args) {
		SpringApplication.run(EadApplication.class, args);
	}

}
