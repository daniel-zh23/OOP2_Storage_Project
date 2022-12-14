package com.storage.storagedb.Entity;

import javax.persistence.*;

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

    public Agent(String fname, String lname, String username, String email, String phone, Double salary, String company, String password) {
        super(fname, lname, username, email, phone, password);
        this.salary = salary;
        this.company = company;
    }

}