package com.storage.storagedb.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column (name="width")
    private Double width;
    @Column(name="length")
    private Double length;
    @Column(name="height")
    private Double height;
    @Column (name="address")
    private String address;

    @Column (name="is_active")
    private boolean isActive = true;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
    @JoinColumn(name = "status_id", insertable = false, updatable = false) // Free / Leased / For Lease 0/1/2
    private Status status;

    @Column(name = "status_id")
    private Integer statusId;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Owner.class)
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private Owner owner;

    @Column(name = "owner_id")
    private Integer ownerId;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "agent_storage",
            joinColumns = { @JoinColumn(name = "storage_id") },
            inverseJoinColumns = { @JoinColumn(name = "agent_id") }
    )
    private Set<Agent> agents = new HashSet<>();

    public Storage( Double width, Double length, Double height, String address, int status) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.address = address;
        this.statusId = status;
    }

    public Storage( Double width, Double length, Double height, String address, int status, int owner) {
        this(width, length, height, address, status);
        this.ownerId = owner;
    }

    public Storage()
    {
        this.width = 0.0;
        this.length = 0.0;
        this.height = 0.0;
        this.address = null;
        this.status = null;
    }

    public Set<Agent> getAgents() {
        return agents;
    }

    public void setAgents(Set<Agent> agents) {
        this.agents = agents;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public void setStatusId(int id)
    {
        this.statusId=id;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", width=" + width +
                ", length=" + length +
                ", height=" + height +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }
}