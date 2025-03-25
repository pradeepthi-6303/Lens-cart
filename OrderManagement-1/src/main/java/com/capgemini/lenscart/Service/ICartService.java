package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;

import com.capgemini.lenscart.Entities.Cart;
import com.capgemini.lenscart.Entities.CartItem;

public interface ICartService {
	Cart addCart(Cart cart);
    Optional<Cart> getCartById(int itemId);
    List<Cart> getAllCarts();
    List<Cart> getCartsByCustomerId(int customerId);
    public boolean  deleteCart(int itemId);
    //Double calculateTotalCost(List<CartItem> cartItems);

}
