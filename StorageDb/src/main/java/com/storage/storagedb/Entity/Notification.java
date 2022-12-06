package com.storage.storagedb.Entity;

import javax.persistence.*;
@Entity
@Table(name="notifications")
public class Notification {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;
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
                ", isRead=" + isRead +
                ", value='" + value + '\'' +
                ", user=" + user +
                '}';
    }

    public Notification(User user, String value) {
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
