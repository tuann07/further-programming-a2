package com.assignment2.group15.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.ZonedDateTime;

@Entity
@Table
public class Car
{
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;

	@Column
	private Long VIN;
	
	@Column
	private String make;
	
	@Column
	private String model;
	
	@Column
	private String color;
	
	@Column
	private String convertible;
	
	@Column
	private String rating;
	
	@Column
	private String license;
	
	@Column
	private String rateperkm;
	
	@Column
	private ZonedDateTime dateCreated;
	
	public Car() {}
	
	public Car(Long id, Long VIN, String make, String model, String color, String convertible, String rating, String license, String rateperkm, ZonedDateTime dateCreated)
	{
		this.id=id;
		this.VIN=VIN;
		this.make=make;
		this.model=model;
		this.color=color;
		this.convertible=convertible;
		this.rating=rating;
		this.license=license;
		this.rateperkm=rateperkm;
		this.dateCreated=dateCreated;
	}
	
	public Car(Long VIN, String make, String model, String color, String convertible, String rating, String license, String rateperkm, ZonedDateTime dateCreated)
	{
		this.VIN=VIN;
		this.make=make;
		this.model=model;
		this.color=color;
		this.convertible=convertible;
		this.rating=rating;
		this.license=license;
		this.rateperkm=rateperkm;
		this.dateCreated=dateCreated;
	}

	public Long getId() 
	{ 
		return id;
	}
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public Long getVIN()
	{
		return VIN;
	}
	
	public void setVIN(Long VIN) 
	{ 
		this.VIN=VIN;
	}
	
	public String getMake() 
	{
		return make;
	}
	
	public void setMake(String make) 
	{
		this.make = make;
	}
	
	public String getModel() 
	{
		return model;
	}
	
	public void setModel(String model) 
	{
		this.model = model;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) 
	{
		this.color = color;
	}
	
	public String getConvertible() 
	{
		return convertible;
	}
	
	public void setConvertible(String convertible) 
	{
		this.convertible = convertible;
	}
	
	public String getRating() 
	{
		return rating;
	}
	
	public void setRating(String rating) 
	{
		this.rating = rating;
	}
	
	public String getLicense() 
	{
		return license;
	}
	
	public void setLicense(String license) 
	{
		this.license = license;
	}
	
	public String getRateperkm() 
	{
		return rateperkm;
	}
	
	public void setRateperkm(String rateperkm) 
	{
		this.rateperkm = rateperkm;
	}
	
	public ZonedDateTime getDateCreated() 
	{
		return dateCreated;
	}
	
	public void setDateCreated(ZonedDateTime dateCreated) 
	{
		this.dateCreated = dateCreated;
	}
	
	@Override
	public String toString() //return car with model, color, license & rate per km
	{
		return "Car {"+ "model = " + model +
				", color = " + color +
				", license = " + license +
				", rate per km = " + rateperkm + "|";
	}
}