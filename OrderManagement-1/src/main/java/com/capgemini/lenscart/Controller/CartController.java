package com.capgemini.lenscart.Controller;

import com.capgemini.lenscart.Dto.CartDto;
import com.capgemini.lenscart.Entities.Cart;
import com.capgemini.lenscart.Exception.CartItemNotFoundException;
import com.capgemini.lenscart.Exception.CartNotFoundException;
import com.capgemini.lenscart.Service.CartServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
@Validated
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartServiceImpl cartService;

    @PostMapping("/add")
    public ResponseEntity<Cart> addCart(@RequestBody @Valid CartDto cartDto) {
        logger.info("Received request to add cart: {}", cartDto);
        try {
            Cart cart = new Cart();
            cart.setName(cartDto.getName());
            cart.setBrand(cartDto.getBrand());
            cart.setPrice(cartDto.getPrice());
            cart.setImage(cartDto.getImage());
            cart.setCustomerId(cartDto.getCustomerId());

            Cart savedCart = cartService.addCart(cart);
            logger.info("Cart added successfully: {}", savedCart);
            return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error adding cart: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Cart> getCartById(@PathVariable int itemId) {
        logger.info("Fetching cart with ID: {}", itemId);
        Optional<Cart> cart = cartService.getCartById(itemId);
        return cart.map(cartData -> {
            logger.info("Cart found: {}", cartData);
            return ResponseEntity.ok(cartData);
        }).orElseThrow(() -> {
            logger.warn("Cart not found with ID: {}", itemId);
            return new CartNotFoundException("Cart not found with ID: " + itemId);
        });
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cart>> getAllCarts() {
        logger.info("Fetching all carts");
        List<Cart> carts = cartService.getAllCarts();
        logger.info("Total carts retrieved: {}", carts.size());
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Cart>> getCartsByCustomerId(@PathVariable int customerId) {
        logger.info("Fetching carts for customer ID: {}", customerId);
        List<Cart> carts = cartService.getCartsByCustomerId(customerId);
        logger.info("Total carts found for customer {}: {}", customerId, carts.size());
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteCart(@PathVariable int itemId) {
        logger.info("Request received to delete cart with ID: {}", itemId);
        try {
            boolean isDeleted = cartService.deleteCart(itemId);
            if (isDeleted) {
                logger.info("Cart deleted successfully with ID: {}", itemId);
                return ResponseEntity.ok().body("Cart item deleted successfully");
            } else {
                logger.warn("Cart item with ID {} not found", itemId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart item with ID " + itemId + " not found");
            }
        } catch (CartItemNotFoundException e) {
            logger.warn("Cart item not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error occurred while deleting cart: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }
}






















//package com.capgemini.lenscart.Controller;
//
//import com.capgemini.lenscart.Dto.CartDto;
//import com.capgemini.lenscart.Entities.Cart;
//import com.capgemini.lenscart.Entities.CartItem;
//import com.capgemini.lenscart.Exception.CartItemNotFoundException;
//import com.capgemini.lenscart.Exception.CartNotFoundException;
//import com.capgemini.lenscart.Service.CartServiceImpl;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/carts")
//@Validated
//public class CartController {
//    
//    @Autowired
//    private CartServiceImpl cartService;
//    
//    @PostMapping("/add")
//    public ResponseEntity<Cart> addCart(@RequestBody @Valid CartDto cartDto) {
//        try {
//            Cart cart = new Cart();
//            cart.setName(cartDto.getName());
//            cart.setBrand(cartDto.getBrand());
//            cart.setPrice(cartDto.getPrice());
//            cart.setImage(cartDto.getImage());
//            cart.setCustomerId(cartDto.getCustomerId());
//
//            Cart savedCart = cartService.addCart(cart);
//            return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
//        } catch (Exception e) {
//            System.err.println("Error adding cart: " + e.getMessage());
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/{itemId}")
//    public ResponseEntity<Cart> getCartById(@PathVariable int itemId) {
//        Optional<Cart> cart = cartService.getCartById(itemId);
//        return cart.map(ResponseEntity::ok)
//                   .orElseThrow(() -> new CartNotFoundException("Cart not found with ID: " + itemId));
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<Cart>> getAllCarts() {
//        List<Cart> carts = cartService.getAllCarts();
//        return new ResponseEntity<>(carts, HttpStatus.OK);
//    }
//
//    @GetMapping("/customer/{customerId}")
//    public ResponseEntity<List<Cart>> getCartsByCustomerId(@PathVariable int customerId) {
//        List<Cart> carts = cartService.getCartsByCustomerId(customerId);
//        return new ResponseEntity<>(carts, HttpStatus.OK);
//    }
//    @DeleteMapping("/{itemId}")
//    public ResponseEntity<?> deleteCart(@PathVariable int itemId) {
//        try {
//            boolean isDeleted = cartService.deleteCart(itemId);
//            if (isDeleted) {
//                return ResponseEntity.ok().body("Cart item deleted successfully");
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart item with ID " + itemId + " not found");
//            }
//        } catch (CartItemNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
//        }
//    }
//   
//}




