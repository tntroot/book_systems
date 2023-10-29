package com.example.book_systems.repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.sql.QuerySelect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.SqlCall;

import com.example.book_systems.entity.Stock;
import com.example.book_systems.mapper.StockMapper;

public interface StockDao extends JpaRepository<Stock, String> {
	

	public List<Stock> findByiSBN(String ISBN);
	
	@Query(value = "select ISBN, book_name, user, price, sales, inventory, min_inventory, tag from stock "
			+ " where ISBN = (case when :input_iSBN is null then ISBN else :input_iSBN end)"
			+ " and book_name = (case when :input_bookName is null then book_name else :input_bookName end)"
			+ " and user = (case when :input_user is null then user else :input_user end)"
			+ " and if(:input_compare = '>=', inventory >= :input_inventory, inventory <= :input_inventory)"
			+ " order by inventory asc"
			,nativeQuery = true)
	public List<Stock> searchStock(
			@Param("input_iSBN") String isBn,
			@Param("input_bookName") String bookName,
			@Param("input_user") String user, 
			@Param("input_compare") String compare ,
			@Param("input_inventory") int inventory);	


	@Modifying
	@Transactional
	@Query(value = "update stock set ISBN = :input_newISBN, book_name = :input_bookName,"
			+ " `user` = :input_user, price = :input_price, inventory = :input_inventory,"
			+ " min_inventory = :input_min_inventory, tag = :input_tag"
			+ " where ISBN = :input_oldISBN",nativeQuery = true)
	public int updateISBN(
			@Param("input_newISBN") String newISBN,
			@Param("input_bookName") String book_name,
			@Param("input_user") String user,
			@Param("input_price") int price,
			@Param("input_inventory") int inventory,
			@Param("input_min_inventory") int min_inventory,
			@Param("input_tag") String tag,
			@Param("input_oldISBN") String oldISBN);
}
