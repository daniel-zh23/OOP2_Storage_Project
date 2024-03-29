package com.storage.storagedb.Entity;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate dateOfSale;
    @Column (name="active")
    private boolean active=true;
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Storage.class)
    @JoinColumn(name = "storage_id", insertable = false, updatable = false)
    private Storage storage;
    @Column(name="storage_id")
    private Integer storageId;
    @ManyToOne(fetch=FetchType.EAGER, targetEntity=Agent.class)
    @JoinColumn(name="agent_id", nullable = false, insertable = false, updatable = false)
    private Agent agent;
    @Column(name = "agent_id")
    private Integer agentId;
    @ManyToOne(fetch=FetchType.EAGER, targetEntity= Customer.class)
    @JoinColumn(name="customer_id", nullable = false, insertable = false, updatable = false)
    private Customer customer;
    @Column(name = "customer_id")
    private Integer customerId;

    public Sales() {
    }

    public Sales(Double price, Integer duration, LocalDate dateOfSale, Integer storageId, Integer agentId, Integer renterId) {
        this.price = price;
        this.duration = duration;
        this.dateOfSale = dateOfSale;
        this.storageId = storageId;
        this.agentId = agentId;
        this.customerId = renterId;
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

    public LocalDate getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(LocalDate dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Customer getRenter() {
        return customer;
    }

    public void setRenter(Customer customer) {
        this.customer = customer;
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer renterId) {
        this.customerId = renterId;
    }
}
