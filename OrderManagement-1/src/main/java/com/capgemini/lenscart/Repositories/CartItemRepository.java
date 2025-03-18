package com.capgemini.lenscart.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.lenscart.Entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	List<CartItem> findByCustomerId(int customerId);

	
	

}
