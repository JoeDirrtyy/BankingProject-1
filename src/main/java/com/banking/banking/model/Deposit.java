package com.banking.banking.model;

import com.banking.banking.model.enums.Medium;
import com.banking.banking.model.enums.Status;
import com.banking.banking.model.enums.TransferType;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@Entity
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TransferType transferType;

    private String transaction_date;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    // many deposits to one account
    private Medium medium;
    private Double amount;
    private String description;

    public TransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", transferType=" + transferType +
                ", transaction_date='" + transaction_date + '\'' +
                ", status=" + status +
                ", account=" + account +
                ", medium=" + medium +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
