/**
 * Copyright (c) 2007 Beijing Shiji Kunlun Software Co., Ltd. All Rights Reserved.
 * $Id$
 */
package com.example.springbootdemo.controller;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootdemo.model.Employee;
import com.example.springbootdemo.repository.EmployeeRepository;

import io.micrometer.core.annotation.Timed;

/**
 * EmployeeController
 *
 * @author Andy Han <andy.han@shijigroup.com>
 * @since 2021-07-20
 */
@RestController
public class EmployeeController {

	private final EmployeeRepository repository;

	EmployeeController(EmployeeRepository repository) {

		this.repository = repository;
	}

	@GetMapping("/employees")
	List<Employee> all() {

		return repository.findAll();
	}

	@PostMapping("/employees")
	Employee newEmployee(@RequestBody Employee newEmployee) {

		return repository.save(newEmployee);
	}

	// Single item Async way
	@Timed(histogram = true)
	@GetMapping("/employeesAsync/{id}")
	CompletionStage<Employee> oneAsync(@PathVariable Long id) {

		return CompletableFuture
				.supplyAsync(() -> one(id), Executors.newWorkStealingPool())
				.whenCompleteAsync((e, t) -> {
					System.out.println(e + " " + Instant.now());
				}, Executors.newWorkStealingPool());

	}

	// Single item
	@Timed
	@GetMapping("/employees/{id}")
	Employee one(@PathVariable Long id) {

		try {

			System.out.println("GetOne: " + Instant.now());
			Thread.sleep(15 * 1_000);
		} catch (InterruptedException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return repository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	@PutMapping("/employees/{id}")
	Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

		return repository.findById(id)
				.map(employee -> {
					employee.setName(newEmployee.getName());
					employee.setRole(newEmployee.getRole());
					return repository.save(employee);
				})
				.orElseGet(() -> {
					newEmployee.setId(id);
					return repository.save(newEmployee);
				});
	}

	@DeleteMapping("/employees/{id}")
	void deleteEmployee(@PathVariable Long id) {

		repository.deleteById(id);
	}
}
