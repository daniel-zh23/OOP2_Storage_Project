package src.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name="id")
@Table(name="owner")
public class Owner extends Users {
    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Storage> storages = new HashSet<Storage>();
    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Sales>sales;

    public Owner(String fname,String lname,String username,String email,String phone)
    {
        super(fname,lname,username,email,phone);
    }
    public Owner()
    {
        super();
    }

    public Set<Storage> getStorages() {
        return storages;
    }
    public void addStorage(Storage storage)
    {
        this.storages.add(storage);
    }

    public void setStorages(Set<Storage> storages) {
        this.storages = storages;
    }

    @Override
    public String toString() {
        String str="Owner{ storages=";
        for (Storage storage : storages)
        {
            str+= storage.toString();
            str+="\n";
        }
        return str+ "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Owner owner = (Owner) o;



        return true;
    }


}
