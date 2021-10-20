package com.jx.restapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
     * get all customers
     */
    @GetMapping("/salesCustomers")
    List<SalesCustomer> getAll() {
        return salesCustomerRepository.findAll();
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
     */
    @GetMapping("/salesCustomers/{id}")
    SalesCustomer getOne(@PathVariable Long id) throws SalesCustomerNotFoundException {
      
      return salesCustomerRepository.findById(id)
        .orElseThrow(() -> new SalesCustomerNotFoundException(id));
    }


    @PutMapping("/employees/{id}")
    SalesCustomer update(@RequestBody SalesCustomer newSalesCustomer, @PathVariable Long id) {
      
      return salesCustomerRepository.findById(id)
        .map(salesCustomer -> {
        	salesCustomer.setCustomerName(newSalesCustomer.getCustomerName());
        	salesCustomer.setPhone(newSalesCustomer.getPhone());
          return salesCustomerRepository.save(salesCustomer);
        })
        .orElseGet(() -> {
        	newSalesCustomer.setId(id);
          return salesCustomerRepository.save(newSalesCustomer);
        });
    }

}
