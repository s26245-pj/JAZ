package com.pjatk.movieservice2;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieService2Application {

	public static void main(String[] args) {
		SpringApplication.run(MovieService2Application.class, args);
	}


	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Movie Service")
						.version("2.0")
						.description("MovieService application created on advanced java")
						.termsOfService("http://swagger.io/terms/"));
	}

}
