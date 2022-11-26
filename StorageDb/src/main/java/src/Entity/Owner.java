package src.Entity;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name="id")
@Table(name="owner")
public class Owner extends Users {

    public Owner(String fname,String lname,String username,String email,String phone)
    {
        super(fname,lname,username,email,phone);
    }
    public Owner()
    {
        super();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Owner owner = (Owner) o;



        return true;
    }


    @Override
    public String toString() {
        return "Owner{" +
                "} " + super.toString();
    }
}
