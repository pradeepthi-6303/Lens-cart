package com.capgemini.lenscart.dto;


import jakarta.validation.constraints.*;


public class PaymentDTO {



    @NotNull(message = "{itemId}")
    private int itemId;

    @NotNull(message = "{transactionId}")
    @Size(min = 5, max = 50, message = "{transactionId}")
    private String transactionId;

    @NotNull(message = "{paymentType.null}")
    @Pattern(regexp = "(?i)^(NetBanking|DebitCard|CreditCard)$", message = "{paymentType.invalid}")
    private String paymentType;

    @Positive(message = "{amount}")
    private Double amount;



    private String status;

    // Getters and Setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}