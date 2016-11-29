package com.github.sasd97.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "authorization")
public class AuthorizationModel {

    @Expose
    @Column(nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Expose
    private String token;

    @Id
    @Expose
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long autorizationId;

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
        return autorizationId;
    }

    public void setUserId(Long autorizationId) {
        this.autorizationId = autorizationId;
    }
}

