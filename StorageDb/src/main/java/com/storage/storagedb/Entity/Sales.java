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
    @Column (name="active")
    private boolean active=true;
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Owner.class)
    @JoinColumn(name = "storage_id", insertable = false, updatable = false)
    private Storage storage;
    @Column(name="storage_id")
    private Integer storageId;
    @ManyToOne(fetch=FetchType.EAGER,targetEntity=Agent.class)
    @JoinColumn(name="agent_id",nullable = false)
    private Agent agent;
    @Column
    private Integer agentId;
    @ManyToOne(fetch=FetchType.EAGER,targetEntity=Renter.class)
    @JoinColumn(name="renter_id",nullable = false)
    private Renter renter;
    @Column
    private Integer renterId;

    public Sales(Double price, Integer duration, Date dateOfSale, Integer storageId, Integer agentId, Integer renterId) {
        this.price = price;
        this.duration = duration;
        this.dateOfSale = dateOfSale;
        this.storageId = storageId;
        this.agentId = agentId;
        this.renterId = renterId;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStorageId() {
        return storageId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getRenterId() {
        return renterId;
    }

    public void setRenterId(Integer renterId) {
        this.renterId = renterId;
    }
}
