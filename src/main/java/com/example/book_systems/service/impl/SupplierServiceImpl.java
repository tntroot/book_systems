package com.example.book_systems.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.book_systems.constans.DataRtnCode;
import com.example.book_systems.entity.Supplier;
import com.example.book_systems.repository.SupplierDao;
import com.example.book_systems.service.ifs.SupplierService;
import com.example.book_systems.vo.requery.SupplierSearchRequery;
import com.example.book_systems.vo.respone.SupplierRespone;

@Service
public class SupplierServiceImpl implements SupplierService{

	@Autowired
	private SupplierDao supplierDao;
	
	@Override
	public SupplierRespone searchSupplier(SupplierSearchRequery supplier) {
		
		List<Supplier> arr = supplierDao.searchSuppliers(supplier.getName(), supplier.getCompiled(), supplier.getCity());
		if(CollectionUtils.isEmpty(arr)) {
			return new SupplierRespone(DataRtnCode.DATA_NOT_FOUND.getCode(),DataRtnCode.DATA_NOT_FOUND.getMessage(),null);
		}
		return new SupplierRespone(DataRtnCode.SUCCESSFUL.getCode(),DataRtnCode.SUCCESSFUL.getMessage(),arr);
	}

	@Override
	public SupplierRespone addSupplier(Supplier supplier) {
		
		if(supplier == null ) {
			return new SupplierRespone(DataRtnCode.INPUT_NULL.getCode(),DataRtnCode.INPUT_NULL.getMessage(),null);
		}
		
		List<String> arr = new ArrayList<>(Arrays.asList(supplier.getName(),supplier.getEmail(),supplier.getLocation_id(),supplier.getLocation_name(),supplier.getPhone()));
		if (checkAddSupplier(arr) || supplier.getCompiled() == 0) {
			return new SupplierRespone(DataRtnCode.INPUT_NULL.getCode(),DataRtnCode.INPUT_NULL.getMessage(),null);
		}
		
		List<Supplier> thisSupplier = supplierDao.findByCompiled(supplier.getCompiled());
		if(!CollectionUtils.isEmpty(thisSupplier)) {
			return new SupplierRespone(DataRtnCode.INSERT_REPEAT_DATA.getCode(),DataRtnCode.INSERT_REPEAT_DATA.getMessage(),null);
		}
		
		int insertAdd = supplierDao.inserSupplier(supplier.getName(),supplier.getCompiled(),supplier.getEmail(),supplier.getPhone(),supplier.getLocation_id(),supplier.getLocation_name());
		if(insertAdd == 0) {
			return new SupplierRespone(DataRtnCode.INSERT_DATA.getCode(),DataRtnCode.INSERT_DATA.getMessage(),null);
		}
		
		thisSupplier = supplierDao.findByCompiled(supplier.getCompiled());
		
		return new SupplierRespone(DataRtnCode.SUCCESSFUL.getCode(),DataRtnCode.SUCCESSFUL.getMessage(),thisSupplier);
	}
	public boolean checkAddSupplier(List<String> arr) {
		for(String item:arr) {
			if(!StringUtils.hasText(item)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public SupplierRespone updateSupplier(Supplier supplier) {
		
		if(supplier == null) {
			return new SupplierRespone(DataRtnCode.UPDATE_ERROR.getCode(),DataRtnCode.UPDATE_ERROR.getMessage(),null);
		}
		
		String oldSe_num = supplier.getSerial_num().substring(1, supplier.getSerial_num().length());
		String NewSe_num = supplier.getLocation_id() + oldSe_num;
		
		if(supplierDao.updateThisSupplier(NewSe_num,supplier.getName(), supplier.getCompiled(), 
				supplier.getEmail(), supplier.getPhone(), supplier.getLocation_id(), 
				supplier.getLocation_name(), supplier.getSerial_num())==0) {
			
			return new SupplierRespone(DataRtnCode.UPDATE_ERROR.getCode(),DataRtnCode.UPDATE_ERROR.getMessage(),null);
		}
		List<Supplier> arr = supplierDao.findBySerialNum(NewSe_num);
 		return new SupplierRespone(DataRtnCode.SUCCESSFUL.getCode(),DataRtnCode.SUCCESSFUL.getMessage(),arr);
	}

}
