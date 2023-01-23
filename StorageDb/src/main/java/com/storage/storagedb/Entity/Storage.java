package com.storage.storagedb.Entity;

import javax.persistence.*;

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
    @Column (name="status") // Free / Leased / For Lease 0/1/2
    private Integer status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ownerId")
    private Owner owner;

    public Storage( Double width, Double length, Double height, String address, Integer status,Owner owner) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.address = address;
        this.status = status;
        this.owner=owner;
    }
    public Storage()
    {
        this.width = 0.0;
        this.length = 0.0;
        this.height = 0.0;
        this.address = null;
        this.status = 0;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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