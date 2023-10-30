package com.example.book_systems.vo.requery;

import com.example.book_systems.entity.Stock;

public class StockEditResquery {

	private Stock stock;
	
	private String oldISBN;

	public StockEditResquery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StockEditResquery(Stock stock, String oldISBN) {
		super();
		this.stock = stock;
		this.oldISBN = oldISBN;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public String getOldISBN() {
		return oldISBN;
	}

	public void setOldISBN(String oldISBN) {
		this.oldISBN = oldISBN;
	}
	
	
}
