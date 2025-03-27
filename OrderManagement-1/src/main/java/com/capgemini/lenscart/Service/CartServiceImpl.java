package com.capgemini.lenscart.Service;

import com.capgemini.lenscart.Entities.Cart;
import com.capgemini.lenscart.Exception.CartItemNotFoundException;
import com.capgemini.lenscart.Repositories.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addCart(Cart cart) {
        logger.info("Adding cart: {}", cart);
        Cart savedCart = cartRepository.save(cart);
        logger.info("Cart added successfully: {}", savedCart);
        return savedCart;
    }

    @Override
    public Optional<Cart> getCartById(int itemId) {
        logger.info("Fetching cart with ID: {}", itemId);
        Optional<Cart> cart = cartRepository.findById(itemId);
        if (cart.isPresent()) {
            logger.info("Cart found: {}", cart.get());
        } else {
            logger.warn("Cart not found with ID: {}", itemId);
        }
        return cart;
    }

    @Override
    public List<Cart> getAllCarts() {
        logger.info("Fetching all carts");
        List<Cart> carts = cartRepository.findAll();
        logger.info("Total carts retrieved: {}", carts.size());
        return carts;
    }

    @Override
    public List<Cart> getCartsByCustomerId(int customerId) {
        logger.info("Fetching carts for customer ID: {}", customerId);
        List<Cart> carts = cartRepository.findByCustomerId(customerId);
        logger.info("Total carts found for customer {}: {}", customerId, carts.size());
        return carts;
    }

    @Override
    public boolean deleteCart(int itemId) {
        logger.info("Attempting to delete cart with ID: {}", itemId);
        Optional<Cart> cartOpt = cartRepository.findById(itemId);
        if (!cartOpt.isPresent()) {
            logger.warn("Cart with ID {} not found", itemId);
            throw new CartItemNotFoundException("Cart with ID " + itemId + " not found");
        }
        cartRepository.delete(cartOpt.get());
        logger.info("Cart deleted successfully with ID: {}", itemId);
        return true;
    }
}


//package com.capgemini.lenscart.Service;
//
//
//import com.capgemini.lenscart.Entities.Cart;
//import com.capgemini.lenscart.Entities.CartItem;
//import com.capgemini.lenscart.Exception.CartItemNotFoundException;
//import com.capgemini.lenscart.Feign.CategoryFeignClient;
//import com.capgemini.lenscart.Repositories.CartRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Locale.Category;
//import java.util.Optional;
//
//@Service
//public class  CartServiceImpl implements ICartService {
//	@Autowired
//	private  CartRepository cartRepository;
//	
//	
//
//   // @Autowired
//   // public Double calculateTotalCost(List<CartItem> cartItems) {
////        double totalCost = 0.0;
////
////        for (CartItem cartLine : cartItems) {
////            if (cartLine.getCategoryId() == null) {
////                throw new IllegalArgumentException("Category ID cannot be null");
////            }
////
////            List<Category> products = CategoryServiceClient.showAllProducts(cartLine.getCategoryId());
////
////            if (products == null || products.isEmpty()) {
////                throw new RuntimeException("No products found for category ID: " + cartLine.getCategoryId());
////            }
////
////            totalCost += products.get(0).getPrice() * cartLine.getCategoryCount();
////        }
////        return totalCost;
////    }
//   
//    @Override
//    public Cart addCart(Cart cart) {
//        return cartRepository.save(cart);
//    }
//
//    @Override
//    public Optional<Cart> getCartById(int itemId) {
//        return cartRepository.findById(itemId);
//    }
//
//    @Override
//    public List<Cart> getAllCarts() {
//        return cartRepository.findAll();
//    }
//
//    @Override
//    public List<Cart> getCartsByCustomerId(int customerId) {
//        return cartRepository.findByCustomerId(customerId);
//    }
//
//    @Override
//    public boolean deleteCart(int itemId) {
//       Optional<Cart> cartOpt = cartRepository.findById(itemId);
//       if (!cartOpt.isPresent()) {
//           throw new CartItemNotFoundException("Cart with ID " + itemId + " not found");
//       }
//       cartRepository.delete(cartOpt.get());
//       return true;
//    }
//   
//      
//    }
