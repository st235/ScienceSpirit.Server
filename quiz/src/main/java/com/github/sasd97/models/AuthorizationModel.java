package com.github.sasd97.models;

import javax.persistence.Entity;
import javax.persistence.Table;

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
    private Long userId;

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
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

