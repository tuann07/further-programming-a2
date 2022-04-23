package com.assignment2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Car
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long VIN;
	private String make;
	private String model;
	private String color;
	private String convertible;
	private String rating;
	private String license;
	private String rateperkm;
	private Date dateCreated;
	
	protected Car() {}
	
	public Car(String make, String model, String color, String convertible, String rating, String license, String rateperkm, Date dateCreated)
	{
		this.make=make;
		this.model=model;
		this.color=color;
		this.convertible=convertible;
		this.rating=rating;
		this.license=license;
		this.rateperkm=rateperkm;
		this.dateCreated=dateCreated;
	}
	
	@Override
	public String toString()
	{
		return String.format("Car[ VIN=%d, make='%s', model='%s', color='%s', convertible='%s', rating='%s', license='%s', rate per km='%s']",VIN, make, model, color, convertible, rating, license, rateperkm);
	}
	
	public Long getVIN()
	{
		return VIN;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getConvertible() {
		return convertible;
	}

	public void setConvertible(String convertible) {
		this.convertible = convertible;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getRateperkm() {
		return rateperkm;
	}

	public void setRateperkm(String rateperkm) {
		this.rateperkm = rateperkm;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
}