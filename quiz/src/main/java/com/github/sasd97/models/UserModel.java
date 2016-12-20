package com.github.sasd97.models;

import com.github.sasd97.utils.DateUtils;

import javax.persistence.*;

/**
 * Created by alexander on 21.11.16.
 */

@Entity
@Table(name = "users")
public class UserModel {

    public enum Role {
        ADMIN,
        USER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String firstName;

    private String lastName;

    private String passwordHash;

    private String avatarUrl;

    @Column(unique = true)
    private String socialId;

    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private long registrationDate = DateUtils.timestamp();

    @Transient
    private String accessToken;

    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public long getRegistrationDate() {
        return registrationDate;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", socialId='" + socialId + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
