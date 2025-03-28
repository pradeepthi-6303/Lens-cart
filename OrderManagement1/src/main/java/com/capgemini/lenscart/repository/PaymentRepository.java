package com.capgemini.lenscart.repository;

import com.capgemini.lenscart.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



 @Repository
 public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Custom queries for the updated itemId instead of orderId
    boolean existsByTransactionId(String transactionId);

    boolean existsByItemId(int itemId);  // Updated to itemId

    Payment findByItemId(int itemId);
}