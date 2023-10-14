package com.example.book_systems.vo.requery;

public class SupplierSearchRequery {

	private String name;
	
	private Integer compiled;
	
	private String city;

	public SupplierSearchRequery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SupplierSearchRequery(String name, Integer compiled, String city) {
		super();
		this.name = name;
		this.compiled = compiled;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCompiled() {
		return compiled;
	}

	public void setCompiled(Integer compiled) {
		this.compiled = compiled;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
}
