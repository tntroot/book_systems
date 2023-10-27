package com.example.book_systems.service.ifs;

import org.springframework.stereotype.Service;

import com.example.book_systems.entity.Supplier;
import com.example.book_systems.vo.requery.SupplierEditCompiledRequery;
import com.example.book_systems.vo.requery.SupplierSearchRequery;
import com.example.book_systems.vo.respone.SupplierRespone;

public interface SupplierService {

	public SupplierRespone searchSupplier(SupplierSearchRequery supplier1);
	
	public SupplierRespone addSupplier(Supplier supplier);
	
	public SupplierRespone updateSupplier(Supplier supplier, Integer oldCompiled);
}
