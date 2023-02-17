package com.maurialbornoz.mangalenderbackend;

import com.maurialbornoz.mangalenderbackend.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MangaLenderBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangaLenderBackendApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean(name = "AppProperties")
	public AppProperties getAppProperties(){
		return new AppProperties();
	}

	 @Bean
	public SpringApplicationContext springApplicationContext(){
		return new SpringApplicationContext();
	 }
}


