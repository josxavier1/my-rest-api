package com.jx.restapi.controller;

public class SalesCustomerNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3911465696323102277L;

	SalesCustomerNotFoundException(Long id) {
	    super("Could not find Sales Customer  " + id);
	  }
}
