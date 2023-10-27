package com.example.book_systems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.book_systems.constans.DataRtnCode;
import com.example.book_systems.entity.Location;
import com.example.book_systems.entity.Supplier;
import com.example.book_systems.repository.LocationDao;
import com.example.book_systems.service.ifs.SupplierService;
import com.example.book_systems.vo.requery.SupplierEditCompiledRequery;
import com.example.book_systems.vo.requery.SupplierSearchRequery;
import com.example.book_systems.vo.respone.LocationRespone;
import com.example.book_systems.vo.respone.SupplierRespone;

@RestController
@CrossOrigin
@RequestMapping("book_systems/supplier")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private LocationDao locationDao;
	
	@GetMapping(value = "search/location")
	public LocationRespone searchLocation() {
		List<Location> arr = locationDao.findAll();
		if(CollectionUtils.isEmpty(arr)) {
			return new LocationRespone(DataRtnCode.DATA_NOT_FOUND.getCode(),DataRtnCode.DATA_NOT_FOUND.getMessage(),null);
		}
		return new LocationRespone(DataRtnCode.SUCCESSFUL.getCode(),DataRtnCode.SUCCESSFUL.getMessage(),arr);
	}

	@GetMapping(value = "search/supplier")
	public SupplierRespone searchSupplier(SupplierSearchRequery supplier) {
		if(!StringUtils.hasText(supplier.getName())) {
			supplier.setName(null);
		}
		if(supplier.getCompiled()==0) {
			supplier.setCompiled(null);
		}
		if(!StringUtils.hasText(supplier.getCity())) {
			supplier.setCity(null);
		}
		return  supplierService.searchSupplier(supplier);
	}
	
	@PostMapping(value = "add/supplier")
	public SupplierRespone addSupplier(@RequestBody Supplier supplier) {
		return supplierService.addSupplier(supplier);
	}
	
	@PostMapping(value = "update/supplier")
	public SupplierRespone updateSupplier(@RequestBody SupplierEditCompiledRequery supplier) {
		
		return supplierService.updateSupplier(supplier.getSupplier(),supplier.getOldCompiled());
	}
}
