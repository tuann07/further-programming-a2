package com.assignment2.group15.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table
public class Booking
{
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long bookID;
	
	@Column
	private String startLoc;
	
	@Column
	private String endLoc;
	
	@Column
	private Date pickup;
	
	@Column
	private Date drop;
	
	@Column
	private Long distance;
	
	@Column
	private Long cusID;
	
	@Column
	private Long charge;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invoice_id", nullable = false, unique = true)
	private Invoice invoice;
	
	@Column
	private ZonedDateTime dateCreated;

	public Booking() {}
	
	public Booking (Long bookID, String startLoc, String endLoc, Date pickup, Date drop, Long distance, Long cusID, Customer customer, Driver driver, Long charge, ZonedDateTime dateCreated)
	{
		this.bookID=bookID;
		this.startLoc=startLoc;
		this.endLoc=endLoc;
		this.pickup=pickup;
		this.drop=drop;
		this.distance=distance;
		this.cusID=cusID;
		this.charge=charge;
		this.dateCreated=dateCreated;
	}
	
	public Booking (String startLoc, String endLoc, Date pickup, Date drop, Long distance, Long cusID, Customer customer, Driver driver, Long charge, ZonedDateTime dateCreated)
	{

		this.startLoc=startLoc;
		this.endLoc=endLoc;
		this.pickup=pickup;
		this.drop=drop;
		this.distance=distance;
		this.cusID=cusID;
		this.charge=charge;
		this.dateCreated=dateCreated;
	}
	
	public Long getBookID() {
		return bookID;
	}

	public void setBookID(Long bookID) {
		this.bookID = bookID;
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

	public void setEnd(String endLoc) {
		this.endLoc = endLoc;
	}

	public Date getPickup() {
		return pickup;
	}

	public void setPickup(Date pickup) {
		this.pickup = pickup;
	}

	public Date getDrop() {
		return drop;
	}

	public void setDrop(Date drop) {
		this.drop = drop;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

	public Long getCusID() {
		return cusID;
	}

	public void setCusID(Long cusID) {
		this.cusID = cusID;
	}

	public Long getCharge() {
		return charge;
	}

	public void setCharge(Long charge) {
		this.charge = charge;
	}
	
	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() //return booking with id, start/end date & charge
	{
		return "Booking{" + "id = " + bookID +
				", start location = " + startLoc +
				", end location = " + endLoc +
				", charge = " + charge + "}";
	}
}