package com.banking.banking.model;
import com.banking.banking.model.enums.AccountType;

import javax.persistence.*;


@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private AccountType accountType;

    private String nickname;

    private Integer rewards;

    private double balance;


    @ManyToOne
    @JoinColumn(name = "customer_Id", nullable = false)
    private Customer customer;


    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getRewards() {
        return rewards;
    }

    public void setRewards(Integer rewards) {
        this.rewards = rewards;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountType=" + accountType +
                ", nickname='" + nickname + '\'' +
                ", rewards=" + rewards +
                ", balance=" + balance +
                ", customer=" + customer +
                '}';
    }
}
