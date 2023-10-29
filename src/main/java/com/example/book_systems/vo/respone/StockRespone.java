package com.example.book_systems.vo.respone;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.book_systems.entity.Stock;

public class StockRespone {

	private String code;
	
	private String message;
	
	private List<Stock> stocks;
	
	private Page<Stock> stocksPage;
	private int thisPage;
	private int allPage;
	private int allData;

	public StockRespone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StockRespone(String code, String message, List<Stock> stocks) {
		super();
		this.code = code;
		this.message = message;
		this.stocks = stocks;
	}
	
	

	public StockRespone(String code, String message, List<Stock> stocks, int thisPage, int allPage, int allData) {
		super();
		this.code = code;
		this.message = message;
		this.stocks = stocks;
		this.thisPage = thisPage;
		this.allPage = allPage;
		this.allData = allData;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public Page<Stock> getStocksPage() {
		return stocksPage;
	}

	public void setStocksPage(Page<Stock> stocksPage) {
		this.stocksPage = stocksPage;
	}

	public int getThisPage() {
		return thisPage;
	}

	public void setThisPage(int thisPage) {
		this.thisPage = thisPage;
	}

	public int getAllPage() {
		return allPage;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public int getAllData() {
		return allData;
	}

	public void setAllData(int allData) {
		this.allData = allData;
	}
	
	
}
