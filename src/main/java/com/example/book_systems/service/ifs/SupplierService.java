package com.example.book_systems.service.ifs;

import org.springframework.stereotype.Service;

import com.example.book_systems.entity.Supplier;
import com.example.book_systems.vo.respone.SupplierRespone;

public interface SupplierService {

	public SupplierRespone searchSupplier(Supplier supplier);
	
	public SupplierRespone addSupplier(Supplier supplier);
	
	public SupplierRespone updateSupplier(Supplier supplier);
}
