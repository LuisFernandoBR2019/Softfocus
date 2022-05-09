package br.com.luis.softfocus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "br.com.luis.softfocus" })
public class SoftfocusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftfocusApplication.class, args);
	}

}
