package io.github.robertoaraujo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackAndpplication {


    public static void main(String[] args) {
        SpringApplication.run(BackAndpplication.class, args);
		Logger logger = LoggerFactory.getLogger(BackAndpplication.class);
		logger.info("Aplicação iniciada com sucesso!");
		logger.info("Acesse url-documentação-swagger: http://localhost:8080/api/swagger-ui.html");
    }

}
