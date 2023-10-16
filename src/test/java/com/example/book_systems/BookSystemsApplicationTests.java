package com.example.book_systems;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@SpringBootTest
class BookSystemsApplicationTests {

	@Autowired
	private JavaMailSender mailSender;
	
	@Test
	void contextLoads() {
		
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);

			try {
				helper.setFrom("cherhorn538@gmail.com", "Speed接案網");
				helper.setTo("cherhorn538@gmail.com");

				String subject = "已要求重新設定密碼";

				String content = "<p>你好, </p>" + "<p>您已要求重新設定密碼</p>" + "<p>驗證碼:</p>" + "<p>"  + "</p>" + "<br>"
						+ "<p>驗證碼有效時間為10分鐘</p>" + "<p>感謝您使用Speed接案網</p>";

				helper.setSubject(subject);

				helper.setText(content, true);

				// send to user email
				mailSender.send(message);
				System.out.println("成功送出");
			} catch (UnsupportedEncodingException | MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}

}
