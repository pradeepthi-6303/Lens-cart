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


import com.capgemini.lenscart.dto.PaymentDTO;
import com.capgemini.lenscart.entity.Payment;
import com.capgemini.lenscart.exceptions.InvalidPaymentException;
import com.capgemini.lenscart.exceptions.PaymentNotFoundException;
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
