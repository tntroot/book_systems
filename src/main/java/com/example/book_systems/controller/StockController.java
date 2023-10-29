package com.example.book_systems.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.book_systems.constans.DataRtnCode;
import com.example.book_systems.entity.BookTag;
import com.example.book_systems.entity.Stock;
import com.example.book_systems.service.ifs.StockService;
import com.example.book_systems.vo.requery.SearchStockRequery;
import com.example.book_systems.vo.respone.BookTagRespone;
import com.example.book_systems.vo.respone.StockRespone;

@RestController
@CrossOrigin
@RequestMapping(value = "book_systems/setting/stock")
public class StockController {
	
	@Autowired
	private StockService stockService;

	@GetMapping(value = "search/tag")
	private BookTagRespone showAlltag() {
		return stockService.showAllTag();
	}
	
	@PostMapping(value = "add/tag")
	private BookTagRespone addTag(@RequestBody BookTag bookTag) {
		return stockService.addTag(bookTag);
	}
	
	@PostMapping(value = "add/stock")
	private StockRespone addStock(@RequestBody Stock stock) {
		return stockService.addStock(stock);
	}
	
	@PostMapping(value = "search/stock")
	private StockRespone searchStock(@RequestBody SearchStockRequery requery) {
		
		StockRespone stockRespone = stockService.searchStock(requery);
		
		if(stockRespone.getCode() != "200") {
			return stockRespone;
		}
		
		List<Stock> res = stockRespone.getStocks();
		
		
//		Pageable pageRequest = PageRequest.of(requery.getPage().getPage(),2);
//				
//		Page<Stock> res = new PageImpl<Stock>(stockRespone.getStocks(),pageRequest,stockRespone.getStocks().size());
		
		// res.getNumberOfElements(); // 總比數
		// res.getSize(); // 當前頁面比數
		// res.getTotalElements();
		// res.getTotalPages(); // 總頁數
		
		return new StockRespone(DataRtnCode.SUCCESSFUL.getCode(), DataRtnCode.SUCCESSFUL.getMessage(), res);
	}
	
}
