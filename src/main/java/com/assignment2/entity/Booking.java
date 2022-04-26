package com.assignment2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class Booking
{
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String start;
	
	@Column
	private String end;
	
	@Column
	private Date pickup;
	
	@Column
	private Date drop;
	
	@Column
	private Long distance;
	
	@Column
	private Long cusID;
	
	@Column
	private String customer;
	
	@Column
	private String driver;
	
	@Column
	private Long charge;
	
	protected Booking() {}
	
	public Booking (String start, String end, Date pickup, Date drop, Long distance, Long cusID, String customer, String driver, Long charge)
	{
		this.start=start;
		this.end=end;
		this.pickup=pickup;
		this.drop=drop;
		this.distance=distance;
		this.cusID=cusID;
		this.customer=customer;
		this.driver=driver;
		this.charge=charge;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
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

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public Long getCharge() {
		return charge;
	}

	public void setCharge(Long charge) {
		this.charge = charge;
	}
	
	@Override
	public String toString() //return booking with id, start/end date & charge
	{
		return "Booking{" + "id = " + id +
				", start date = " + start +
				", end date = " + end +
				", charge = " + charge + "}";
	}
}