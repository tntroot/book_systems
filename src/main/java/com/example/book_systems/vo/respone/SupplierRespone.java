package com.example.book_systems.vo.respone;

import java.util.List;

import com.example.book_systems.entity.Supplier;

public class SupplierRespone{

	private String code;
	
	private String message;
	
	private List<Supplier> suppliers;

	public SupplierRespone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SupplierRespone(String code, String message, List<Supplier> suppliers) {
		super();
		this.code = code;
		this.message = message;
		this.suppliers = suppliers;
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

	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}
	
	
}
