package com.capgemini.lenscart.service;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CartClient cartClient;  // Injecting the CartClient

    public Payment processPayment(@Valid PaymentDTO paymentDTO) throws Exception {
        logger.info("Processing payment for Item ID: {}", paymentDTO.getItemId());

        // Validate Payment Type
        String paymentType = paymentDTO.getPaymentType().toUpperCase();

        // Check if transactionId or itemId already exist
        if (paymentRepository.existsByTransactionId(paymentDTO.getTransactionId())) {
            logger.error("Transaction ID {} already used.", paymentDTO.getTransactionId());
            throw new Exception("Transaction ID already used.");
        }

        if (paymentRepository.existsByItemId(paymentDTO.getItemId())) {
            logger.error("Item ID {} already used.", paymentDTO.getItemId());
            throw new Exception("Item ID already used.");
        }

        // Fetch cart details using CartClient based on itemId
        CartDTO cartDTO = cartClient.getCartByItemId(paymentDTO.getItemId());
        if (cartDTO == null) {
            logger.error("Cart with Item ID {} not found.", paymentDTO.getItemId());
            throw new Exception("Cart with itemId " + paymentDTO.getItemId() + " not found.");
        }

        double cartAmount = cartDTO.getPrice();
        logger.info("Cart price fetched: {}", cartAmount);

        // Create Payment object from DTO
        Payment payment = new Payment();
        payment.setItemId(paymentDTO.getItemId());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setPaymentType(paymentType);
        payment.setAmount(cartAmount);  // Set the amount to the cart's price
        payment.setStatus("SUCCESS");
        payment.setGeneratedTime(LocalDateTime.now());

        // Save and return the payment
        Payment savedPayment = paymentRepository.save(payment);
        logger.info("Payment processed and saved successfully with Transaction ID: {}", savedPayment.getTransactionId());

        return savedPayment;
    }

    @Transactional
    public Payment updatePaymentStatus(Long id, String status) throws Exception {
        logger.info("Updating payment status for Payment ID: {}", id);

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new Exception("Payment not found"));

        // Update status
        if ("FAILURE".equals(status)) {
            payment.setStatus("FAILURE");
            logger.info("Payment status set to FAILURE for Payment ID: {}", id);
        } else {
            payment.setStatus("SUCCESS");
            logger.info("Payment status set to SUCCESS for Payment ID: {}", id);
        }
        payment.setGeneratedTime(LocalDateTime.now());

        // Save and return the updated payment
        Payment updatedPayment = paymentRepository.save(payment);
        logger.info("Payment status updated successfully for Payment ID: {}", updatedPayment.getId());
        return updatedPayment;
    }

    public Payment getPaymentById(Long id) {
        logger.info("Fetching payment details for Payment ID: {}", id);
        return paymentRepository.findById(id).orElse(null);
    }

    public void deletePayment(Long id) throws Exception {
        logger.info("Deleting payment with Payment ID: {}", id);

        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            paymentRepository.delete(payment.get());
            logger.info("Payment with ID {} deleted successfully.", id);
        } else {
            logger.error("Payment with ID {} not found.", id);
            throw new Exception("Payment with ID " + id + " not found");
        }
    }

    public List<Payment> getAllPayments() {
        logger.info("Fetching all payments");
        return paymentRepository.findAll();
    }

    public Payment getPaymentByItemId(int itemId) {
        logger.info("Fetching payment for Item ID: {}", itemId);
        return paymentRepository.findByItemId(itemId);
    }
}
