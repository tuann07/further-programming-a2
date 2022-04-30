package com.assignment2.group15.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table
public class Invoice {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double totalCharge;

//    private Customer customer;

//    private Driver driver;

    @Column
    private ZonedDateTime pickUpTime;

    @Column
    private ZonedDateTime dropOffTime;

    @Column
    private ZonedDateTime dateCreated;

    public Invoice() {
    }

    public Invoice(Long id, Double totalCharge, ZonedDateTime pickUpTime, ZonedDateTime dropOffTime, ZonedDateTime dateCreated) {
        this.id = id;
        this.totalCharge = totalCharge;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
        this.dateCreated = dateCreated;
    }

    public Invoice(Double totalCharge, ZonedDateTime pickUpTime, ZonedDateTime dropOffTime, ZonedDateTime dateCreated) {
        this.totalCharge = totalCharge;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(Double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public ZonedDateTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(ZonedDateTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public ZonedDateTime getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(ZonedDateTime dropOffTime) {
        this.dropOffTime = dropOffTime;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", totalCharge=" + totalCharge +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
