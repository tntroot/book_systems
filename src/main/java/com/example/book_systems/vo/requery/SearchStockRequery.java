package com.example.book_systems.vo.requery;

public class SearchStockRequery {

	private String iSBN;
	
	private String bookName;
	
	private String user;
	
	private String compare = ">";
	
	private int inventory;
	
	private String tag;
	
	// 當前頁數
	private PageReuqery page;

	public SearchStockRequery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchStockRequery(String iSBN, String bookName, String user, String compare, int inventory, String tag,
			PageReuqery page) {
		super();
		this.iSBN = iSBN;
		this.bookName = bookName;
		this.user = user;
		this.compare = compare;
		this.inventory = inventory;
		this.tag = tag;
		this.page = page;
	}

	public String getiSBN() {
		return iSBN;
	}

	public void setiSBN(String iSBN) {
		this.iSBN = iSBN;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCompare() {
		return compare;
	}

	public void setCompare(String compare) {
		this.compare = compare;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public PageReuqery getPage() {
		return page;
	}

	public void setPage(PageReuqery page) {
		this.page = page;
	}

}
