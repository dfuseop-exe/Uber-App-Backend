package com.project.uber.uberApp;

import com.project.uber.uberApp.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UberAppApplicationTests {

	@Autowired
	EmailService emailService;

	@Test
	void contextLoads() {
		emailService.sendEmail(
				"its.abhi917@gmail.com" ,
				"Test Demo Email" ,
				"Dummy Email From Uber"
		);
	}

	@Test
	void sendBulkEmail() {
		String[] email = {"its.abhi917@gmail.com" , "sushant.165.shinde@gmail.com"};
		emailService.sendEmail(
                email,
				"Test Demo Email" ,
				"Dummy Email From Uber"
		);
	}
}
