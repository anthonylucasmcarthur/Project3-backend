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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.aspects.LogIt;
import com.revature.entities.Office;
import com.revature.services.OfficeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Component
@RestController
@RequestMapping("/offices")
@CrossOrigin
@Tag(name = "Office", description = "Office Controller")
public class OfficeController {

	@Autowired
	OfficeService os;
	
	
	@Operation(summary = "Return all offices",  description="Returns all offices", tags={"Office"})
	@GetMapping(produces="application/json")
	public List<Office> getOffices() {
		return os.getOffices();
	}
	
	@LogIt
	@Operation(summary = "Create office",description="Adds a new Office", tags={"Office"})
	@PostMapping(produces="application/json")
	public Office addOffice(@Parameter(description="Office to create", required=true) 
										@Valid @RequestBody(required = true) Office office) {
		return os.addOffice(office);
	}
	
	@LogIt
	@Operation(summary = "Update specified office", description="Updates office", tags={"Office"})
	@PutMapping(produces = "application/json")
	public Office updateOffice(@Parameter(description="Office to update", required=true) @Valid @RequestBody(required = true) Office office) {
		return os.updateOffice(office);
	}
	
//	@LogIt
//	@Operation(summary = "Delete specified office", description="Updates office", tags={"Office"})
//	@DeleteMapping(produces = "application/json")
//	public boolean deleteOffice(@Parameter(description="Office to update", required=true) @Valid @RequestBody(required = true) Office office) {
//		return os.deleteOffice(office);
//	}
	
	@LogIt
	@Operation(summary = "Delete specified office", description="Updates office", tags={"Office"})
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public boolean deleteOffice(@Parameter(description="Office to update", required=true)@PathVariable("id")int id) {
		return os.deleteOffice(os.getOfficeById(id));
	}
	
	@Operation(summary = "Return specified office", description="Returns office by number", tags={"Office"})
	@GetMapping(value = "/{id}", produces = "application/json")
	public Office getOfficeById(@Parameter(description="Id of office", required = true)@PathVariable("id")int id) {
		return os.getOfficeById(id);
	}
	
	
}
