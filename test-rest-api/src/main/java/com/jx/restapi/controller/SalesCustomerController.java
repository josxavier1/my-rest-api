package com.jx.restapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jx.restapi.domain.SalesCustomer;
import com.jx.restapi.repository.SalesCustomerRepository;

/**
 * curl commands for windows (adjust according to the env and db)
 * ==============================================================
 * curl -v localhost:8080/salesCustomers
 * curl -v localhost:8080/salesCustomers/11
 * curl -X POST localhost:8080/salesCustomers -H "Content-type:application/json" -d "{\"customerName\": \"Gandalf\", \"phone\": \"1111111111\"}"
 */

/**
 * @author JCheraparambil
 *
 */
@RestController
public class SalesCustomerController {

	private final SalesCustomerRepository salesCustomerRepository;

	public SalesCustomerController(SalesCustomerRepository salesCustomerRepository) {
		this.salesCustomerRepository = salesCustomerRepository;
	}

	/*
	 * get all customers before Hateos
	 * 
	 */
	@GetMapping("/salesCustomers")
	CollectionModel<EntityModel<SalesCustomer>> getAll() {

		List<EntityModel<SalesCustomer>> salesCustomers = null;
		try {
			salesCustomers = salesCustomerRepository.findAll().stream().map(salesCustomer -> {
				try {
					return EntityModel.of(salesCustomer,
							linkTo(methodOn(SalesCustomerController.class).getOne(salesCustomer.getId())).withSelfRel(),
							linkTo(methodOn(SalesCustomerController.class).getAll()).withRel("salesCustomers"));
				} catch (SalesCustomerNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}).collect(Collectors.toList());
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CollectionModel.of(salesCustomers,
				linkTo(methodOn(SalesCustomerController.class).getAll()).withSelfRel());
	}

	/*
	 * add a single sales customer
	 */
	@PostMapping("/salesCustomers")
	SalesCustomer addNew(@RequestBody SalesCustomer salesCustomer) {
		return salesCustomerRepository.save(salesCustomer);
	}

	/*
	 * return a single sales customer
	 * 
	 * without Hateos
	 */
	@GetMapping("/salesCustomers/{id}")
	EntityModel<SalesCustomer> getOne(@PathVariable Long id) throws SalesCustomerNotFoundException {

		SalesCustomer salesCustomer = salesCustomerRepository.findById(id) //
				.orElseThrow(() -> new SalesCustomerNotFoundException(id));

		return EntityModel.of(salesCustomer, //
				linkTo(methodOn(SalesCustomerController.class).getOne(id)).withSelfRel(),
				linkTo(methodOn(SalesCustomerController.class).getAll()).withRel("salesCustomers"));
	}

	
	@PutMapping("/salesCustomers/{id}")
	SalesCustomer update(@RequestBody SalesCustomer newSalesCustomer, @PathVariable Long id) {

		return salesCustomerRepository.findById(id).map(salesCustomer -> {
			salesCustomer.setCustomerName(newSalesCustomer.getCustomerName());
			salesCustomer.setPhone(newSalesCustomer.getPhone());
			return salesCustomerRepository.save(salesCustomer);
		}).orElseGet(() -> {
			newSalesCustomer.setId(id);
			return salesCustomerRepository.save(newSalesCustomer);
		});
	}

}
