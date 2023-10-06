package com.example.book_systems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.book_systems.entity.Supplier;
import com.example.book_systems.service.ifs.SupplierService;
import com.example.book_systems.vo.respone.SupplierRespone;

@RestController
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;

	@GetMapping(value = "search/supplier")
	public SupplierRespone searchSupplier(@RequestBody Supplier supplier) {
		return supplierService.searchSupplier(supplier);
	}
	
	@PostMapping(value = "add/supplier")
	public SupplierRespone addSupplier(@RequestBody Supplier supplier) {
		return supplierService.addSupplier(supplier);
	}
	
	@PostMapping(value = "update/supplier")
	public SupplierRespone updateSupplier(@RequestBody Supplier supplier) {
		return supplierService.updateSupplier(supplier);
	}
}
