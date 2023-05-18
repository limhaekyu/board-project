package com.limhaekyu.boardproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@Configuration
public class LayOutConfig {
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
}
