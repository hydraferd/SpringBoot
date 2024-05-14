package com.example.nds;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NdsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NdsApplication.class, args);
	}
   @Bean
   public ModelMapper modelMapper() {
	   return new ModelMapper();
   }
}
