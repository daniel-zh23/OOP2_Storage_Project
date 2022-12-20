package com.storage.storagedb.Entity;

import javax.persistence.*;
@Entity
@Table(name="renter")
public class Renter {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;



}
