package com.github.sasd97.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by alexander on 21.11.16.
 */

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @Expose
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    @Column(unique = true)
    private String socialId;

    @Expose
    @Column(nullable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();

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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
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
