package com.example.book_systems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book_systems.entity.BookTag;
import com.example.book_systems.entity.Stock;
import com.example.book_systems.service.ifs.StockService;
import com.example.book_systems.vo.requery.SearchStockRequery;
import com.example.book_systems.vo.respone.BookTagRespone;
import com.example.book_systems.vo.respone.StockRespone;

@RestController
@CrossOrigin
@RequestMapping(value = "book_system/setting/stock")
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
		return stockService.searchStock(requery);
	}
	
}
