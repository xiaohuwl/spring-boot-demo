/**
 * Copyright (c) 2007 Beijing Shiji Kunlun Software Co., Ltd. All Rights Reserved.
 * $Id$
 */
package com.example.springbootdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Andy Han <andy.han@shijigroup.com>
 * @since 2021-07-20
 */
@ControllerAdvice
public class EmployeeNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String employeeNotFoundHandler(EmployeeNotFoundException ex) {

		return ex.getMessage();
	}
}
