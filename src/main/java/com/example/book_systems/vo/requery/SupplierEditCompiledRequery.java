package com.example.book_systems.vo.requery;

import com.example.book_systems.entity.Supplier;

public class SupplierEditCompiledRequery {

	private Supplier supplier;
	
	private Integer oldCompiled;
	
	private Integer newCompiled;

	public SupplierEditCompiledRequery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SupplierEditCompiledRequery(Supplier supplier, Integer oldCompiled, Integer newCompiled) {
		super();
		this.supplier = supplier;
		this.oldCompiled = oldCompiled;
		this.newCompiled = newCompiled;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Integer getOldCompiled() {
		return oldCompiled;
	}

	public void setOldCompiled(Integer oldCompiled) {
		this.oldCompiled = oldCompiled;
	}

	public Integer getNewCompiled() {
		return newCompiled;
	}

	public void setNewCompiled(Integer newCompiled) {
		this.newCompiled = newCompiled;
	}
	
	
}