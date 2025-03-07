package com.capgemini.lenscart.Service;

import com.capgemini.lenscart.Entities.Cart;
import com.capgemini.lenscart.Repositories.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {

    private final CartRepo cartRepository;

    @Autowired
    public CartServiceImpl(CartRepo cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> getCartById(int itemId) {
        return cartRepository.findById(itemId);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public List<Cart> getCartsByCustomerId(int customerId) {
        return cartRepository.findByCustomerId(customerId);
    }

    @Override
    public void deleteCart(int itemId) {
        cartRepository.deleteById(itemId);
    }
}