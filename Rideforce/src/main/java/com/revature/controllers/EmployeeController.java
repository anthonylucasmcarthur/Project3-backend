package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aspects.LogIt;
import com.revature.entities.Employee;
import com.revature.services.EmployeeService;
import com.revature.services.OfficeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Component
@RestController
@RequestMapping("/employees")
@CrossOrigin
@Tag(name = "Employee", description = "Employee Controller")
public class EmployeeController {
	
	@Autowired
	private EmployeeService es;
	
	@Autowired
	private OfficeService or;
	
	@PostMapping
	@Operation(summary = "Log in operation", description="Returns employee", tags={"User"})
	public Employee login( @RequestParam(name="username")String username,@RequestParam(name="password")String password) {
		return es.loginEmployee(username, password);
	}
	
	@Operation(summary="Return all employees", description="Returns all employees", tags={"User"})
    @GetMapping(produces="application/json")
	public List<Employee> getEmployees() {
		return es.getEmployees();
	}
	
	@LogIt
	@Operation(summary = "Update specified user", description="Updates user", tags={"User"})
    @PutMapping(produces = "application/json")
	public Employee updateEmployee(@Parameter(description="Admin to update", required=true) @Valid @RequestBody(required=true) Employee employee) {
		return es.updateEmployee(employee);
	}
	
	@LogIt
	@Operation(summary = "Delete specified user", description="Deletes user by id", tags={"User"})
    @DeleteMapping(produces = "application/json")
	public boolean deleteUserById(@Parameter(description="Id of Admin", required=true) @Valid @RequestBody(required=true) Employee employee) {
		return es.deleteEmployee(employee);
	}

	
	@Operation(summary = "Return specified user", description="Returns user by id", tags={"User"})
    @GetMapping(value = "/{id}", produces = "application/json")
	public Employee getEmployeeById(@Parameter(description="Id of Admin", required=true) @PathVariable("id")int id) {
		return es.getEmployeeById(id);
	}

	
}
