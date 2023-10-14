package com.example.book_systems.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location {

	// 位置ID
	@Id
	@Column(name = "location_id")
	private String location_id;
	
	// 位置
	@Column(name = "location_name")
	private String location_name;

	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Location(String location_id, String location_name) {
		super();
		this.location_id = location_id;
		this.location_name = location_name;
	}

	public String getLocation_id() {
		return location_id;
	}

	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	
	
}
