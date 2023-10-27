package com.example.book_systems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_systems.entity.Stock;

public interface StockDao extends JpaRepository<Stock, String> {

	public List<Stock> findByiSBN(String ISBN);
	
}
