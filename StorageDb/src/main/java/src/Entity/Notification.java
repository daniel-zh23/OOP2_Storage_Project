package src.Entity;

import javax.persistence.*;
@Entity
@Table(name="notifications")
public class Notification {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private Users user;
    @Column(name="isread")
    private boolean isRead;
    @Column(name="value")
    private String value;

    public String getValue() {
        return value;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", user=" + user +
                ", isRead=" + isRead +
                ", value='" + value + '\'' +
                '}';
    }

    public Notification(Users user, String value) {
        this.user = user;
        this.value = value;
        this.isRead=false;
    }
    public Notification()
    {
        this.user=null;
        this.value=null;
        this.isRead=false;
    }

}
