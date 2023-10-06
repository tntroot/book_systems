package com.example.book_systems.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.book_systems.entity.Supplier;

public interface SupplierDao extends JpaRepository<Supplier, String> {

	public List<Supplier> findAll();
	
	@Query("select count(serial_num) from supplier")
	public List<Supplier> findCount();
	
//	@Modifying
//	@Transactional
//	@Query()
//	public List<Supplier> updateSupplier();
}
