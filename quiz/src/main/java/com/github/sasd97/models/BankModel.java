package com.github.sasd97.models;

import javax.persistence.*;

/**
 * Created by Alexadner Dadukin on 12/29/2016.
 */

@Entity
@Table(name = "bank")
public class BankModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long walletId;

    @ManyToOne
    private UserModel user;

    private Long balance = 100L;

    public long increase(long value) {
        this.balance += value;
        return balance;
    }

    public long decrease(long value) {
        this.balance -= value;
        return balance;
    }

    public Long getWalletId() {
        return walletId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public UserModel getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "BankModel{" +
                "walletId=" + walletId +
                ", user=" + user +
                ", balance=" + balance +
                '}';
    }
}
