package com.example.book_systems.vo.respone;

import java.util.List;
import com.example.book_systems.entity.Location;

public class LocationRespone {

	private String code;
	
	private String mesage;
	
	private List<Location> locations;

	public LocationRespone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocationRespone(String code, String mesage, List<Location> locations) {
		super();
		this.code = code;
		this.mesage = mesage;
		this.locations = locations;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMesage() {
		return mesage;
	}

	public void setMesage(String mesage) {
		this.mesage = mesage;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	
	
}
