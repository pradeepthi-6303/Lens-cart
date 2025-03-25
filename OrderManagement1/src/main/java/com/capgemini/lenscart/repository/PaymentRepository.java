package com.capgemini.lenscart.repository;

import com.capgemini.lenscart.entity.Payment;
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
/*
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    boolean existsByTransactionId(String transactionId);
    boolean existsByOrderId(Long orderId);

    boolean existsByUserId(Long userId);

    void findByOrderId(Long orderId);
}
*/
 @Repository
 public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Custom queries for the updated itemId instead of orderId
    boolean existsByTransactionId(String transactionId);

    boolean existsByItemId(int itemId);  // Updated to itemId

    //boolean existsByUserId(Long userId);

    //Payment findByItemId(int itemId);  // Updated to itemId

    Payment findByItemId(int itemId);
}