package src.Entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name="id")
public class Admin extends User {
    public Admin() {
        super();
    }

    public Admin(String fname, String lname, String username, String email, String phone) {
        super(fname, lname, username, email, phone);
    }

    @Override
    public String toString() {
        return "Admin{} " + super.toString();
    }
}