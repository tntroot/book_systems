package com.example.book_systems.vo.respone;

import java.util.List;

import com.example.book_systems.entity.Stock;

public class StockRespone {

	private String code;
	
	private String message;
	
	private List<Stock> stocks;

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
	
	
}
