package com.storage.storagedb.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agent")
@PrimaryKeyJoinColumn(name="id")
public class Agent extends User {

    @Column(name="salary")
    private Double salary;
    @Column(name="company")
    private String company;
    @Column (name="rating")
    private Double rating; // score 0-5

    @OneToMany(mappedBy = "agent")
    private List<Storage> storages = new ArrayList<>();

    public Double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "salary=" + salary +
                ", company='" + company + '\'' +
                ", rating=" + rating +
                "} " + super.toString();
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Agent() {
        super();
        this.salary = 0.0;
        this.company = "";
    }

    public Agent(String fname, String lname, String username, String email, String phone, Double salary, String company, String pass) {
        super(fname, lname, username, email, phone, pass);
        this.salary = salary;
        this.company = company;
    }

    public List<Storage> getStorages() {
        return storages;
    }

    public void setStorages(List<Storage> storages) {
        this.storages = storages;
    }
}