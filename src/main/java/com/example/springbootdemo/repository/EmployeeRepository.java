/**
 * Copyright (c) 2007 Beijing Shiji Kunlun Software Co., Ltd. All Rights Reserved.
 * $Id$
 */
package com.example.springbootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootdemo.model.Employee;

/**
 *
 * @author Andy Han <andy.han@shijigroup.com>
 * @since 2021-07-20
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}