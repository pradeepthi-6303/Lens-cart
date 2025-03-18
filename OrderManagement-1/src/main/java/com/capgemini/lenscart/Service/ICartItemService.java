package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;

import com.capgemini.lenscart.Entities.CartItem;

public interface ICartItemService {
	
	CartItem addCartItem(CartItem cartItem);
    Optional<CartItem> getCartItemById(int itemId);
    List<CartItem> getAllCartItems();
    List<CartItem> getCartsItemByCustomerId(int customerId);
    String deleteCartItem(int itemId);
	
}
