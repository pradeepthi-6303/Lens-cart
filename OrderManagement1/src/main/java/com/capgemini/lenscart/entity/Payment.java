package com.capgemini.lenscart.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
/*
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private String transactionId;
    private String paymentType;
    private Double amount;

    //@Enumerated(EnumType.STRING)
    private String status;
    private Long userId;
    private LocalDateTime generatedTime;
    // Getters and Setters


    public Payment(Long id, Long orderId, String paymentType, Double amount, String status, Long userId, String transactionId, LocalDateTime generatedTime) {
        super();
        this.amount = amount;
        this.orderId = orderId;
        this.paymentType = paymentType;
        this.status = status;
        this.id = id;
        this.userId = userId;
        this.transactionId = transactionId;


        this.generatedTime = generatedTime;
    }

    public Payment(LocalDateTime generatedTime) {

        this.generatedTime = generatedTime;
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

    public void setGeneratedTime(LocalDateTime now) {
    }


    public LocalDateTime getGeneratedTime() {
        return null;
    }
}
*/
/*
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false, unique = true)
    private String transactionId;

    @Column(nullable = false)
    private String paymentType;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String status; // SUCCESS or CANCELED (we are not using REFUNDED here)

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDateTime generatedTime;

    @Column(nullable = false)
    private boolean orderCanceled; // Track if the order is canceled internally

    public Payment() {

    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(LocalDateTime generatedTime) {
        this.generatedTime = generatedTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isOrderCanceled() {
        return orderCanceled;
    }

    public void setOrderCanceled(boolean orderCanceled) {
        this.orderCanceled = orderCanceled;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    // Getters and Setters
}
*/

/*
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false, unique = true)
    private String transactionId;

    @Column(nullable = false)
    private String paymentType;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String status;

    private LocalDateTime generatedTime;
    private String message;
    private Boolean canceled = false; // default value is false.
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(LocalDateTime generatedTime) {
        this.generatedTime = generatedTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }
}
*//*
 @Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private String transactionId;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private Double amount;
    private String status;

    private Long userId;
    private LocalDateTime generatedTime;

    public Payment() {
        // Default constructor
    }

    public Payment(Long orderId, String transactionId, PaymentType paymentType, Double amount, Long userId) {
        this.orderId = orderId;
        this.transactionId = transactionId;
        this.paymentType = paymentType;
        this.amount = amount;
        this.userId = userId;
        this.generatedTime = LocalDateTime.now();
        this.status = determineStatus();
    }

    private String determineStatus() {
        // Just a basic implementation to start with
        return amount > 0 ? "SUCCESS" : "FAILURE";
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
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

    public LocalDateTime getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(LocalDateTime generatedTime) {
        this.generatedTime = generatedTime;
    }
}
*/
/*
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private String transactionId;
    private String paymentType;
    private Double amount;
    private String status;  // No default value
    private Long userId;
    private LocalDateTime generatedTime = LocalDateTime.now();

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(LocalDateTime generatedTime) {
        this.generatedTime = generatedTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getUserId() {
     //   return userId;
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
// Getters and Setters
}
*/
/*
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int itemId; // Changed from orderId to itemId
    private String transactionId;
    private String paymentType;
    private Double amount;
    private String status;  // No default value
    private Long userId;
    private LocalDateTime generatedTime = LocalDateTime.now();

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(LocalDateTime generatedTime) {
        this.generatedTime = generatedTime;
    }


}*/

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int itemId;
    private String transactionId;
    private String paymentType;
    private Double amount;
    private String status;
    private Long userId;
    private LocalDateTime generatedTime = LocalDateTime.now();

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(LocalDateTime generatedTime) {
        this.generatedTime = generatedTime;
    }
}