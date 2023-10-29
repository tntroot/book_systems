package com.example.book_systems.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.example.book_systems.entity.Stock;

public class StockMapper implements RowMapper<Stock>{

	public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
		Stock stock = new Stock();
		stock.setiSBN(rs.getString("iSBN"));
		stock.setBook_name(rs.getString("book_name"));
		stock.setUser(rs.getString("user"));
		stock.setPrice(rs.getInt("price"));
		stock.setSales(rs.getInt("sales"));
		stock.setInventory(rs.getInt("inventory"));
		stock.setMin_inventory(rs.getInt("min_inventory"));
		stock.setTag(rs.getString("tag"));
		
	      return stock;
	   }
}
