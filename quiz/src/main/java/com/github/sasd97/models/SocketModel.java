package com.github.sasd97.models;

import javax.persistence.*;

/**
 * Created by Alexadner Dadukin on 1/1/2017.
 */

@Entity
@Table(name = "socket")
public class SocketModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long socketId;

    @ManyToOne
    private UserModel user;

    @Column(unique = true)
    private String session;

    public Long getSocketId() {
        return socketId;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SocketModel{" +
                "socketId=" + socketId +
                ", user=" + user +
                ", session='" + session + '\'' +
                '}';
    }
}
