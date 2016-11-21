package com.github.sasd97.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;

/**
 * Created by alexander on 21.11.16.
 */

@Entity
@Table(name = "USER")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SerializedName("id")
    @Expose
    private Long id;

    private String firstName;
    private String lastName;

    protected UserModel() {}

    public UserModel(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}
