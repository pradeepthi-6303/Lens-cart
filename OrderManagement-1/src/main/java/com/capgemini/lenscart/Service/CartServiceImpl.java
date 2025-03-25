package com.capgemini.lenscart.Service;


import com.capgemini.lenscart.Entities.Cart;
import com.capgemini.lenscart.Entities.CartItem;
import com.capgemini.lenscart.Exception.CartItemNotFoundException;
import com.capgemini.lenscart.Feign.CategoryFeignClient;
import com.capgemini.lenscart.Repositories.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {
	
	@Autowired
	private CategoryFeignClient categoryFeignClient;
    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
//    public Double calculateTotalCost(List<CartItem> cartItems) {
//        double totalCost = 0.0;
//        for (CartItem cartLine : cartItems) {
//            if (cartItems.getCategoryId() == null) { // Handle null productId
//                throw new IllegalArgumentException("Product ID cannot be null");
//            }
//            List<Category> products = CategoryServiceClient.showAllProducts(cartLine.getCategoryId());
//            if (!products.isEmpty()) {
//                totalCost += products.get(0).getPrice() * cartLine.getCategoryCount();
//            }
//        }
//        return totalCost;
//    }
//    
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
    public boolean deleteCart(int itemId) {
       Optional<Cart> cartOpt = cartRepository.findById(itemId);
       if (!cartOpt.isPresent()) {
           throw new CartItemNotFoundException("Cart with ID " + itemId + " not found");
       }
       cartRepository.delete(cartOpt.get());
       return true;
    }
}