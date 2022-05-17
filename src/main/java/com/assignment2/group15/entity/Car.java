package com.assignment2.group15.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table
public class Car
{
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long carID;

	@Column
	private String vin;
	
	@Column
	private String make;
	
	@Column
	private String model;
	
	@Column
	private String color;
	
	@Column
	private String convertible;
	
	@Column
	private Double rating;
	
	@Column
	private String license;
	
	@Column
	private Double ratePerKm;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "car")
	private Driver driver;

	@Column
	private ZonedDateTime dateCreated;
	
	public Car() {}

	public Car(Long carID, String vin, String make, String model, String color, String convertible, Double rating, String license, Double ratePerKm, Driver driver, ZonedDateTime dateCreated) {
		this.carID = carID;
		this.vin = vin;
		this.make = make;
		this.model = model;
		this.color = color;
		this.convertible = convertible;
		this.rating = rating;
		this.license = license;
		this.ratePerKm = ratePerKm;
		this.driver = driver;
		this.dateCreated = dateCreated;
	}

	public Car(String vin, String make, String model, String color, String convertible, Double rating, String license, Double ratePerKm, Driver driver, ZonedDateTime dateCreated) {
		this.vin = vin;
		this.make = make;
		this.model = model;
		this.color = color;
		this.convertible = convertible;
		this.rating = rating;
		this.license = license;
		this.ratePerKm = ratePerKm;
		this.driver = driver;
		this.dateCreated = dateCreated;
	}

	public Long getcarID() {
		return carID;
	}

	public void setcarID(Long carID) {
		this.carID = carID;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
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

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public Double getRatePerKm() {
		return ratePerKm;
	}

	public void setRatePerKm(Double ratePerKm) {
		this.ratePerKm = ratePerKm;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "Car{" +
				"id=" + carID +
				", vin='" + vin + '\'' +
				", make='" + make + '\'' +
				", model='" + model + '\'' +
				", color='" + color + '\'' +
				", convertible='" + convertible + '\'' +
				", rating=" + rating +
				", license='" + license + '\'' +
				", ratePerKm=" + ratePerKm +
				", driver=" + driver +
				", dateCreated=" + dateCreated +
				'}';
	}
}