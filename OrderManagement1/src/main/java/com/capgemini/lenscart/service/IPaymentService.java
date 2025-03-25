package com.capgemini.lenscart.service;

import com.capgemini.lenscart.dto.PaymentDTO;
import com.capgemini.lenscart.entity.Payment;

import java.util.List;

public interface IPaymentService {
    Payment processPayment(PaymentDTO paymentDTO) throws Exception;

    Payment updatePaymentStatus(Long id, String status) throws Exception;

    Payment getPaymentById(Long id);

    void deletePayment(Long id) throws Exception;

    List<Payment> getAllPayments();

    Payment getPaymentByItemId(int itemId);

}
