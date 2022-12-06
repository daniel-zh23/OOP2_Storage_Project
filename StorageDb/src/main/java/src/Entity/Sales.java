package src.Entity;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="sales")
public class Sales {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="price")
    private Double price;
    @Column (name="duration") // in months
    private Integer duration;
    @Column (name="date")
    private Date dateOfSale;
    @ManyToOne
    @JoinColumn(name="owner_id",nullable = false)
    private Owner owner;
    @ManyToOne
    @JoinColumn(name="agent_id",nullable = false)
    private Agent agent;
    @ManyToOne
    @JoinColumn(name="renter_id",nullable = false)
    private Renter renter;
}
