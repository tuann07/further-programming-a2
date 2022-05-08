package com.assignment2.group15.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
@Table
public class Booking
{
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String startLoc;
	
	@Column
	private String endLoc;
	
	@Column
	private LocalDate pickup;
	
	@Column
	private LocalDate drop;
	
	@Column
	private Long distance;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore()
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore()
	private Driver driver;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "booking")
	@JsonIgnore()
	private Invoice invoice;
	
	@Column
	private ZonedDateTime dateCreated;

	public Booking() {}

	public Booking(Long id, String startLoc, String endLoc, LocalDate pickup, LocalDate drop, Long distance, Customer customer, Driver driver, Invoice invoice, ZonedDateTime dateCreated) {
		this.id = id;
		this.startLoc = startLoc;
		this.endLoc = endLoc;
		this.pickup = pickup;
		this.drop = drop;
		this.distance = distance;
		this.dateCreated = dateCreated;
	}

	public Booking(String startLoc, String endLoc, LocalDate pickup, LocalDate drop, Long distance, Customer customer, Driver driver, Invoice invoice, ZonedDateTime dateCreated) {
		this.startLoc = startLoc;
		this.endLoc = endLoc;
		this.pickup = pickup;
		this.drop = drop;
		this.distance = distance;
		this.customer = customer;
		this.driver = driver;
		this.invoice = invoice;
		this.dateCreated = dateCreated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStartLoc() {
		return startLoc;
	}

	public void setStartLoc(String startLoc) {
		this.startLoc = startLoc;
	}

	public String getEndLoc() {
		return endLoc;
	}

	public void setEndLoc(String endLoc) {
		this.endLoc = endLoc;
	}

	public LocalDate getPickup() {
		return pickup;
	}

	public void setPickup(LocalDate pickup) {
		this.pickup = pickup;
	}

	public LocalDate getDrop() {
		return drop;
	}

	public void setDrop(LocalDate drop) {
		this.drop = drop;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "Booking{" +
				"id=" + id +
				", startLoc='" + startLoc + '\'' +
				", endLoc='" + endLoc + '\'' +
				", pickup=" + pickup +
				", drop=" + drop +
				", distance=" + distance +
				", customer=" + customer +
				", driver=" + driver +
				", invoice=" + invoice +
				", dateCreated=" + dateCreated +
				'}';
	}
}