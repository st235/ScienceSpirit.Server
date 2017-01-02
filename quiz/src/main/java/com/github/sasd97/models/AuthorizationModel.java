package com.github.sasd97.models;

import com.github.sasd97.utils.DateUtils;
import com.github.sasd97.utils.HashUtils;

import javax.persistence.*;

@Entity
@Table(name = "authorization")
public class AuthorizationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long authorizationId;

    @ManyToOne
    private UserModel user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private long creationDate = DateUtils.timestamp();

    @Column(nullable = false)
    private long expirationDate = DateUtils.fromNow(7);

    public AuthorizationModel() {}

    public AuthorizationModel(UserModel user) {
        this.user = user;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String data) {
        this.token = HashUtils.md5(HashUtils.randomData(data));
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

    public Long getUserId() {
        return user.getUserId();
    }

    public long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "AuthorizationModel{" +
                "authorizationId=" + authorizationId +
                ", user=" + user +
                ", token='" + token + '\'' +
                ", creationDate=" + creationDate +
                ", expirationDate=" + expirationDate +
                '}';
    }
}

