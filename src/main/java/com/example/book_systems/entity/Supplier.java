package com.example.book_systems.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "supplier")
public class Supplier {
	
	// 供應商編號
	@Id
	@Column(name = "serial_num")
	private String serial_num;
	
	// 廠商名稱
	@Column(name = "name")
	private String name;
	
	// 統編
	@Column(name = "compiled")
	private int compiled;
	
	// 信箱
	@Column(name = "email")
	private String email;
	
	// 電話
	@Column(name = "phone")
	private String phone;
	
	// 位置
	@Column(name = "location")
	private String location;

	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Supplier(String serial_num, String name, int compiled, String email, String phone, String location) {
		super();
		this.serial_num = serial_num;
		this.name = name;
		this.compiled = compiled;
		this.email = email;
		this.phone = phone;
		this.location = location;
	}

	public String getSerial_num() {
		return serial_num;
	}

	public void setSerial_num(String serial_num) {
		this.serial_num = serial_num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCompiled() {
		return compiled;
	}

	public void setCompiled(int compiled) {
		this.compiled = compiled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
