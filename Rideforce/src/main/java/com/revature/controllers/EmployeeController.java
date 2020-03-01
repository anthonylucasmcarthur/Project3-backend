package com.revature.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.revature.aspects.LogIt;
import com.revature.entities.Employee;
import com.revature.services.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Component
@RestController
@RequestMapping("/employees")
@CrossOrigin
@Tag(name = "Employee", description = "Employee Controller")
public class EmployeeController {

	@Autowired
	private EmployeeService es;

	@PostMapping(value = "/create", produces = "application/json")
	@Operation(summary = "Create employeee operation", description = "Creates employee", tags = { "Employee" })
	public Employee addEmployee(
			@Parameter(description = "Employee to create", required = true) @Valid @RequestBody(required = true) Employee employee) {
		return es.addEmployee(employee);
	}

	@PostMapping(value = "/login")
	@Operation(summary = "Log in operation", description = "Returns employee", tags = { "Employee" })
	public Employee login(
			@Parameter(description = "Employee to login", required = true) @Valid @RequestBody ObjectNode objectNode) {
		String username = objectNode.get("username").asText();
		String password = objectNode.get("password").asText();

		return es.loginEmployee(username, password);
	}

	@Operation(summary = "Return all employees", description = "Returns all employees", tags = { "Employee" })
	@GetMapping(produces = "application/json")
	public List<Employee> getEmployees() {
		return es.getEmployees();
	}

	@LogIt
	@Operation(summary = "Update specified employee", description = "Updates employee", tags = { "Employee" })
	@PutMapping(produces = "application/json")
	public Employee updateEmployee(
			@Parameter(description = "Employee to update", required = true) @Valid @RequestBody(required = true) Employee employee) {
		return es.updateEmployee(employee);
	}

//	@LogIt
//	@Operation(summary = "Delete specified employee", description = "Deletes employee", tags = { "Employee" })
//	@DeleteMapping(produces = "application/json")
//	public boolean deleteEmployee(
//			@Parameter(description = "Employee to delete", required = true) @Valid @RequestBody(required = true) Employee employee) {
//		return es.deleteEmployee(employee);
//	}
	
	@LogIt
	@Operation(summary = "Delete specified employee", description = "Deletes employee", tags = { "Employee" })
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public boolean deleteEmployee(
			@Parameter(description = "Employee to delete", required = true) @PathVariable("id") int id) {
		return es.deleteEmployee(es.getEmployeeById(id));
	}

	@Operation(summary = "Return specified user", description = "Returns user by id", tags = { "User" })
	@GetMapping(value = "/{id}", produces = "application/json")
	public Employee getEmployeeById(
			@Parameter(description = "Id of Employee", required = true) @PathVariable("id") int id) {
		return es.getEmployeeById(id);
	}

}
