package com.assignment2.group15.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private String start;

    @Column
    private String end;

    public Customer() {
    }

    public Customer(long id, String name, String address, String phone, String start, String end) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.start = start;
        this.end = end;
    }

    public Customer(String name, String address, String phone, String start, String end) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.start = start;
        this.end = end;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setPhone(String phone) { this.phone = phone; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStart() { return start; }

    public void setStart(String start) { this.start = start; }

    public String getEnd() { return end; }

    public void setEnd(String end) { this.end = end; }

    @Override
    public String toString()
    {
        return "Customer {"+ "id=" + id +
                ", name = " + name +
                ", address = " + address +
                ", phone = " + phone +
                ", start = " + start +
                ", end = " + end +"|";
    }
}
