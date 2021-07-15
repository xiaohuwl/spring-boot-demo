package com.example.springbootdemo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

	@Autowired
	DataSource dataSource;

	@Bean
	public ApplicationRunner runner() {

		return args -> {

			log.info("DatabaseProductName: {}", dataSource.getConnection().getMetaData().getDatabaseProductName());
		};
	}
}
