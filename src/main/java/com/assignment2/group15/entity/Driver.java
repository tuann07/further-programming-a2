package com.assignment2.group15.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String start;

    @Column
    private String end;

    @Column
    private Date pickup;

    @Column
    private Date drop;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false, unique = true)
    private Car car;

    @OneToMany(mappedBy = "driver", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Invoice> invoices;

    public Driver() {
    }

    public Driver(Long id, String name, String start, String end, Date pickup, Date drop) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.pickup = pickup;
        this.drop = drop;
    }

    public Driver(String name, String start, String end, Date pickup, Date drop) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.pickup = pickup;
        this.drop = drop;
    }

    public Driver(String name) {
        this.name = name;
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

    public String getStart() { return start; }

    public void setStart(String start) { this.start = start; }

    public String getEnd() { return end; }

    public void setEnd(String end) { this.end = end; }

    public Date getPickup() { return pickup; }

    public void setPickup(Date pickup) { this.pickup = pickup; }

    public Date getDrop() { return drop; }

    public void setDrop(Date drop) { this.drop = drop; }

    @Override
    public String toString()
    {
        return "Customer {"+ "id=" + id +
                ", name = " + name +
                ", start date = " + start +
                ", end date = " + end +"|";
    }
}
