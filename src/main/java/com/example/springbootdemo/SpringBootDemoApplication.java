package com.example.springbootdemo;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.springbootdemo.model.Employee;
import com.example.springbootdemo.repository.EmployeeRepository;

@SpringBootApplication
public class SpringBootDemoApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootDemoApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

	@Autowired
	DataSource dataSource;

	@Bean
	public ApplicationRunner initDatabase(EmployeeRepository repository) {

		return args -> {

			log.info("DatabaseProductName: {}", dataSource.getConnection().getMetaData().getDatabaseProductName());
			log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
			log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
		};
	}
}