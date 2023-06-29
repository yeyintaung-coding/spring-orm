package com.jdc.location.entity;

public enum Type {
	
	State("State"),Region("Region"),Union("Union Territory");
	
	private String value;
	private Type(String value) {
		this.value=value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
