package src.Entity;

import javax.persistence.*;

@Entity
@Table(name = "agent")
@PrimaryKeyJoinColumn(name="id")
public class Agent extends Users {

    @Column(name="salary")
    private Double salary;
    @Column(name="company")
    private String company;


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

    public Agent(String fname, String lname, String username, String email, String phone, Double salary, String company) {
        super(fname, lname, username, email, phone);
        this.salary = salary;
        this.company = company;
    }

    @Override
    public String toString() {
        return "Agent{" +

                "salary=" + salary +
                ", company='" + company + '\'' +
                "} " + super.toString();
    }
}