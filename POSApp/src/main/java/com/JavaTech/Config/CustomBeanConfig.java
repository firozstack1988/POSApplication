package com.JavaTech.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanConfig {

	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
	}
}
