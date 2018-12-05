package com.eu.search.searchweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={"com.eu.search.controller","com.eu.search.serviceImpl"})
public class SearchWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchWebApplication.class, args);
	}
}
