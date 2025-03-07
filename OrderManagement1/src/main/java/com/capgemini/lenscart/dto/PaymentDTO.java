package com.capgemini.lenscart.dto;


import jakarta.validation.constraints.*;
/*
public class PaymentDTO {


    private Long orderId;

    @NotNull(message = "Transaction ID cannot be null")
    //@NotBlank(message = "Transaction ID is required")
    @Size(min=1,max = 50, message = "Transaction ID must be less than 50 characters")
    private String transactionId;

    @NotBlank(message = "Payment type is required")
    @Size(min=1,max=30,message = "payment mode must be between 1 and 30 characters")
    private String paymentType;

    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount must be greater than or equal to 0")
    private Double amount;

    //@NotBlank(message = "Status is pending")
    @NotNull(message = "payment status is required")
    @Size(min=1,max = 20, message = "Payment status must be between 1 and 20 characters")
    private String status;

    @NotNull(message = "User ID is required")
    @Positive(message = "userId must be positive")

    private Long userId;

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;

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
    public Long getUserId() {
        return userId;

    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public PaymentDTO(Long orderId, String transactionId, String paymentType, Double amount, String status, Long userId) {
        super();
        this.orderId = orderId;
        this.transactionId = transactionId;
        this.paymentType = paymentType;
        this.amount = amount;
        this.status = status;
        this.userId = userId;
    }
    public PaymentDTO() {


    }
    // Getters and Setters
}
*/

public class PaymentDTO {

    @NotNull(message = "Order ID is required")
    @Positive(message = "Order ID must be positive")
    private Long orderId;

    @NotBlank(message = "Transaction ID is required")
    @Size(max = 50, message = "Transaction ID must be less than 50 characters")
    private String transactionId;

    @NotBlank(message = "Payment type is required")
    private String paymentType;

    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount must be greater than or equal to 0")
    private Double amount;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be positive")
    private Long userId;

    // Getters and Setters

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public PaymentDTO(Long orderId, String transactionId, String paymentType, Double amount, String status, Long userId) {
        super();
        this.orderId = orderId;
        this.transactionId = transactionId;
        this.paymentType = paymentType;
        this.amount = amount;
        this.status = status;
        this.userId = userId;
    }

    public PaymentDTO() {
    }
}