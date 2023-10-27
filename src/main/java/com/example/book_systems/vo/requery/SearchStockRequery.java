package com.example.book_systems.vo.requery;

public class SearchStockRequery {

	private String iSBN;
	
	private String name;
	
	private String user;
	
	private String compare = ">";
	
	private int inventory;
	
	private String tag;

	public SearchStockRequery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchStockRequery(String iSBN, String name, String user, String compare, int inventory, String tag) {
		super();
		this.iSBN = iSBN;
		this.name = name;
		this.user = user;
		this.compare = compare;
		this.inventory = inventory;
		this.tag = tag;
	}

	public String getiSBN() {
		return iSBN;
	}

	public void setiSBN(String iSBN) {
		this.iSBN = iSBN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	
	
}
