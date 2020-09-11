package com.example.genericMs.bean;

public class PersonBean {
	private String name;
	private String dob;
    private String company;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public PersonBean(String name, String dob, String company) {
		super();
		this.name = name;
		this.dob = dob;
		this.company = company;
	}
    
    
	

}
