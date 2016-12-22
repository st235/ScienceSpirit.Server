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

