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
/*
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
*/
/*
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

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be positive")
    private Long userId;

    public PaymentDTO(Double amount, Long orderId, String paymentType, String transactionId, Long userId) {
        super();
        this.amount = amount;
        this.orderId = orderId;
        this.paymentType = paymentType;
        this.transactionId = transactionId;
        this.userId = userId;
    }

    public @NotNull(message = "Amount is required") @Min(value = 0, message = "Amount must be greater than or equal to 0") Double getAmount() {
        return amount;
    }

    public void setAmount(@NotNull(message = "Amount is required") @Min(value = 0, message = "Amount must be greater than or equal to 0") Double amount) {
        this.amount = amount;
    }

    public @NotNull(message = "Order ID is required") @Positive(message = "Order ID must be positive") Long getOrderId() {
        return orderId;
    }

    public void setOrderId(@NotNull(message = "Order ID is required") @Positive(message = "Order ID must be positive") Long orderId) {
        this.orderId = orderId;
    }

    public @NotBlank(message = "Payment type is required") String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(@NotBlank(message = "Payment type is required") String paymentType) {
        this.paymentType = paymentType;
    }

    public @NotBlank(message = "Transaction ID is required") @Size(max = 50, message = "Transaction ID must be less than 50 characters") String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(@NotBlank(message = "Transaction ID is required") @Size(max = 50, message = "Transaction ID must be less than 50 characters") String transactionId) {
        this.transactionId = transactionId;
    }

    public @NotNull(message = "User ID is required") @Positive(message = "User ID must be positive") Long getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "User ID is required") @Positive(message = "User ID must be positive") Long userId) {
        this.userId = userId;
    }


    // Getters and setters
}
*/
/*
    @NotNull(message = "Order ID is required")
    @Positive(message = "Order ID must be positive")
    private Long orderId;

    @NotBlank(message = "Transaction ID is required")
    @Size(max = 50, message = "Transaction ID must be less than 50 characters")
    private String transactionId;

    @NotBlank(message = "Payment type is required")
    @Size(max = 20, message = "Payment type must be less than 20 characters")
    private String paymentType;

    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount must be greater than or equal to 0")
    private Double amount;

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be positive")
    private Long userId;

    // The status will be automatically set to "SUCCESS" in the service layer
    private String status = "SUCCESS";

    // The time when the payment is generated (optional, you can set it in the service layer)
    private LocalDateTime generatedTime;

    // Response message
    private String message;

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
}
*/
    /*
@NotNull(message = "Order ID is required")
@Positive(message = "Order ID must be positive")
private Long orderId;

    @NotBlank(message = "Transaction ID is required")
    @Size(max = 50, message = "Transaction ID must be less than 50 characters")
    private String transactionId;

    @NotBlank(message = "Payment type is required")
    @Size(max = 20, message = "Payment type must be less than 20 characters")
    private String paymentType;

    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount must be greater than or equal to 0")
    private Double amount;

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be positive")
    private Long userId;

    private String status = "SUCCESS"; // Default to "SUCCESS"
    private LocalDateTime generatedTime;

    private String message;

    // Getters and Setters

    public @NotNull(message = "Amount is required") @Min(value = 0, message = "Amount must be greater than or equal to 0") Double getAmount() {
        return amount;
    }

    public void setAmount(@NotNull(message = "Amount is required") @Min(value = 0, message = "Amount must be greater than or equal to 0") Double amount) {
        this.amount = amount;
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

    public @NotNull(message = "Order ID is required") @Positive(message = "Order ID must be positive") Long getOrderId() {
        return orderId;
    }

    public void setOrderId(@NotNull(message = "Order ID is required") @Positive(message = "Order ID must be positive") Long orderId) {
        this.orderId = orderId;
    }

    public @NotBlank(message = "Payment type is required") @Size(max = 20, message = "Payment type must be less than 20 characters") String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(@NotBlank(message = "Payment type is required") @Size(max = 20, message = "Payment type must be less than 20 characters") String paymentType) {
        this.paymentType = paymentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public @NotBlank(message = "Transaction ID is required") @Size(max = 50, message = "Transaction ID must be less than 50 characters") String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(@NotBlank(message = "Transaction ID is required") @Size(max = 50, message = "Transaction ID must be less than 50 characters") String transactionId) {
        this.transactionId = transactionId;
    }

    public @NotNull(message = "User ID is required") @Positive(message = "User ID must be positive") Long getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "User ID is required") @Positive(message = "User ID must be positive") Long userId) {
        this.userId = userId;
    }
}
*//*
private Long id;  // Generated automatically in the database, no need to provide in request

    @NotNull(message = "Order ID is required")
    @Positive(message = "Order ID must be positive")
    private Long orderId;

    @NotBlank(message = "Transaction ID is required")
    @Size(max = 50, message = "Transaction ID must be less than 50 characters")
    private String transactionId;

    @NotBlank(message = "Payment type is required")
    @Pattern(regexp = "UPI|NET_BANKING|DEBIT_CARD|CREDIT_CARD", message = "Invalid payment type. Valid types are UPI, Net Banking, Debit Card, and Credit Card.")
    private String paymentType;

    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount must be greater than or equal to 0")
    private Double amount;

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be positive")
    private Long userId;

    private String status;  // This will be set automatically (SUCCESS or FAILURE)

    private LocalDateTime generatedTime;  // Automatically generated, populated by the service layer.

    private String message;  // Custom message, populated by the service layer (e.g., validation errors)

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
}
*/
/*


        private Long id;  // Auto-generated

        @NotNull(message = "Order ID is required")
        @Positive(message = "Order ID must be positive")
        private Long orderId;

        @NotBlank(message = "Transaction ID is required")
        @Size(max = 50, message = "Transaction ID must be less than 50 characters")
        private String transactionId;

        @NotBlank(message = "Payment type is required")
        @Pattern(regexp = "UPI|NET_BANKING|DEBIT_CARD|CREDIT_CARD", message = "Invalid payment type. Valid types are UPI, Net Banking, Debit Card, and Credit Card.")
        private String paymentType;

        @NotNull(message = "Amount is required")
        @Min(value = 0, message = "Amount must be greater than or equal to 0")
        @Positive(message = "Amount should be positive")
        private Double amount;

        @NotNull(message = "User ID is required")
        @Positive(message = "User ID must be positive")
        private Long userId;

        @NotBlank(message = "Status is required")
        private String status;  // Auto-generated by service layer

        private LocalDateTime generatedTime;  // Set by the service

        private String message;  // Custom message for validation or error responses
        private Boolean canceled = false;
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
    */
