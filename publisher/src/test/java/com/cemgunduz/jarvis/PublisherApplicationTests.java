package com.cemgunduz.jarvis;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PublisherApplication.class)
@Ignore
public class PublisherApplicationTests {

	@Test
	public void contextLoads() {

		GmailCompatibleEmailTest emailTest = new GmailCompatibleEmailTest();
		try {
			emailTest.send();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
