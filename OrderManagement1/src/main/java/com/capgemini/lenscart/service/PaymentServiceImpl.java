package com.capgemini.lenscart.service;
/*
import com.capgemini.lenscart.entity.Payment;
import com.capgemini.lenscart.exceptions.InvalidPaymentException;
import com.capgemini.lenscart.exceptions.PaymentNotFoundException;
import com.capgemini.lenscart.exceptions.ResourceNotFoundException;
import com.capgemini.lenscart.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
    }

    public Payment savePayment(Payment payment) {
        if (payment.getAmount() < 0) {
            throw new InvalidPaymentException("Amount must be greater than or equal to 0");
        }
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new PaymentNotFoundException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }
}
*/


import com.capgemini.lenscart.client.CartClient;
import com.capgemini.lenscart.dto.CartDTO;
import com.capgemini.lenscart.dto.PaymentDTO;
import com.capgemini.lenscart.entity.Payment;
import com.capgemini.lenscart.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
/*
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    */

/*
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
    }

    public Payment savePayment(PaymentDTO paymentDTO) {
        validatePaymentDTO(paymentDTO);
        Payment payment = new Payment();
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setPaymentType(paymentDTO.getPaymentType());
        payment.setAmount(paymentDTO.getAmount());
        payment.setStatus(paymentDTO.getStatus());
        payment.setUserId(paymentDTO.getUserId());
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new PaymentNotFoundException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }

    private void validatePaymentDTO(PaymentDTO paymentDTO) {
        if (paymentDTO.getOrderId() == null || paymentDTO.getOrderId() <= 0) {
            throw new InvalidPaymentException("orderId : Order ID must be positive and not null");
        }
        if (paymentDTO.getTransactionId() == null || paymentDTO.getTransactionId().isBlank() || paymentDTO.getTransactionId().length() > 50) {
            throw new InvalidPaymentException("Transaction : Transaction ID must be positive, not null and must be less than 50 characters");
        }
        if (paymentDTO.getPaymentType() == null || paymentDTO.getPaymentType().isBlank()) {
            throw new InvalidPaymentException("Payment type is required");
        }
        if (paymentDTO.getAmount() == null || paymentDTO.getAmount() < 0) {
            throw new InvalidPaymentException("Amount must be greater than or equal to 0");
        }
        if (paymentDTO.getStatus() == null || paymentDTO.getStatus().isBlank()) {
            throw new InvalidPaymentException("Status is required");
        }
        if (paymentDTO.getUserId() == null || paymentDTO.getUserId() <= 0) {
            throw new InvalidPaymentException("User ID must be positive and not null");
        }
    }
}
*/
    /*

    public String getAllPayments() {
        return "Success";
    }

    public String getPaymentById(Long id) {
        return "Payment details: Success";
    }

    public String savePayment(PaymentDTO paymentDTO) {
        // Validate if transactionId already exists
        if (paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            return "Transaction ID already exists.";
        }

        // Create Payment object from DTO
        Payment payment = new Payment();
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setPaymentType(paymentDTO.getPaymentType());
        payment.setAmount(paymentDTO.getAmount());
        payment.setStatus("SUCCESS");  // Automatically set status to SUCCESS
        payment.setUserId(paymentDTO.getUserId());
        payment.setGeneratedTime(LocalDateTime.now());

        // Save payment to repository
        paymentRepository.save(payment);

        return "Success";
    }

    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return paymentRepository.existsById(id);
    }

    // Update Payment details by ID
    public String updatePayment(Long id, PaymentDTO paymentDTO) {
        // Find the payment to update
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found with id: " + id));

        // Update fields
        existingPayment.setOrderId(paymentDTO.getOrderId());
        existingPayment.setTransactionId(paymentDTO.getTransactionId());  // Assuming you want to allow updating the transactionId
        existingPayment.setPaymentType(paymentDTO.getPaymentType());
        existingPayment.setAmount(paymentDTO.getAmount());
        existingPayment.setStatus("SUCCESS");  // Automatically set status to SUCCESS
        existingPayment.setUserId(paymentDTO.getUserId());
        existingPayment.setGeneratedTime(LocalDateTime.now());

        // Save the updated payment
        paymentRepository.save(existingPayment);

        return "Payment updated successfully!";
    }
}
*/
    /*

    // Save new payment
    public PaymentDTO savePayment(PaymentDTO paymentDTO) {
        // Validate if transactionId already exists
        if (paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction ID already exists.");
        }

        // Create Payment object from DTO
        Payment payment = new Payment();
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setPaymentType(paymentDTO.getPaymentType());
        payment.setAmount(paymentDTO.getAmount());
        payment.setStatus("SUCCESS");  // Automatically set status to SUCCESS
        payment.setUserId(paymentDTO.getUserId());
        payment.setGeneratedTime(LocalDateTime.now());

        // Save payment to repository
        paymentRepository.save(payment);

        // Set success message
        paymentDTO.setMessage("Payment created successfully");
        paymentDTO.setGeneratedTime(payment.getGeneratedTime()); // set generated time in response

        return paymentDTO;
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found with id: " + id));
    }

    // Update Payment details by ID
    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
        // Find the payment to update
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found with id: " + id));

        // Update fields
        existingPayment.setOrderId(paymentDTO.getOrderId());
        existingPayment.setTransactionId(paymentDTO.getTransactionId()); // Assuming you want to allow updating the transactionId
        existingPayment.setPaymentType(paymentDTO.getPaymentType());
        existingPayment.setAmount(paymentDTO.getAmount());
        existingPayment.setStatus("SUCCESS");  // Automatically set status to SUCCESS
        existingPayment.setUserId(paymentDTO.getUserId());
        existingPayment.setGeneratedTime(LocalDateTime.now());

        // Save the updated payment
        paymentRepository.save(existingPayment);

        // Set success message
        paymentDTO.setMessage("Payment updated successfully");
        paymentDTO.setGeneratedTime(existingPayment.getGeneratedTime()); // set generated time in response

        return paymentDTO;
    }
}

*/
/*
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    private final List<String> validPaymentTypes = Arrays.asList("NET_BANKING", "DEBIT_CARD", "CREDIT_CARD");

    // Method to save the payment
    public PaymentDTO savePayment(PaymentDTO paymentDTO) {
        // Check if orderId and userId already exist
        if (paymentRepository.existsByOrderIdAndUserId(paymentDTO.getOrderId(), paymentDTO.getUserId())) {
            paymentDTO.setMessage("Order ID and User ID already exist.");
            return paymentDTO;
        }

        // Validate the payment type
        if (!validPaymentTypes.contains(paymentDTO.getPaymentType().toUpperCase())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "PaymentType:Invalid Payment Type. Allowed values: UPI, NET_BANKING, DEBIT_CARD, CREDIT_CARD");
        }

        // Check if the transaction ID already exists
        if (paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "TransactionId:Transaction ID already exists.");
        }

        // Check if the order is canceled (this should be set based on your business logic)
        boolean orderCanceled = isOrderCanceled(paymentDTO.getOrderId()); // This method should determine if the order is canceled

        // Create Payment entity
        Payment payment = new Payment();
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setPaymentType(paymentDTO.getPaymentType());
        payment.setAmount(paymentDTO.getAmount());

        // If order is canceled, set status to "CANCELED", otherwise set it to "SUCCESS"
        payment.setStatus(orderCanceled ? "CANCELED" : "SUCCESS");

        payment.setUserId(paymentDTO.getUserId());
        payment.setGeneratedTime(LocalDateTime.now());
        payment.setOrderCanceled(orderCanceled);

        // Save to database
        paymentRepository.save(payment);

        // If the order is canceled, set a custom message to notify that
        paymentDTO.setGeneratedTime(payment.getGeneratedTime());
        paymentDTO.setMessage(orderCanceled ? "Payment has been canceled due to order cancellation." : "Payment created successfully");

        return paymentDTO;
    }

    // Refund logic is removed, we are using "CANCELED" instead of "REFUND"
    // For the sake of simplicity, I am assuming the logic for issuing a refund is not needed anymore.

    // Check if the order is canceled (this logic will depend on your actual order status)
    private boolean isOrderCanceled(Long orderId) {
        // Replace this logic with actual order status checking
        // For example, you could query an order service or database to check if the order is canceled.
        return false; // Assuming the order is not canceled for now
    }

    public PaymentDTO getPaymentById(Long id) {
        return null;
    }

    public PaymentDTO cancelPayment(Long id) {
        return null;
    }

    public List<PaymentDTO> getAllPayments() {
        return List.of();
    }

    public void deletePayment(Long id) {
    }

    // Other methods like getAllPayments, getPaymentById, etc. will remain unchanged...
}
*/
/*
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
        return mapToDTO(payment);
    }

    public PaymentDTO savePayment(PaymentDTO paymentDTO) {
        // Check if transactionId already exists
        if (paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            throw new PaymentAlreadyExistsException("Transaction ID already exists.");
        }
        // Check if orderId and userId already exist (if applicable)
        if (paymentRepository.existsByOrderIdAndUserId(paymentDTO.getOrderId(), paymentDTO.getUserId())) {
            throw new PaymentAlreadyExistsException("Order ID and User ID already exist.");
        }

        Payment payment = new Payment();
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setPaymentType(paymentDTO.getPaymentType());
        payment.setAmount(paymentDTO.getAmount());
        payment.setUserId(paymentDTO.getUserId());
        payment.setStatus(paymentDTO.getStatus());
        payment.setGeneratedTime(paymentDTO.getGeneratedTime());
        paymentRepository.save(payment);

        return mapToDTO(payment);
    }

    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
        // Check if payment exists
        Optional<Payment> existingPaymentOpt = paymentRepository.findById(id);
        if (!existingPaymentOpt.isPresent()) {
            throw new PaymentNotFoundException("Payment not found with id: " + id);
        }

        // Validate transactionId, orderId, userId before update
        if (!existingPaymentOpt.get().getTransactionId().equals(paymentDTO.getTransactionId()) &&
                paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            throw new PaymentAlreadyExistsException("Transaction ID already exists.");
        }

        if (paymentRepository.existsByOrderIdAndUserId(paymentDTO.getOrderId(), paymentDTO.getUserId())) {
            throw new PaymentAlreadyExistsException("Order ID and User ID already exist.");
        }

        Payment existingPayment = existingPaymentOpt.get();
        existingPayment.setOrderId(paymentDTO.getOrderId());
        existingPayment.setTransactionId(paymentDTO.getTransactionId());
        existingPayment.setPaymentType(paymentDTO.getPaymentType());
        existingPayment.setAmount(paymentDTO.getAmount());
        existingPayment.setUserId(paymentDTO.getUserId());
        existingPayment.setStatus(paymentDTO.getStatus());
        existingPayment.setGeneratedTime(paymentDTO.getGeneratedTime());

        paymentRepository.save(existingPayment);

        return mapToDTO(existingPayment);
    }

    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new PaymentNotFoundException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }

    private PaymentDTO mapToDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setOrderId(payment.getOrderId());
        paymentDTO.setTransactionId(payment.getTransactionId());
        paymentDTO.setPaymentType(payment.getPaymentType());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setUserId(payment.getUserId());
        paymentDTO.setStatus(payment.getStatus());
        paymentDTO.setGeneratedTime(payment.getGeneratedTime());
        return paymentDTO;
    }
}
*/
/*
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentDTO savePayment(PaymentDTO paymentDTO) {
        // Validate if transaction ID already exists
        if (paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            throw new PaymentAlreadyExistsException("Transaction ID already exists.");
        }
        // Validate if orderId and userId already exist
        if (paymentRepository.existsByOrderIdAndUserId(paymentDTO.getOrderId(), paymentDTO.getUserId())) {
            throw new PaymentAlreadyExistsException("Order ID and User ID already exist.");
        }

        Payment payment = new Payment();
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setPaymentType(paymentDTO.getPaymentType());
        payment.setAmount(paymentDTO.getAmount());
        payment.setUserId(paymentDTO.getUserId());
        payment.setStatus("SUCCESS"); // Default status, can be updated based on the business logic
        payment.setGeneratedTime(LocalDateTime.now());

        paymentRepository.save(payment);

        return mapToDTO(payment);
    }

    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
        return mapToDTO(payment);
    }

    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
        // Check if the payment exists
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));

        // Handle business logic (e.g., transaction ID or user ID already exists)
        if (!existingPayment.getTransactionId().equals(paymentDTO.getTransactionId()) &&
                paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            throw new PaymentAlreadyExistsException("Transaction ID already exists.");
        }

        // Update the payment record
        existingPayment.setOrderId(paymentDTO.getOrderId());
        existingPayment.setTransactionId(paymentDTO.getTransactionId());
        existingPayment.setPaymentType(paymentDTO.getPaymentType());
        existingPayment.setAmount(paymentDTO.getAmount());
        existingPayment.setUserId(paymentDTO.getUserId());
        existingPayment.setStatus(paymentDTO.getStatus());
        existingPayment.setGeneratedTime(LocalDateTime.now());

        paymentRepository.save(existingPayment);

        return mapToDTO(existingPayment);
    }

    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new PaymentNotFoundException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }

    private PaymentDTO mapToDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setOrderId(payment.getOrderId());
        paymentDTO.setTransactionId(payment.getTransactionId());
        paymentDTO.setPaymentType(payment.getPaymentType());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setUserId(payment.getUserId());
        paymentDTO.setStatus(payment.getStatus());
        paymentDTO.setGeneratedTime(payment.getGeneratedTime());
        return paymentDTO;
    }

    public List<PaymentDTO> getAllPayments() {
        return List.of();
    }
}
*/
/*
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment savePayment(PaymentDTO paymentDTO) {
        List<String> validationMessages = new ArrayList<>();

        // Check for duplicate transaction ID
        if (paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            validationMessages.add("Transaction ID already exists.");
        }

        // Check for duplicate Order ID
        if (paymentRepository.existsByOrderId(paymentDTO.getOrderId())){
            validationMessages.add("Order ID already exists.");
        }

        // Check for duplicate User ID
        if (paymentRepository.existsByUserId(paymentDTO.getUserId())) {
            validationMessages.add("User ID already exists.");
        }

        // Check for invalid payment type
        if (!isValidPaymentType(paymentDTO.getPaymentType())) {
            validationMessages.add("Invalid payment type. Valid types are UPI, Net Banking, Debit Card, and Credit Card.");
        }

        // If there are validation messages, throw an exception with the messages
        if (!validationMessages.isEmpty()) {
            throw new InvalidPaymentException(String.join(", ", validationMessages));
        }

        // Proceed to save payment if no validation errors
        Payment payment = new Payment();
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setPaymentType(paymentDTO.getPaymentType());
        payment.setAmount(paymentDTO.getAmount());
        payment.setStatus("SUCCESS");
        payment.setUserId(paymentDTO.getUserId());
        payment.setGeneratedTime(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    // Helper method to validate payment type
    private boolean isValidPaymentType(String paymentType) {
        return "UPI".equalsIgnoreCase(paymentType) ||
                "Net Banking".equalsIgnoreCase(paymentType) ||
                "Debit Card".equalsIgnoreCase(paymentType) ||
                "Credit Card".equalsIgnoreCase(paymentType);
    }

    public PaymentDTO getPaymentById(Long id) {
        return null;
    }

    public void deletePayment(Long id) {
    }

    public List<PaymentDTO> getAllPayments() {
        return List.of();
    }

    public PaymentDTO updatePayment(Long id, @Valid PaymentDTO paymentDTO) {
        return paymentDTO;
    }
}
*/
/*
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment savePayment(PaymentDTO paymentDTO) {
        List<String> validationMessages = new ArrayList<>();

        // Check for duplicate transaction ID
        if (paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            validationMessages.add("Transaction ID already exists.");
        }

        // Check for duplicate Order ID
        if (paymentRepository.existsByOrderId(paymentDTO.getOrderId())) {
            validationMessages.add("Order ID already exists.");
        }

        // Check for duplicate User ID
        if (paymentRepository.existsByUserId(paymentDTO.getUserId())) {
            validationMessages.add("User ID already exists.");
        }

        // Check for invalid payment type
        if (!isValidPaymentType(paymentDTO.getPaymentType())) {
            validationMessages.add("Invalid payment type. Valid types are UPI, Net Banking, Debit Card, and Credit Card.");
        }

        // If there are validation messages, throw an exception with the messages
        if (!validationMessages.isEmpty()) {
            throw new InvalidPaymentException(String.join(", ", validationMessages));
        }

        // Proceed to save payment if no validation errors
        Payment payment = new Payment();
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setPaymentType(paymentDTO.getPaymentType());
        payment.setAmount(paymentDTO.getAmount());
        payment.setStatus("SUCCESS");
        payment.setUserId(paymentDTO.getUserId());
        payment.setGeneratedTime(LocalDateTime.now());

        // Handle failure or cancellation scenario
        if (paymentDTO.getAmount() <= 0) {
            payment.setStatus("CANCELED");
            payment.setMessage("Payment canceled due to invalid amount.");
        } else {
            payment.setMessage("Payment created successfully.");
        }

        return paymentRepository.save(payment);
    }

    // Helper method to validate payment type
    private boolean isValidPaymentType(String paymentType) {
        return "UPI".equalsIgnoreCase(paymentType) ||
                "Net Banking".equalsIgnoreCase(paymentType) ||
                "Debit Card".equalsIgnoreCase(paymentType) ||
                "Credit Card".equalsIgnoreCase(paymentType);
    }

    public void deletePayment(Long id) {
    }

    public Payment getPaymentById(Long id) {
        return null;
    }

    public Payment updatePayment(Long id, PaymentDTO paymentDTO) {
        return null;
    }
}
*/
/*
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment savePayment(PaymentDTO paymentDTO) {
        List<String> validationMessages = new ArrayList<>();

        // Check for duplicate transaction ID
        if (paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            validationMessages.add("Transaction ID already exists.");
        }

        // Check for duplicate Order ID
        if (paymentRepository.existsByOrderId(paymentDTO.getOrderId())) {
            validationMessages.add("Order ID already exists.");
        }

        // Check for duplicate User ID
        if (paymentRepository.existsByUserId(paymentDTO.getUserId())) {
            validationMessages.add("User ID already exists.");
        }

        // Check for invalid payment type
        if (!isValidPaymentType(paymentDTO.getPaymentType())) {
            validationMessages.add("Invalid payment type. Valid types are UPI, Net Banking, Debit Card, and Credit Card.");
        }

        // Check for invalid amount
        if (paymentDTO.getAmount() == null || paymentDTO.getAmount() < 0) {
            validationMessages.add("Amount must be greater than or equal to 0.");
        }

        // If there are validation messages, throw an exception with the messages
        if (!validationMessages.isEmpty()) {
            throw new InvalidPaymentException(String.join(", ", validationMessages));
        }

        // Proceed to save payment if no validation errors
        Payment payment = new Payment();
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setPaymentType(paymentDTO.getPaymentType());
        payment.setAmount(paymentDTO.getAmount());
        payment.setStatus("SUCCESS");  // Assuming success initially
        payment.setUserId(paymentDTO.getUserId());
        payment.setGeneratedTime(LocalDateTime.now());

        // Set success message
        payment.setMessage("Payment created successfully.");
        payment.setCanceled(false); // Initially set to not canceled

        // Save the payment to the repository
        return paymentRepository.save(payment);
    }

    // Helper method to validate payment type
    private boolean isValidPaymentType(String paymentType) {
        return "UPI".equalsIgnoreCase(paymentType) ||
                "Net Banking".equalsIgnoreCase(paymentType) ||
                "Debit Card".equalsIgnoreCase(paymentType) ||
                "Credit Card".equalsIgnoreCase(paymentType);
    }

    public Payment updatePayment(Long id, PaymentDTO paymentDTO) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));

        // Perform the update logic
        existingPayment.setOrderId(paymentDTO.getOrderId());
        existingPayment.setTransactionId(paymentDTO.getTransactionId());
        existingPayment.setPaymentType(paymentDTO.getPaymentType());
        existingPayment.setAmount(paymentDTO.getAmount());

        // If the payment type is invalid, return an error message
        if (!isValidPaymentType(paymentDTO.getPaymentType())) {
            throw new InvalidPaymentException("Invalid payment type. Valid types are UPI, Net Banking, Debit Card, and Credit Card.");
        }

        // Check if the amount is valid (must be positive)
        if (paymentDTO.getAmount() == null || paymentDTO.getAmount() < 0) {
            throw new InvalidPaymentException("Amount must be greater than or equal to 0.");
        }

        // Check if payment status is success or failure
        if ("FAILURE".equalsIgnoreCase(paymentDTO.getStatus())) {
            existingPayment.setCanceled(true);
            existingPayment.setMessage("Payment failed. Order is canceled.");
            existingPayment.setStatus("FAILURE");
        } else {
            existingPayment.setMessage("Payment updated successfully.");
            existingPayment.setStatus("SUCCESS");
        }

        // Save the updated payment back to the repository
        return paymentRepository.save(existingPayment);
    }
}*/
/*
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
    }

    public Payment savePayment(PaymentDTO paymentDTO) {
        validatePaymentDTO(paymentDTO);

        Payment payment = new Payment();
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setPaymentType(paymentDTO.getPaymentType());
        payment.setAmount(paymentDTO.getAmount());

        // Automatically set status to "SUCCESS" if not provided
        if (paymentDTO.getStatus() == null || paymentDTO.getStatus().isBlank()) {
            payment.setStatus("SUCCESS");
        } else {
            payment.setStatus(paymentDTO.getStatus());
        }

        payment.setUserId(paymentDTO.getUserId());
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new PaymentNotFoundException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }

    private void validatePaymentDTO(PaymentDTO paymentDTO) {
        if (paymentDTO.getOrderId() == null || paymentDTO.getOrderId() <= 0) {
            throw new InvalidPaymentException("Order ID must be positive and not null");
        }
        if (paymentDTO.getTransactionId() == null || paymentDTO.getTransactionId().isBlank() || paymentDTO.getTransactionId().length() > 50) {
            throw new InvalidPaymentException("Transaction ID must be positive, not null, and must be less than 50 characters");
        }
        if (paymentDTO.getPaymentType() == null || paymentDTO.getPaymentType().isBlank()) {
            throw new InvalidPaymentException("Payment type is required");
        }
        if (paymentDTO.getAmount() == null || paymentDTO.getAmount() < 0) {
            throw new InvalidPaymentException("Amount must be greater than or equal to 0");
        }
        if (paymentDTO.getStatus() == null || paymentDTO.getStatus().isBlank()) {
            throw new InvalidPaymentException("Status is required");
        }
        if (paymentDTO.getUserId() == null || paymentDTO.getUserId() <= 0) {
            throw new InvalidPaymentException("User ID must be positive and not null");
        }
    }

    public Payment updatePayment(Long id, @Valid PaymentDTO paymentDTO) {
        return null;
    }
}
*/
/*@Service

public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(@Valid PaymentDTO paymentDTO) {
        return null;
    }

    public Payment updatePaymentStatus(Long id, String status) {
        return null;
    }

    public Payment getPaymentById(Long id) {
        return null;
    }

    public List<Payment> getAllPayments() {
        return List.of();
    }

    // 1. Create a new payment
    /*
    @Transactional
    public Payment createPayment(PaymentDTO paymentDTO) throws PaymentException {
        // Check if the transactionId already exists
        if (paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            throw new PaymentException("Transaction ID already exists");
        }

        // Check if the orderId already exists
        if (paymentRepository.existsByOrderId(paymentDTO.getOrderId())) {
            throw new PaymentException("Order ID already exists");
        }

        // Check if the userId already exists
        if (paymentRepository.existsByUserId(paymentDTO.getUserId())) {
            throw new PaymentException("User ID already exists");
        }

        // Convert the payment type to PaymentType enum
        PaymentType paymentType = PaymentType.valueOf(paymentDTO.getPaymentType().toUpperCase());

        // Create the Payment object with auto-generated values like status and generated time
        Payment payment = new Payment(
                paymentDTO.getOrderId(),
                paymentDTO.getTransactionId(),
                paymentType,
                paymentDTO.getAmount(),
                paymentDTO.getUserId()
        );

        // Save the payment to the database
        return paymentRepository.save(payment);
    }

    // 2. Get a payment by ID
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    // 3. Get all payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // 4. Update an existing payment
    @Transactional
    public Payment updatePayment(Long id, PaymentDTO paymentDTO) throws PaymentException {
        // Check if the payment with the given ID exists
        Optional<Payment> existingPaymentOptional = paymentRepository.findById(id);
        if (!existingPaymentOptional.isPresent()) {
            throw new PaymentException("Payment not found");
        }

        // Get the existing payment
        Payment existingPayment = existingPaymentOptional.get();

        // Update values
        if (paymentDTO.getTransactionId() != null && !paymentDTO.getTransactionId().isEmpty()) {
            existingPayment.setTransactionId(paymentDTO.getTransactionId());
        }

        if (paymentDTO.getPaymentType() != null && !paymentDTO.getPaymentType().isEmpty()) {
            PaymentType paymentType = PaymentType.valueOf(paymentDTO.getPaymentType().toUpperCase());
            existingPayment.setPaymentType(paymentType);
        }

        if (paymentDTO.getAmount() != null || paymentDTO.getAmount() <= 0) {
            existingPayment.setAmount(paymentDTO.getAmount());
        }

        if (paymentDTO.getUserId() != null) {
            existingPayment.setUserId(paymentDTO.getUserId());
        }

        // After updating, return the updated payment
        return paymentRepository.save(existingPayment);
    }

    // 5. Delete a payment
    @Transactional
    public void deletePayment(Long id) throws PaymentException {
        // Check if the payment exists
        if (!paymentRepository.existsById(id)) {
            throw new PaymentException("Payment not found");
        }

        // Delete the payment by ID
        paymentRepository.deleteById(id);
    }
}
*/
/*
    public ResponseEntity<Object> createPayment(PaymentDTO paymentDTO) {
        // Check if the transactionId already exists
        if (paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            return ResponseEntity.badRequest().body("Transaction ID already exists");
        }

        // Check if the orderId already exists
        if (paymentRepository.existsByOrderId(paymentDTO.getOrderId())) {
            return ResponseEntity.badRequest().body("Order ID already exists");
        }

        // Check if the userId already exists
        if (paymentRepository.existsByUserId(paymentDTO.getUserId())) {
            return ResponseEntity.badRequest().body("User ID already exists");
        }

        // If all checks pass, save the payment and return success
        Payment payment = new Payment();
        // Map DTO to entity and save
        paymentRepository.save(payment);

        return ResponseEntity.ok("Payment created successfully");
    }
}
*/

