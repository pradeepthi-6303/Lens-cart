package com.capgemini.lenscart.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.lenscart.Dto.CartItemDto;
import com.capgemini.lenscart.Entities.Cart;
import com.capgemini.lenscart.Entities.CartItem;
import com.capgemini.lenscart.Service.ICartItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    private static final Logger logger = LoggerFactory.getLogger(CartItemController.class);

    @Autowired
    private ICartItemService cartItemService;

    @PostMapping("/add")
    public ResponseEntity<CartItem> addCartItem(@Valid @RequestBody CartItemDto cartItemDto) {
        logger.info("Received request to add cart item: {}", cartItemDto);
        try {
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(cartItemDto.getQuantity());
            cartItem.setCustomerId(cartItemDto.getCustomerId());

            // Create and set the Cart from CartDto
            Cart cart = new Cart();
            cart.setName(cartItemDto.getCart().getName());
            cart.setBrand(cartItemDto.getCart().getBrand());
            cart.setPrice(cartItemDto.getCart().getPrice());
            cart.setImage(cartItemDto.getCart().getImage());
            cart.setCustomerId(cartItemDto.getCart().getCustomerId());

            // Calculate and set the total price
            double total = cart.getPrice() * cartItem.getQuantity();
            cart.setTotal(total);

            cartItem.setCart(cart);

            CartItem savedCartItem = cartItemService.addCartItem(cartItem);
            logger.info("Cart item added successfully: {}", savedCartItem);
            return new ResponseEntity<>(savedCartItem, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error adding cart item: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable int id) {
        logger.info("Fetching cart item with ID: {}", id);
        Optional<CartItem> cartItem = cartItemService.getCartItemById(id);
        return cartItem.map(cart -> {
            logger.info("Cart item found: {}", cart);
            return ResponseEntity.ok(cart);
        }).orElseGet(() -> {
            logger.warn("Cart item not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        });
    }

    @GetMapping("/all")
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        logger.info("Fetching all cart items");
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        logger.info("Total cart items retrieved: {}", cartItems.size());
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CartItem>> getCartsItemByCustomerId(@PathVariable int customerId) {
        logger.info("Fetching cart items for customer ID: {}", customerId);
        List<CartItem> cartItems = cartItemService.getCartsItemByCustomerId(customerId);
        logger.info("Total cart items found for customer {}: {}", customerId, cartItems.size());
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCartItem(@PathVariable int id) {
        logger.info("Request received to delete cart item with ID: {}", id);
        try {
            String message = cartItemService.deleteCartItem(id);
            logger.info("Cart item deleted successfully with ID: {}", id);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            logger.error("Error deleting cart item with ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }
}

//package com.capgemini.lenscart.Controller;
//
//
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.capgemini.lenscart.Dto.CartItemDto;
//import com.capgemini.lenscart.Entities.Cart;
//import com.capgemini.lenscart.Entities.CartItem;
//import com.capgemini.lenscart.Service.ICartItemService;
//
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/api/cart-items")
//public class CartItemController {
//
//    @Autowired
//    private ICartItemService cartItemService;
//
//    @PostMapping("/add")
//    public ResponseEntity<CartItem> addCartItem(@Valid @RequestBody CartItemDto cartItemDto) {
//        CartItem cartItem = new CartItem();
//        cartItem.setQuantity(cartItemDto.getQuantity());
//        cartItem.setCustomerId(cartItemDto.getCustomerId());
//
//        // Create and set the Cart from CartDto
//        Cart cart = new Cart();
//        cart.setName(cartItemDto.getCart().getName());
//        cart.setBrand(cartItemDto.getCart().getBrand());
//        cart.setPrice(cartItemDto.getCart().getPrice());
//        cart.setImage(cartItemDto.getCart().getImage());
//        cart.setCustomerId(cartItemDto.getCart().getCustomerId());
//
//        // Calculate and set the total price
//        double total = cart.getPrice() * cartItem.getQuantity();
//        cart.setTotal(total);
//
//        cartItem.setCart(cart);
//        
//        CartItem savedCartItem = cartItemService.addCartItem(cartItem);
//        return new ResponseEntity<>(savedCartItem, HttpStatus.CREATED);
//    }
//
//
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CartItem> getCartItemById(@PathVariable int id) {
//        Optional<CartItem> cartItem = cartItemService.getCartItemById(id);
//        return cartItem.map(ResponseEntity::ok)
//                       .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("all")
//    public ResponseEntity<List<CartItem>> getAllCartItems() {
//        List<CartItem> cartItems = cartItemService.getAllCartItems();
//        return ResponseEntity.ok(cartItems);
//    }
//
//    @GetMapping("/customer/{customerId}")
//    public ResponseEntity<List<CartItem>> getCartsItemByCustomerId(@PathVariable int customerId) {
//        List<CartItem> cartItems = cartItemService.getCartsItemByCustomerId(customerId);
//        return ResponseEntity.ok(cartItems);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteCartItem(@PathVariable int id) {
//        String message = cartItemService.deleteCartItem(id);
//        return ResponseEntity.ok(message);
//    }
//}