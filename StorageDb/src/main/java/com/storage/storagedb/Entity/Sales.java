package com.storage.storagedb.Entity;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="sales")
public class Sales {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="price")
    private Double price;
    @Column (name="duration") // in months
    private Integer duration;
    @Column (name="date")
    private Date dateOfSale;
    @ManyToOne
    @JoinColumn(name="owner_id",nullable = false)
    private Owner owner;
    @ManyToOne
    @JoinColumn(name="agent_id",nullable = false)
    private Agent agent;
    @ManyToOne
    @JoinColumn(name="renter_id",nullable = false)
    private Renter renter;

    public Sales(Double price, Integer duration, Date dateOfSale, Owner owner, Agent agent, Renter renter) {
        this.price = price;
        this.duration = duration;
        this.dateOfSale = dateOfSale;
        this.owner = owner;
        this.agent = agent;
        this.renter = renter;
    }

    public Integer getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(Date dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }


}
