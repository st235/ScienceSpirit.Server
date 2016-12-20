package com.github.sasd97.models;

import javax.persistence.*;

/**
 * Created by alexander on 20.12.16.
 */

@Entity
@Table(name = "admin_secret_hash")
public class AdminSecretModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adminSecretHashId;

    private String secret;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "AdminSecretModel{" +
                "adminSecretHashId=" + adminSecretHashId +
                ", secret='" + secret + '\'' +
                '}';
    }
}
