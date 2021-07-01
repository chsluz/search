package com.search;

import java.io.FileNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchApplication {

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(SearchApplication.class, args);
	}

}
