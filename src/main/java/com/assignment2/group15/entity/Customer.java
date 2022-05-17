package com.assignment2.group15.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table
public class Customer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phone;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @Column
    private ZonedDateTime dateCreated;

    public Customer() {
    }

    public Customer(Long id, String name, String address, String phone, List<Booking> bookings, ZonedDateTime dateCreated) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.bookings = bookings;
        this.dateCreated = dateCreated;
    }

    public Customer(String name, String address, String phone, List<Booking> bookings, ZonedDateTime dateCreated) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.bookings = bookings;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", bookings=" + bookings +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
