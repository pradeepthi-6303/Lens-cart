package com.capgemini.lenscart.service;
import com.capgemini.lenscart.dto.PaymentDTO;
import com.capgemini.lenscart.entity.Payment;
import java.util.List;
public interface IPaymentService {

        // Method to process the payment
        Payment processPayment(PaymentDTO paymentDTO) throws Exception;

        // Method to update payment status
        Payment updatePaymentStatus(Long id, String status) throws Exception;

        // Method to fetch a payment by its ID
        Payment getPaymentById(Long id);

        // Method to delete a payment
        void deletePayment(Long id) throws Exception;

        // Method to fetch all payments
        List<Payment> getAllPayments();

        // Method to fetch payment by item ID
        Payment getPaymentByItemId(int itemId);
    }

