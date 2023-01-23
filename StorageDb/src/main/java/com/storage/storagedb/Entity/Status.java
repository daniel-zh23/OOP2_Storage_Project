package com.storage.storagedb.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name="id")
@Table(name="status")
public class Status {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column (name="name")
    private String name;

    public Status() {}

    public Status(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "status")
    private List<Storage> storages = new ArrayList<>();

    public List<Storage> getStorages() {
        return storages;
    }

    public void setStorages(List<Storage> storages) {
        this.storages = storages;
    }

    public String getName() {
        return name;
    }
}
