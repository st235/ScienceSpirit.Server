package com.github.sasd97.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "authorization")
public class AuthorizationModel {

    @Id
    @Expose
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long authorizationId;

    @ManyToOne
    private UserModel user;

    @Expose
    @Column(nullable = false)
    private String token;

    @Expose
    @Column(nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    public AuthorizationModel() {}

    public AuthorizationModel(UserModel user) {
        this.user = user;
    }


    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public Long getUserId() {
        return authorizationId;
    }

    public void setUserId(Long authorizationId) {
        this.authorizationId = authorizationId;
    }

    public Long getAuthorizationId() {
        return authorizationId;
    }

    public void setAuthorizationId(Long authorizationId) {
        this.authorizationId = authorizationId;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AuthorizationModel{" +
                "authorizationId=" + authorizationId +
                ", user=" + user +
                ", token='" + token + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}

