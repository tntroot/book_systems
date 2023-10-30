package com.example.book_systems.service.ifs;

import com.example.book_systems.entity.BookTag;
import com.example.book_systems.entity.Stock;
import com.example.book_systems.vo.requery.SearchStockRequery;
import com.example.book_systems.vo.requery.StockEditResquery;
import com.example.book_systems.vo.respone.BookTagRespone;
import com.example.book_systems.vo.respone.StockRespone;

public interface StockService {

	public BookTagRespone showAllTag();
	
	public BookTagRespone addTag(BookTag bookTag);
	
	public StockRespone addStock(Stock stock);
	
	public StockRespone searchStock(SearchStockRequery requery);
	
	public StockRespone editStock(StockEditResquery resquery);
}