/*
    @Service
    @Validated
    public class PaymentService {

        @Autowired
        private PaymentRepository paymentRepository;

     //   @Autowired
       // private CartClient cartClient;

        public Payment processPayment(@Valid PaymentDTO paymentDTO) throws Exception {

            // Validate Payment Type
            String paymentType = paymentDTO.getPaymentType().toUpperCase();
          /*  if (!paymentType.equals("NET BANKING") && !paymentType.equals("DEBIT CARD") && !paymentType.equals("CREDIT CARD")) {
                throw new Exception("Invalid payment type. Allowed values: Net Banking, Debit Card, Credit Card.");
           // }

            // Check if transactionId, orderId or userId already exist
            if (paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
                throw new Exception("Transaction ID already used.");
            }

            if (paymentRepository.existsByOrderId(paymentDTO.getOrderId())) {
                throw new Exception("Order ID already used.");
            }

            if (paymentRepository.existsByUserId(paymentDTO.getUserId())) {
                throw new Exception("User ID already used.");
            }

            // Create Payment object from DTO
            Payment payment = new Payment();
            payment.setOrderId(paymentDTO.getOrderId());
            payment.setTransactionId(paymentDTO.getTransactionId());
            payment.setPaymentType(paymentType);
            payment.setAmount(paymentDTO.getAmount());
            payment.setStatus("SUCCESS");  // Status is set to SUCCESS by default upon creation
            payment.setUserId(paymentDTO.getUserId());
            payment.setGeneratedTime(LocalDateTime.now());

            // Save and return the payment
            return paymentRepository.save(payment);
        }

        @Transactional
        public Payment updatePaymentStatus(Long id, String status) throws Exception {
            Payment payment = paymentRepository.findById(id).orElseThrow(() -> new Exception("Payment not found"));

            // Update status to SUCCESS or CANCELLED based on the status parameter
            if ("FAILURE".equals(status)) {
                payment.setStatus("FAILURE");
            } else {
                payment.setStatus("SUCCESS");
            }
            payment.setGeneratedTime(LocalDateTime.now());
            return paymentRepository.save(payment);
        }

        public Payment getPaymentById(Long id) {
            return paymentRepository.findById(id).orElse(null);
        }

        public List<Payment> getAllPayments() {
            return paymentRepository.findAll();
        }

        // Method to delete a payment by ID (without @Transactional)
        public void deletePayment(Long id) throws Exception {
            Optional<Payment> payment = paymentRepository.findById(id);
            if (payment.isPresent()) {
                paymentRepository.delete(payment.get());  // Delete the payment from repository
            } else {
                throw new Exception("Payment with ID " + id + " not found");

            }
        }

       public Payment getPaymentDetailsByCartId(Integer cartId) {
            // Use CartClient to get the Cart details
            CartDTO cartDTO = CartClient.getCartById(cartId);

            if (cartDTO != null) {
                // Retrieve the payment info based on the cart orderId
                Long orderId = (long) cartDTO.getItemId(); // Assuming itemId as orderId
                return (Payment) paymentRepository.findByOrderId(orderId).orElse(null);  // Fetch payment by orderId
            }

            return null; // Return null or throw an exception if cart not found
        }


    }
    */
