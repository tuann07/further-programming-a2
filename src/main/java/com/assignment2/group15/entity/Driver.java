package com.assignment2.group15.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String license;

    @Column
    private String phone;

    @Column
    private Double rating;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false, unique = true)
    private Car car;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @Column
    private ZonedDateTime dateCreated;

    public Driver() {
    }

    public Driver(Long id, String license, String phone, Double rating, Car car, List<Booking> bookings, ZonedDateTime dateCreated) {
        this.id = id;
        this.license = license;
        this.phone = phone;
        this.rating = rating;
        this.car = car;
        this.bookings = bookings;
        this.dateCreated = dateCreated;
    }

    public Driver(String license, String phone, Double rating, Car car, List<Booking> bookings, ZonedDateTime dateCreated) {
        this.license = license;
        this.phone = phone;
        this.rating = rating;
        this.car = car;
        this.bookings = bookings;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", license='" + license + '\'' +
                ", phone='" + phone + '\'' +
                ", rating=" + rating +
                ", car=" + car +
                ", bookings=" + bookings +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