/*
    @NotNull(message = "Order ID is required")
    private Long orderId;

    @NotNull(message = "Transaction ID is required")

    private String transactionId;

    @NotNull(message = "Payment type is required")
    @Pattern(regexp = "^(NETBANKING|DEBITCARD|CREDITCARD|netbanking|debitcard|creditcard)$",
            message = "Payment type must be 'NetBanking', 'DebitCard' or 'CreditCard' (case insensitive)")
    private String paymentType;

    @Min(value = 0, message = "Amount must be a positive number")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be a positive number")
    private Double amount;


    @NotNull(message = "User ID is required")
    private Long userId;

    private LocalDateTime generatedTime;

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

    public LocalDateTime getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(LocalDateTime generatedTime) {
        this.generatedTime = generatedTime;
    }
}
*/
/*
    @NotNull(message = "Order ID must not be null")
    private Long orderId;

    @NotNull(message = "Transaction ID must not be null")
    @Size(max = 50, message = "Transaction ID must be between 5 and 50 characters")
    private String transactionId;


    @NotNull(message = "Payment type must not be null")
    //@Pattern(regexp = "^(NETBANKING|DEBITCARD|CREDITCARD|netbanking|debitcard|creditcard)$",
           // message = "Payment type must be 'NetBanking', 'DebitCard' or 'CreditCard' (case insensitive)")
   // @ValidPaymentType(message = "Payment type must be 'NetBanking', 'DebitCard' or 'CreditCard' (case insensitive)")
   // @Pattern(regexp = "(?i)^(NetBanking|DebitCard|CreditCard)$", message = "Payment type must be 'NetBanking', 'DebitCard' or 'CreditCard' (case insensitive)")
    private String paymentType;

    @Positive(message = "Amount must be greater than zero")
    private Double amount;

    @NotNull(message = "User ID must not be null")
    private Long userId;

    private String status;  // Status will not have a default value

    private LocalDateTime generatedTime = LocalDateTime.now();

    // Getters and Setters


    public @Positive(message = "Amount must be greater than zero") Double getAmount() {
        return amount;
    }

    public void setAmount(@Positive(message = "Amount must be greater than zero") Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(LocalDateTime generatedTime) {
        this.generatedTime = generatedTime;
    }

    public @NotNull(message = "Order ID must not be null") Long getOrderId() {
        return orderId;
    }

    public void setOrderId(@NotNull(message = "Order ID must not be null") Long orderId) {
        this.orderId = orderId;
    }

    public @NotNull(message = "Payment type must not be null") String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(@NotNull(message = "Payment type must not be null") String paymentType) {
        this.paymentType = paymentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public @NotNull(message = "Transaction ID must not be null") @Size(max = 50, message = "Transaction ID must be between 5 and 50 characters") String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(@NotNull(message = "Transaction ID must not be null") @Size(max=50, message = "Transaction ID must be between 5 and 50 characters") String transactionId) {
        this.transactionId = transactionId;
    }

    public @NotNull(message = "User ID must not be null") Long getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "User ID must not be null") Long userId) {
        this.userId = userId;
    }
}
*/
/*

    @NotNull(message = "Order ID must not be null")
    private Long orderId;

    @NotNull(message = "Transaction ID must not be null")
    @Size(max = 50, message = "Transaction ID upto 50 characters")
    private String transactionId;

    @NotNull(message = "Payment type must not be null")
    @Pattern(regexp = "(?i)^(NetBanking|DebitCard|CreditCard)$", message = "Payment type must be 'NetBanking', 'DebitCard' or 'CreditCard' (case insensitive)")
    private String paymentType;

    @Positive(message = "Amount must be greater than zero")
    private Double amount;

    @NotNull(message = "User ID must not be null")
    private Long userId;

    private String status;  // Status will not have a default value

    private LocalDateTime generatedTime = LocalDateTime.now();

    // Getters and Setters


    public @Positive(message = "Amount must be greater than zero") Double getAmount() {
        return amount;
    }

    public void setAmount(@Positive(message = "Amount must be greater than zero") Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(LocalDateTime generatedTime) {
        this.generatedTime = generatedTime;
    }

    public @NotNull(message = "Order ID must not be null") Long getOrderId() {
        return orderId;
    }

    public void setOrderId(@NotNull(message = "Order ID must not be null") Long orderId) {
        this.orderId = orderId;
    }

    public @NotNull(message = "Payment type must not be null") @Pattern(regexp = "(?i)^(NetBanking|DebitCard|CreditCard)$", message = "Payment type must be 'NetBanking', 'DebitCard' or 'CreditCard' (case insensitive)") String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(@NotNull(message = "Payment type must not be null") @Pattern(regexp = "(?i)^(NetBanking|DebitCard|CreditCard)$", message = "Payment type must be 'NetBanking', 'DebitCard' or 'CreditCard' (case insensitive)") String paymentType) {
        this.paymentType = paymentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public @NotNull(message = "Transaction ID must not be null") @Size(max = 50, message = "Transaction ID upto 50 characters") String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(@NotNull(message = "Transaction ID must not be null") @Size(max = 50, message = "Transaction ID upto 50 characters") String transactionId) {
        this.transactionId = transactionId;
    }

    public @NotNull(message = "User ID must not be null") Long getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "User ID must not be null") Long userId) {
        this.userId = userId;
    }

    public PaymentDTO() {

    }
}
*/
    /*
@NotNull(message = "{orderId}")
private Long orderId;

    @NotNull(message = "{transactionId}")
    @Size(min = 5, max = 50, message = "{transactionId}")
    private String transactionId;

    @NotNull(message = "{paymentType.null}")
    @Pattern(regexp = "(?i)^(NetBanking|DebitCard|CreditCard)$", message = "{paymentType.invalid}")
    private String paymentType;

    @Positive(message = "{amount}")
    private Double amount;

    @NotNull(message = "{userId}")
    private Long userId;

    private String status;  // Optional, but can be set when updating status

    private LocalDateTime generatedTime = LocalDateTime.now();

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
}
*/
/*
    @NotNull(message = "{itemId}") // Validation for itemId
    private int itemId;  // Changed from orderId to itemId

    @NotNull(message = "{transactionId}")  // Validation for transactionId
    @Size(min = 5, max = 50, message = "{transactionId}")
    private String transactionId;

    @NotNull(message = "{paymentType.null}")  // Validation for paymentType
    @Pattern(regexp = "(?i)^(NetBanking|DebitCard|CreditCard)$", message = "{paymentType.invalid}")
    private String paymentType;

    @Positive(message = "{amount}")  // Validation for amount to be positive
    private Double amount;

    @NotNull(message = "{userId}")  // Validation for userId
    private Long userId;

    private String status; // Optional, can be set when updating status

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
}*/


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

    @NotNull(message = "{userId}")
    private Long userId;

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
}