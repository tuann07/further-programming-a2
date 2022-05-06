package com.assignment2.group15.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table
public class Invoice {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double totalCharge;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Driver driver;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "invoice")
    private Booking booking;

    @Column
    private LocalDate pickUpTime;

    @Column
    private LocalDate dropOffTime;

    @Column
    private ZonedDateTime dateCreated;

    public Invoice() {
    }

    public Invoice(Long id, Double totalCharge, LocalDate pickUpTime, LocalDate dropOffTime, ZonedDateTime dateCreated) {
        this.id = id;
        this.totalCharge = totalCharge;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
        this.dateCreated = dateCreated;
    }

    public Invoice(Double totalCharge, LocalDate pickUpTime, LocalDate dropOffTime, ZonedDateTime dateCreated) {
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

    public LocalDate getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalDate pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public LocalDate getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(LocalDate dropOffTime) {
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