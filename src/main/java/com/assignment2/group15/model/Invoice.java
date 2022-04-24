package com.assignment2.group15.model;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table
public class Invoice {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private double totalCharge;

//    private Customer customer;

//    private Driver driver;

    @Column
    private ZonedDateTime dateCreated;

    public Invoice() {
    }

    public Invoice(long id, double totalCharge, ZonedDateTime dateCreated) {
        this.id = id;
        this.totalCharge = totalCharge;
        this.dateCreated = dateCreated;
    }

    public Invoice(double totalCharge, ZonedDateTime dateCreated) {
        this.totalCharge = totalCharge;
        this.dateCreated = dateCreated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(double totalCharge) {
        this.totalCharge = totalCharge;
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
