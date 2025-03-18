package com.capgemini.lenscart.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.lenscart.Dto.CartItemDto;
import com.capgemini.lenscart.Entities.CartItem;
import com.capgemini.lenscart.Exception.CartItemNotFoundException;
import com.capgemini.lenscart.Repositories.CartItemRepository;
import com.capgemini.lenscart.Repositories.CartRepository;

@Service
public class CartItemServiceImpl implements ICartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartItem addCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public Optional<CartItem> getCartItemById(int itemId) {
        return cartItemRepository.findById(itemId);
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public List<CartItem> getCartsItemByCustomerId(int customerId) {
        return cartItemRepository.findByCustomerId(customerId);
    }

//    @Override
//    public void deleteCartItem(int itemId) {
//        cartItemRepository.deleteById(itemId);
//    }
    @Override
    public String deleteCartItem(int itemId) {
        try {
            // First, delete dependent rows in the cart_item table
            cartItemRepository.deleteById(itemId);

            // Then, delete the row in the cart table
            cartRepository.deleteById(itemId);

            return "Cart Item deleted with id " + itemId;
        } catch (EmptyResultDataAccessException e) {
            throw new CartItemNotFoundException("Cart item with ID " + itemId + " does not exist.");
        }
    }
}