/*
@Service
@Validated
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(@Valid PaymentDTO paymentDTO) throws Exception {

        // Validate Payment Type
        String paymentType = paymentDTO.getPaymentType().toUpperCase();

        // Check if transactionId, itemId or userId already exist
        if (paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            throw new Exception("Transaction ID already used.");
        }

        if (paymentRepository.existsByItemId(paymentDTO.getItemId())) {  // Updated to itemId
            throw new Exception("Item ID already used.");
        }

        if (paymentRepository.existsByUserId(paymentDTO.getUserId())) {
            throw new Exception("User ID already used.");
        }

        // Create Payment object from DTO
        Payment payment = new Payment();
        payment.setItemId(paymentDTO.getItemId());  // Updated to itemId
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setPaymentType(paymentType);
        payment.setAmount(paymentDTO.getAmount());
        payment.setStatus("SUCCESS");  // Status is set to SUCCESS by default upon creation
        payment.setUserId(paymentDTO.getUserId());
        payment.setGeneratedTime(LocalDateTime.now());

        // Save and return the payment
        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment updatePaymentStatus(Long id, String status) throws Exception {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new Exception("Payment not found"));

        // Update status to SUCCESS or CANCELLED based on the status parameter
        if ("FAILURE".equals(status)) {
            payment.setStatus("FAILURE");
        } else {
            payment.setStatus("SUCCESS");
        }
        payment.setGeneratedTime(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public void deletePayment(Long id) throws Exception {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            paymentRepository.delete(payment.get());
        } else {
            throw new Exception("Payment with ID " + id + " not found");
        }
    }
/*
    public Payment getPaymentDetailsByItemId(Long itemId) {  // Updated to itemId
        return paymentRepository.findByItemId(itemId).orElse();
    //}


}
*/
/*
@Service
@Validated
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CartClient cartClient;  // Injecting the CartClient

    public Payment processPayment(@Valid PaymentDTO paymentDTO) throws Exception {

        // Validate Payment Type
        String paymentType = paymentDTO.getPaymentType().toUpperCase();

        // Check if transactionId, itemId or userId already exist
        if (paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            throw new Exception("Transaction ID already used.");
        }

        if (paymentRepository.existsByItemId(paymentDTO.getItemId())) {  // Updated to itemId
            throw new Exception("Item ID already used.");
        }

        if (paymentRepository.existsByUserId(paymentDTO.getUserId())) {
            throw new Exception("User ID already used.");
        }

        // Fetch cart details using CartClient based on itemId
        CartDTO cartDTO = cartClient.getCartByItemId(paymentDTO.getItemId());
        if (cartDTO == null) {
            throw new Exception("Cart with itemId " + paymentDTO.getItemId() + " not found.");
        }

        // Create Payment object from DTO
        Payment payment = new Payment();
        payment.setItemId(paymentDTO.getItemId());  // Updated to itemId
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setPaymentType(paymentType);
        payment.setAmount(paymentDTO.getAmount());
        payment.setStatus("SUCCESS");  // Status is set to SUCCESS by default upon creation
        payment.setUserId(paymentDTO.getUserId());
        payment.setGeneratedTime(LocalDateTime.now());

        // Save and return the payment
        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment updatePaymentStatus(Long id, String status) throws Exception {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new Exception("Payment not found"));

        // Update status to SUCCESS or CANCELLED based on the status parameter
        if ("FAILURE".equals(status)) {
            payment.setStatus("FAILURE");
        } else {
            payment.setStatus("SUCCESS");
        }
        payment.setGeneratedTime(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public void deletePayment(Long id) throws Exception {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            paymentRepository.delete(payment.get());
        } else {
            throw new Exception("Payment with ID " + id + " not found");
        }
    }

}
*/
/*
@Service
@Validated
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CartClient cartClient;  // Injecting the CartClient

    public Payment processPayment(@Valid PaymentDTO paymentDTO) throws Exception {

        // Validate Payment Type
        String paymentType = paymentDTO.getPaymentType().toUpperCase();

        // Check if transactionId, itemId or userId already exist
        if (paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            throw new Exception("Transaction ID already used.");
        }

        if (paymentRepository.existsByItemId(paymentDTO.getItemId())) {  // Updated to itemId
            throw new Exception("Item ID already used.");
        }

        if (paymentRepository.existsByUserId(paymentDTO.getUserId())) {
            throw new Exception("User ID already used.");
        }

        // Fetch cart details using CartClient based on itemId
        CartDTO cartDTO = cartClient.getCartByItemId(paymentDTO.getItemId());
        if (cartDTO == null) {
            throw new Exception("Cart with itemId " + paymentDTO.getItemId() + " not found.");
        }

        // Create Payment object from DTO
        Payment payment = new Payment();
        payment.setItemId(paymentDTO.getItemId());  // Updated to itemId
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setPaymentType(paymentType);
        payment.setAmount(paymentDTO.getAmount());
        payment.setStatus("SUCCESS");  // Status is set to SUCCESS by default upon creation
        payment.setUserId(paymentDTO.getUserId());
        payment.setGeneratedTime(LocalDateTime.now());

        // Save and return the payment
        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment updatePaymentStatus(Long id, String status) throws Exception {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new Exception("Payment not found"));

        // Update status to SUCCESS or CANCELLED based on the status parameter
        if ("FAILURE".equals(status)) {
            payment.setStatus("FAILURE");
        } else {
            payment.setStatus("SUCCESS");
        }
        payment.setGeneratedTime(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public void deletePayment(Long id) throws Exception {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            paymentRepository.delete(payment.get());
        } else {
            throw new Exception("Payment with ID " + id + " not found");
        }
    }

}
*/
/*
@Service
@Validated
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CartClient cartClient;  // Injecting the CartClient

    public Payment processPayment(@Valid PaymentDTO paymentDTO) throws Exception {
        String paymentType = paymentDTO.getPaymentType().toUpperCase();

        // Check if transactionId, itemId, or userId already exist in the database
        if (paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            throw new Exception("Transaction ID already used.");
        }

        if (paymentRepository.existsByItemId(paymentDTO.getItemId())) {
            throw new Exception("Item ID already used.");
        }

        if (paymentRepository.existsByUserId(paymentDTO.getUserId())) {
            throw new Exception("User ID already used.");
        }

        // Fetch cart details using CartClient
        CartDTO cartDTO = cartClient.getCartByItemId(paymentDTO.getItemId());
        if (cartDTO == null) {
            throw new Exception("Cart with itemId " + paymentDTO.getItemId() + " not found.");
        }

        // Create Payment object and set values
        Payment payment = new Payment();
        payment.setItemId(paymentDTO.getItemId());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setPaymentType(paymentType);
        payment.setAmount(paymentDTO.getAmount());
        payment.setStatus("SUCCESS");  // Default status upon creation
        payment.setUserId(paymentDTO.getUserId());
        payment.setGeneratedTime(LocalDateTime.now());

        // Save and return the payment
        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment updatePaymentStatus(Long id, String status) throws Exception {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new Exception("Payment not found"));

        if ("FAILURE".equals(status)) {
            payment.setStatus("FAILURE");
        } else {
            payment.setStatus("SUCCESS");
        }

        payment.setGeneratedTime(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public void deletePayment(Long id) throws Exception {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            paymentRepository.delete(payment.get());
        } else {
            throw new Exception("Payment with ID " + id + " not found");
        }
    }
}*/

