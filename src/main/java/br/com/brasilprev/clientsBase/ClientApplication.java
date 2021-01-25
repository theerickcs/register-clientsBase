package br.com.brasilprev.clientsBase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
//@EnableCaching
@EnableSwagger2
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

}
