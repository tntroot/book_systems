package com.example.book_systems.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {

//	ISBN, book_name, user, price, sales, inventory, min_inventory, tag
	@Id
	@Column(name = "ISBN")
	private String iSBN;
	
	@Column(name = "book_name")
	private String book_name;
	
	@Column(name = "user")
	private String user;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "sales")
	private int sales;
	
	@Column(name = "inventory")
	private int inventory;
	
	@Column(name = "min_inventory")
	private int min_inventory;
	
	@Column(name = "tag")
	private String tag;

	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stock(String iSBN, String book_name, String user, int price, int sales, int inventory, int min_inventory,
			String tag) {
		super();
		this.iSBN = iSBN;
		this.book_name = book_name;
		this.user = user;
		this.price = price;
		this.sales = sales;
		this.inventory = inventory;
		this.min_inventory = min_inventory;
		this.tag = tag;
	}

	public String getiSBN() {
		return iSBN;
	}

	public void setiSBN(String iSBN) {
		this.iSBN = iSBN;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getMin_inventory() {
		return min_inventory;
	}

	public void setMin_inventory(int min_inventory) {
		this.min_inventory = min_inventory;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
	
}
