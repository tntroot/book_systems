package com.example.book_systems.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.book_systems.entity.Stock;

public interface StockDao extends JpaRepository<Stock, String> {
	

	public List<Stock> findByiSBN(String ISBN);
	
	@Query(value = "select ISBN, book_name, user, price, sales, inventory, min_inventory, tag from stock "
			+ " where ISBN like concat('%', :input_iSBN ,'%')"
			+ " and book_name like concat('%', :input_bookName ,'%')"
			+ " and user like concat('%', :input_user ,'%')"
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
