package src.Entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="users")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Basic
    @Column(name = "username", nullable = false, length = 20,unique = true)
    private String username;
    @Basic
    @Column(name = "FirstName", nullable = true, length = 20)
    private String firstName;
    @Basic
    @Column(name = "LastName", nullable = true, length = 20)
    private String lastName;
    @Basic
    @Column(name = "email", nullable = true, length = 20)
    private String email;
    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    private String phone;
    @Basic
    @Column(name="password",nullable = true)
    private String password;




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public User()
    {
        this.firstName=null;
        this.lastName=null;
        this.username=null;
        this.email=null;
        this.phone=null;
        this.password=null;
    }
    public User(String fname, String lname, String username, String email, String phone)
    {
        this.firstName=fname;
        this.lastName=lname;
        this.username=username;
        this.email=email;
        this.phone=phone;
        this.password=null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, lastName, email, phone, password);
    }
}
