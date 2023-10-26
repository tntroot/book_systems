package com.example.book_systems.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

	private String checkEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	private String checkPhone = "^0[\\d]{9}";
	
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
		
		SupplierRespone respone = checkAddUpdateData(supplier);
		if(!respone.getCode().equals("200")) {
			return respone;
		}
		
		int insertAdd = supplierDao.inserSupplier(supplier.getName(),supplier.getCompiled(),supplier.getEmail(),supplier.getPhone(),supplier.getLocation_id(),supplier.getLocation_name());
		if(insertAdd == 0) {
			return new SupplierRespone(DataRtnCode.INSERT_DATA.getCode(),DataRtnCode.INSERT_DATA.getMessage(),null);
		}
		
		List<Supplier> thisSupplier = supplierDao.findByCompileds(supplier.getCompiled());
		
		return new SupplierRespone(DataRtnCode.SUCCESSFUL.getCode(),DataRtnCode.SUCCESSFUL.getMessage(),thisSupplier);
	}
	
	// 檢查更新/新增內容
	private SupplierRespone checkAddUpdateData(Supplier supplier) {
		
		if(supplier == null ) {
			return new SupplierRespone(DataRtnCode.INPUT_NULL.getCode(),DataRtnCode.INPUT_NULL.getMessage(),null);
		}
		
		if (!StringUtils.hasText(supplier.getEmail()) || 
				!StringUtils.hasText(supplier.getLocation_id()) || 
				!StringUtils.hasText(supplier.getLocation_name()) || 
				!StringUtils.hasText(supplier.getName()) || 
				!StringUtils.hasText(supplier.getPhone()) || 
				supplier.getCompiled() <= 0) {
			return new SupplierRespone(DataRtnCode.INPUT_NULL.getCode(),DataRtnCode.INPUT_NULL.getMessage(),null);
		}
		
		if(!supplier.getEmail().matches(checkEmail)) {
			return new SupplierRespone(DataRtnCode.EMAIL_EXITS.getCode(),DataRtnCode.EMAIL_EXITS.getMessage(),null);
		}
		if(!supplier.getPhone().matches(checkPhone)) {
			return new SupplierRespone(DataRtnCode.PHONE_EXITS.getCode(),DataRtnCode.PHONE_EXITS.getMessage(),null);
		}
		
		List<Supplier> thisSupplier = supplierDao.findByCompileds(supplier.getCompiled());
		if(!CollectionUtils.isEmpty(thisSupplier)) {
			return new SupplierRespone(DataRtnCode.COMPILED_REPEAT_DATA.getCode(),DataRtnCode.COMPILED_REPEAT_DATA.getMessage(),null);
		}
		
		return new SupplierRespone(DataRtnCode.SUCCESSFUL.getCode(),DataRtnCode.SUCCESSFUL.getMessage(),null);
	}

	@Override
	public SupplierRespone updateSupplier(Supplier supplier) {
		
		SupplierRespone respone = checkAddUpdateData(supplier);
		if(!respone.getCode().equals("200")) {
			return respone;
		}
		
//		List<Supplier> all = supplierDao.findAll();
//		List<Supplier> findall = all.stream().filter( i -> i.getLocation_id().equals(supplier.getLocation_id().substring(0,1))).collect(Collectors.toList());
//		
//		String oldSe_num = supplier.getSerial_num().substring(1, supplier.getSerial_num().length());
//		String NewSe_num = supplier.getLocation_id() + String.format("%05d", findall.size()+1);
		
		if(supplierDao.updateThisSupplier(supplier.getName(), supplier.getCompiled(), 
				supplier.getEmail(), supplier.getPhone(), supplier.getLocation_id(), 
				supplier.getLocation_name(),supplier.getSerial_num())==0) {
			
			return new SupplierRespone(DataRtnCode.UPDATE_ERROR.getCode(),DataRtnCode.UPDATE_ERROR.getMessage(),null);
		}
		List<Supplier> arr = supplierDao.findBySerialNum(supplier.getSerial_num());
 		return new SupplierRespone(DataRtnCode.SUCCESSFUL.getCode(),DataRtnCode.SUCCESSFUL.getMessage(),arr);
	}

}
