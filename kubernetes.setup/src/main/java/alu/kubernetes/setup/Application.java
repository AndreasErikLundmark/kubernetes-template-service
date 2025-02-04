package alu.kubernetes.setup;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		final Logger logger = LoggerFactory.getLogger(Application.class);
		SpringApplication.run(Application.class, args);
		logger.info("Application started ------------------>>>>>>");

	}

}
