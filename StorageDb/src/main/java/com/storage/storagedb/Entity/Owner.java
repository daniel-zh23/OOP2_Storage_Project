package com.storage.storagedb.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name="id")
@Table(name="owner")
public class Owner extends User {

    @OneToMany(mappedBy = "owner")
    private List<Storage> storages = new ArrayList<>();

    public Owner(String fname,String lname,String username,String email,String phone, String password)
    {
        super(fname,lname,username,email,phone, password);
    }
    public Owner()
    {
        super();
    }


    public List<Storage> getStorages() { return this.storages; }

    public void setStorages(List<Storage> storageList) {
        this.storages = storageList;
    }

    @Override
    public String toString() {
        return "Owner" + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return true;
    }


}
