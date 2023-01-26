package com.storage.storagedb.Entity;

import javax.persistence.*;
@Entity
@Table(name="notifications")
public class Notification {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name="user_id", insertable = false, updatable = false, nullable = false)
    private User user;

    @Column(name = "user_id")
    private Integer userId;

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

    public boolean getRead() {
        return isRead;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public User getUser(){return this.user;}
    public Integer getUserId(){return this.user.getId();}


    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", isRead=" + isRead +
                ", value='" + value + '\'' +
                ", user=" + user +
                '}';
    }

    public Notification(int userId, String value) {
        this.userId = userId;
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
