package com.example.book_systems.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.book_systems.constans.DataRtnCode;
import com.example.book_systems.entity.BookTag;
import com.example.book_systems.entity.Stock;
import com.example.book_systems.repository.BookTagDao;
import com.example.book_systems.repository.StockDao;
import com.example.book_systems.service.ifs.StockService;
import com.example.book_systems.vo.requery.SearchStockRequery;
import com.example.book_systems.vo.respone.BookTagRespone;
import com.example.book_systems.vo.respone.StockRespone;

@Service
public class StockImpl implements StockService{

	@Autowired
	private StockDao stockDao;
	
	@Autowired
	private BookTagDao bookTagDao;
	
	@Override
	public BookTagRespone showAllTag() {
		List<BookTag> all = bookTagDao.findAll();
		if(CollectionUtils.isEmpty(all)) {
			return new BookTagRespone(DataRtnCode.DATA_NOT_FOUND.getCode(),DataRtnCode.DATA_NOT_FOUND.getMessage(),null);
		}
		return new BookTagRespone(DataRtnCode.SUCCESSFUL.getCode(),DataRtnCode.SUCCESSFUL.getMessage(),all);
	}

	@Override
	public BookTagRespone addTag(BookTag bookTag) {
		
		if(bookTag == null) {
			return new BookTagRespone(DataRtnCode.DATA_ERROR.getCode(),DataRtnCode.DATA_ERROR.getMessage(),null);
		}
		
		if(bookTagDao.existsByTag(bookTag.getTag())) {
			return new BookTagRespone(DataRtnCode.INSERT_REPEAT_DATA.getCode(),DataRtnCode.INSERT_REPEAT_DATA.getMessage(),null);
		};
		bookTagDao.save(bookTag);
		List<BookTag> res = bookTagDao.findByTag(bookTag.getTag());
		
		return new BookTagRespone(DataRtnCode.SUCCESSFUL.getCode(),DataRtnCode.SUCCESSFUL.getMessage(),res);
	}

	@Override
	public StockRespone addStock(Stock stock) {
		
		if(stock == null) {
			return new StockRespone(DataRtnCode.DATA_ERROR.getCode(),DataRtnCode.DATA_ERROR.getMessage(),null);
		}
		if(!StringUtils.hasText(stock.getiSBN()) || 
			!StringUtils.hasText(stock.getBook_name()) || 
			!StringUtils.hasText(stock.getUser()) || 
			stock.getPrice()<0 || 
			stock.getSales()<0 || 
			stock.getInventory() <0 || 
			stock.getMin_inventory() <0 || 
			!StringUtils.hasText(stock.getTag()) ) 
		{
			return new StockRespone(DataRtnCode.INPUT_NULL.getCode(),DataRtnCode.INPUT_NULL.getMessage(),null);
		}
		
		if(stockDao.existsById(stock.getiSBN())) {
			return new StockRespone(DataRtnCode.INSERT_REPEAT_DATA.getCode(),DataRtnCode.INSERT_REPEAT_DATA.getMessage(),null);
		}
		
		stockDao.save(stock);
		List<Stock> res = stockDao.findByiSBN(stock.getiSBN());
		
		return new StockRespone(DataRtnCode.SUCCESSFUL.getCode(),DataRtnCode.SUCCESSFUL.getMessage(),res);
	}

	@Override
	public StockRespone searchStock(SearchStockRequery requery) {
		// TODO Auto-generated method stub
		return null;
	}

}
