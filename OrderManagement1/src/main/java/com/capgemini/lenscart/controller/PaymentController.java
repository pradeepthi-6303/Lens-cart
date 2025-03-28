package com.capgemini.lenscart.controller;

import com.capgemini.lenscart.client.CartClient;
import com.capgemini.lenscart.dto.CartDTO;
import com.capgemini.lenscart.dto.PaymentDTO;
import com.capgemini.lenscart.entity.Payment;
import com.capgemini.lenscart.service.PaymentService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    // Logger initialization
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CartClient cartClient;

    @PostMapping("/createpayment")
    public ResponseEntity<?> createPayment(@RequestBody @Validated PaymentDTO paymentDTO) {
        logger.info("Creating payment for itemId: {}", paymentDTO.getItemId());
        try {
            Payment payment = paymentService.processPayment(paymentDTO);
            logger.info("Payment successfully created with transactionId: {}", payment.getTransactionId());
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            logger.error("Error creating payment for itemId: {}. Exception: {}", paymentDTO.getItemId(), e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updatePaymentStatus(@PathVariable Long id, @RequestParam String status) {
        logger.info("Updating payment status for paymentId: {} with status: {}", id, status);
        try {
            Payment updatedPayment = paymentService.updatePaymentStatus(id, status);
            logger.info("Payment status updated successfully for paymentId: {}", id);
            return ResponseEntity.ok(updatedPayment);
        } catch (Exception e) {
            logger.error("Error updating payment status for paymentId: {}. Exception: {}", id, e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        logger.info("Fetching payment details for paymentId: {}", id);
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            logger.warn("Payment with paymentId: {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        logger.info("Payment found for paymentId: {}", id);
        return ResponseEntity.ok(payment);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        logger.info("Fetching all payments.");
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        logger.info("Deleting payment with paymentId: {}", id);
        try {
            paymentService.deletePayment(id);
            logger.info("Payment with paymentId: {} deleted successfully", id);
            return ResponseEntity.ok("Payment with ID " + id + " deleted successfully");
        } catch (Exception e) {
            logger.error("Failed to delete payment with paymentId: {}. Exception: {}", id, e.getMessage());
            return ResponseEntity.badRequest().body("Failed to delete payment with ID " + id + ": " + e.getMessage());
        }
    }

    @GetMapping("/cart/{itemId}")
    public ResponseEntity<?> getCartAndPaymentDetails(@PathVariable("itemId") int itemId) {
        logger.info("Fetching cart and payment details for itemId: {}", itemId);
        try {
            // Fetch Cart details using CartClient
            CartDTO cartDTO = null;
            try {
                cartDTO = cartClient.getCartByItemId(itemId);
                logger.info("Cart details fetched successfully for itemId: {}", itemId);
            } catch (FeignException.NotFound e) {
                logger.warn("Cart not found for itemId: {}", itemId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Cart not found with itemId " + itemId);
            }

            // Fetch Payment details using PaymentService
            Payment payment = paymentService.getPaymentByItemId(itemId);
            if (payment == null) {
                logger.warn("Payment for itemId: {} not found.", itemId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Payment for itemId " + itemId + " not found.");
            }

            // Return cart and payment details as a response
            Map<String, Object> response = new HashMap<>();
            response.put("cart", cartDTO);
            response.put("payment", payment);

            logger.info("Returning cart and payment details for itemId: {}", itemId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error fetching cart and payment details for itemId: {}. Exception: {}", itemId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}
