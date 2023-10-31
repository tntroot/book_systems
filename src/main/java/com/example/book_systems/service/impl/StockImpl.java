package com.example.book_systems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

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
import com.example.book_systems.vo.requery.StockEditResquery;
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
			return new BookTagRespone(DataRtnCode.TAG_REPEAT_DATA.getCode(),DataRtnCode.TAG_REPEAT_DATA.getMessage(),null);
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
		
		if(requery == null) {
			return new StockRespone(DataRtnCode.DATA_ERROR.getCode(),DataRtnCode.DATA_ERROR.getMessage(),null);
		}
		
		List<Stock> arr = stockDao.searchStock(requery.getiSBN(), requery.getBookName(), requery.getUser(), requery.getCompare(), requery.getInventory());
		
		if(CollectionUtils.isEmpty(arr)) {
			return new StockRespone(DataRtnCode.DATA_NOT_FOUND.getCode(),DataRtnCode.DATA_NOT_FOUND.getMessage(),null);
		}
		//	jpa 好像不支持 動態帶入運算符，先不撈出 篩選庫存量資料，另外再去做塞選	
//		if(requery.getCompare().equals(">=")) {
//			arr = arr.stream().filter((i)-> i.getInventory() >= requery.getInventory()).collect(Collectors.toList());
//		}else {
//			arr = arr.stream().filter((i)-> i.getInventory() <= requery.getInventory()).collect(Collectors.toList());
//		}
		
		if(StringUtils.hasText(requery.getTag())) {
			String[] tag = requery.getTag().split(",");
			for(String item:tag) {
				arr = arr.stream().filter((i) -> i.getTag().contains(item)).collect(Collectors.toList());
			}
		}
		if(arr.size()<=0) {
			return new StockRespone(DataRtnCode.DATA_NOT_FOUND.getCode(),DataRtnCode.DATA_NOT_FOUND.getMessage(),null);
		}
		
		return new StockRespone(DataRtnCode.SUCCESSFUL.getCode(),DataRtnCode.SUCCESSFUL.getMessage(),arr);
	}

	@Override
	public StockRespone editStock(StockEditResquery resquery) {
		
		if(resquery == null) {
			return new StockRespone(DataRtnCode.DATA_ERROR.getCode(),DataRtnCode.DATA_ERROR.getMessage(),null);
		}
		
		if(!StringUtils.hasText(resquery.getStock().getiSBN()) || 
			!StringUtils.hasText(resquery.getStock().getBook_name()) || 
			!StringUtils.hasText(resquery.getStock().getUser()) || 
			resquery.getStock().getPrice()<0 || 
			resquery.getStock().getInventory() <0 || 
			resquery.getStock().getMin_inventory() <0 || 
			!StringUtils.hasText(resquery.getStock().getTag()) ) 
		{
			return new StockRespone(DataRtnCode.INPUT_NULL.getCode(),DataRtnCode.INPUT_NULL.getMessage(),null);
		}
		
		if(stockDao.existsById(resquery.getStock().getiSBN())) {
			return new StockRespone(DataRtnCode.EDIT_REPEAT_DATA.getCode(),DataRtnCode.EDIT_REPEAT_DATA.getMessage(),null);
		}
		
		int edit = stockDao.updateISBN(resquery.getStock().getiSBN(), 
				resquery.getStock().getBook_name(), resquery.getStock().getUser(),
				resquery.getStock().getPrice(), resquery.getStock().getInventory(),
				resquery.getStock().getMin_inventory(), resquery.getStock().getTag(),
				resquery.getOldISBN());
		if (edit == 0) {
			return new StockRespone(DataRtnCode.UPDATE_ERROR.getCode(),DataRtnCode.UPDATE_ERROR.getMessage(),null);
		}
		
		List<Stock> arr = stockDao.findByiSBN(resquery.getStock().getiSBN());
		
		return new StockRespone(DataRtnCode.SUCCESSFUL.getCode(),DataRtnCode.SUCCESSFUL.getMessage(),arr);
	}

}
