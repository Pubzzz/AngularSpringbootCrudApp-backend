package com.example.SpringbootCRUDapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="Countries")
public class Country {

	//Declaring Variables
	private long id;
	@NotNull
	private String CountryName;
	
	//Default Constructor without parameters
	public Country() {
		
	}
	
	//Default Constructor with parameters
	public Country(String countryName) {
		this.CountryName=countryName;
	}
	
	//Auto-Generated ID
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id=id;
	}
	
	@Column(name="country_name",nullable=false)
	public String getCountryName() {
		return CountryName;
	}
	public void setCountryName(String countryName) {
		this.CountryName=countryName;
	}
}
