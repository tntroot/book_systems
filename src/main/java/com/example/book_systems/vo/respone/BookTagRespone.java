package com.example.book_systems.vo.respone;

import java.util.List;

import com.example.book_systems.entity.BookTag;

public class BookTagRespone {

	private String code;
	
	private String meassage;
	
	private List<BookTag> bookTags;

	public BookTagRespone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookTagRespone(String code, String meassage, List<BookTag> bookTags) {
		super();
		this.code = code;
		this.meassage = meassage;
		this.bookTags = bookTags;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMeassage() {
		return meassage;
	}

	public void setMeassage(String meassage) {
		this.meassage = meassage;
	}

	public List<BookTag> getBookTags() {
		return bookTags;
	}

	public void setBookTags(List<BookTag> bookTags) {
		this.bookTags = bookTags;
	}
	
	
	
	
}
