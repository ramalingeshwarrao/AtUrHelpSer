package com.aturhelp.common;

public enum Category {

	GENERAL("GEN"),
	ALTERNATE("ALT");
	
	private String category;
	
	Category(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}
}
