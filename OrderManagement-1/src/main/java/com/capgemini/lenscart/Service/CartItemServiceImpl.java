package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.lenscart.Entities.CartItem;
import com.capgemini.lenscart.Exception.CartItemNotFoundException;
import com.capgemini.lenscart.Repositories.CartItemRepository;
import com.capgemini.lenscart.Repositories.CartRepository;

@Service
public class CartItemServiceImpl implements ICartItemService {

    private static final Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);

    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartItem addCartItem(CartItem cartItem) {
        logger.info("Adding cart item: {}", cartItem);
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        logger.info("Cart item added successfully: {}", savedCartItem);
        return savedCartItem;
    }

    @Override
    public Optional<CartItem> getCartItemById(int itemId) {
        logger.info("Fetching cart item with ID: {}", itemId);
        Optional<CartItem> cartItem = cartItemRepository.findById(itemId);
        if (cartItem.isPresent()) {
            logger.info("Cart item found: {}", cartItem.get());
        } else {
            logger.warn("Cart item not found with ID: {}", itemId);
        }
        return cartItem;
    }

    @Override
    public List<CartItem> getAllCartItems() {
        logger.info("Fetching all cart items");
        List<CartItem> cartItems = cartItemRepository.findAll();
        logger.info("Total cart items retrieved: {}", cartItems.size());
        return cartItems;
    }

    @Override
    public List<CartItem> getCartsItemByCustomerId(int customerId) {
        logger.info("Fetching cart items for customer ID: {}", customerId);
        List<CartItem> cartItems = cartItemRepository.findByCustomerId(customerId);
        logger.info("Total cart items found for customer {}: {}", customerId, cartItems.size());
        return cartItems;
    }

    @Override
    public String deleteCartItem(int itemId) {
        logger.info("Attempting to delete cart item with ID: {}", itemId);
        try {
            cartItemRepository.deleteById(itemId);
            cartRepository.deleteById(itemId);
            logger.info("Cart item deleted successfully with ID: {}", itemId);
            return "Cart Item deleted with id " + itemId;
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Cart item with ID {} not found", itemId);
            throw new CartItemNotFoundException("Cart item with ID " + itemId + " does not exist.");
        } catch (Exception e) {
            logger.error("Unexpected error occurred while deleting cart item: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while deleting cart item");
        }
    }
}

//package com.capgemini.lenscart.Service;
//
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Service;
//
//import com.capgemini.lenscart.Dto.CartItemDto;
//import com.capgemini.lenscart.Entities.CartItem;
//import com.capgemini.lenscart.Exception.CartItemNotFoundException;
//import com.capgemini.lenscart.Repositories.CartItemRepository;
//import com.capgemini.lenscart.Repositories.CartRepository;
//
//@Service
//public class CartItemServiceImpl implements ICartItemService {
//
//    @Autowired
//    private CartItemRepository cartItemRepository;
//    
//    @Autowired
//    private CartRepository cartRepository;
//
//    @Override
//    public CartItem addCartItem(CartItem cartItem) {
//        return cartItemRepository.save(cartItem);
//    }
//
//    @Override
//    public Optional<CartItem> getCartItemById(int itemId) {
//        return cartItemRepository.findById(itemId);
//    }
//
//    @Override
//    public List<CartItem> getAllCartItems() {
//        return cartItemRepository.findAll();
//    }
//
//    @Override
//    public List<CartItem> getCartsItemByCustomerId(int customerId) {
//        return cartItemRepository.findByCustomerId(customerId);
//    }
//
////    @Override
////    public void deleteCartItem(int itemId) {
////        cartItemRepository.deleteById(itemId);
////    }
//    @Override
//    public String deleteCartItem(int itemId) {
//        try {
//            // First, delete dependent rows in the cart_item table
//            cartItemRepository.deleteById(itemId);
//
//            // Then, delete the row in the cart table
//            cartRepository.deleteById(itemId);
//
//            return "Cart Item deleted with id " + itemId;
//        } catch (EmptyResultDataAccessException e) {
//            throw new CartItemNotFoundException("Cart item with ID " + itemId + " does not exist.");
//        }
//    }
//
//	public CartItemDto updateCartItem(CartItemDto cartItemDto) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
//
//
//
//
//
//
