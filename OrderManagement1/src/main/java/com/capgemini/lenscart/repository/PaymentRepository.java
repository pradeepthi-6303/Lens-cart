package com.capgemini.lenscart.repository;

import com.capgemini.lenscart.entity.Payment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/*
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
   /* boolean existsByTransactionId(String transactionId);

    boolean existsByOrderIdAndUserId(Long orderId, Long userId);

    Optional<Payment> findByTransactionId(String transactionId);


    boolean existsByUserId(Long userId);


    boolean existsByOrderId(Long orderId);
}


    Optional<Payment> findByTransactionId(String transactionId);

    Optional<Payment> findByOrderIdAndUserId(Long orderId, Long userId);
}
*/
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    boolean existsByTransactionId(String transactionId);
    boolean existsByOrderId(Long orderId);

    boolean existsByUserId(Long userId);
}