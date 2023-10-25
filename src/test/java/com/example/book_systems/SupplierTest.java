package com.example.book_systems;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.book_systems.entity.Supplier;
import com.example.book_systems.repository.SupplierDao;
import com.example.book_systems.service.ifs.SupplierService;

@SpringBootTest(classes = BookSystemsApplication.class)
public class SupplierTest {
	
	private SupplierDao supplierDao;
	
	@Autowired
	private SupplierService supplierService;

	@Test
	public void addSupplierTest() {
		List<Supplier> arr = supplierDao.findByCompileds(94563212);
		System.out.println(arr);
	}
}
