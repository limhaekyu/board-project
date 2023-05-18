package com.limhaekyu.boardproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
public class BoardProjectApplication {

	@Bean
	public CharacterEncodingFilter characterEncodingFilter() {
	    CharacterEncodingFilter characterEncodingFilter = new 
	    CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BoardProjectApplication.class, args);
	}

}
