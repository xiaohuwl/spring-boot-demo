/**
 * Copyright (c) 2007 Beijing Shiji Kunlun Software Co., Ltd. All Rights Reserved.
 * $Id$
 */
package com.example.springbootdemo.controller;

/**
 *
 * @author Andy Han <andy.han@shijigroup.com>
 * @since 2021-07-20
 */
public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7540132522517934239L;

	EmployeeNotFoundException(Long id) {

		super("Could not find employee " + id);
	}

}
