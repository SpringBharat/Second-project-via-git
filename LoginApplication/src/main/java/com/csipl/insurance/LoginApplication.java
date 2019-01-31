package com.csipl.insurance;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginApplication  implements ApplicationRunner  {

	public static void main(String[] args) {
		
		SpringApplication.run(LoginApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
	System.out.println("LoginApplication.run()");
	System.out.println("LoginApplication.run()");
		
	}

}

