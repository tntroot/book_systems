package com.example.book_systems.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.book_systems.constans.DataRtnCode;
import com.example.book_systems.entity.Supplier;
import com.example.book_systems.repository.SupplierDao;
import com.example.book_systems.service.ifs.SupplierService;
import com.example.book_systems.vo.respone.SupplierRespone;

@Service
public class SupplierServiceImpl implements SupplierService{

	@Autowired
	private SupplierDao supplierDao;
	
	@Override
	public SupplierRespone searchSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SupplierRespone addSupplier(Supplier supplier) {
		
		if (supplier == null) {
			return new SupplierRespone(DataRtnCode.INPUT_NULL.getCode(),DataRtnCode.INPUT_NULL.getMessage(),null);
		}
//		String num = 
		return null;
	}

	@Override
	public SupplierRespone updateSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return null;
	}

}
