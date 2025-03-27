package com.capgemini.lenscart.Repositories;

import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.lenscart.Entities.Cart;
import com.capgemini.lenscart.Entities.CartItem;



public interface CartRepository extends JpaRepository<Cart, Integer> {

	
    // You can define custom query methods here if needed

	List<Cart> findByCustomerId(int customerId);
	

}