@Service
public class PaymentServiceImpl {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CartClient cartClient;  // Injecting the CartClient

    public Payment processPayment(@Valid PaymentDTO paymentDTO) throws Exception {

        // Validate Payment Type
        String paymentType = paymentDTO.getPaymentType().toUpperCase();

        // Check if transactionId, itemId, or userId already exist
        if (paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            throw new Exception("Transaction ID already used.");
        }

        if (paymentRepository.existsByItemId(paymentDTO.getItemId())) {
            throw new Exception("Item ID already used.");
        }
      /*
       if (paymentRepository.existsByUserId(paymentDTO.getUserId())) {
            throw new Exception("User ID already used.");
        }
       */
        // Fetch cart details using CartClient based on itemId
        CartDTO cartDTO = cartClient.getCartByItemId(paymentDTO.getItemId());
        if (cartDTO == null) {
            throw new Exception("Cart with itemId " + paymentDTO.getItemId() + " not found.");
        }

        double cartAmount = cartDTO.getPrice();


        // Create Payment object from DTO
        Payment payment = new Payment();
        payment.setItemId(paymentDTO.getItemId());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setPaymentType(paymentType);
        //payment.setAmount(paymentDTO.getAmount());
        payment.setAmount(cartAmount);  // Set the amount to the cart's price
        payment.setStatus("SUCCESS");
        //payment.setUserId(paymentDTO.getUserId());
        payment.setGeneratedTime(LocalDateTime.now());

        // Save and return the payment
        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment updatePaymentStatus(Long id, String status) throws Exception {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new Exception("Payment not found"));

        // Update status
        if ("FAILURE".equals(status)) {
            payment.setStatus("FAILURE");
        } else {
            payment.setStatus("SUCCESS");
        }
        payment.setGeneratedTime(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public void deletePayment(Long id) throws Exception {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            paymentRepository.delete(payment.get());
        } else {
            throw new Exception("Payment with ID " + id + " not found");
        }
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
    public Payment getPaymentByItemId(int itemId) {
        return paymentRepository.findByItemId(itemId);
    }
}