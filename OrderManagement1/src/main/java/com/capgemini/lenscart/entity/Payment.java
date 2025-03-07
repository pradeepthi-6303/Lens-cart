package com.capgemini.lenscart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private String transactionId;
    private String paymentType;
    private Double amount;
    private String status;
    private Long userId;

    // Getters and Setters


    public Payment(Long id,Long orderId, String paymentType,Double amount, String status, Long userId,String transactionId) {
        super();
        this.amount = amount;
        this.orderId = orderId;
        this.paymentType = paymentType;
        this.status = status;
        this.id = id;
        this.userId = userId;
        this.transactionId = transactionId;

    }

    public Payment() {

    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getTransactionId() {
        return transactionId;

    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}