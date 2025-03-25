package com.capgemini.lenscart.Controller;
//
//import com.capgemini.lenscart.Dto.CartItemDto;
//import com.capgemini.lenscart.Service.CartItemServiceImpl;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/cart-items")
//public class CartItemController {
//
//    private final CartItemServiceImpl cartItemService;
//
//    public CartItemController(CartItemServiceImpl cartItemService) {
//        this.cartItemService = cartItemService;
//    }
//
//    // Create a new cart item
//    @PostMapping
//    public ResponseEntity<CartItemDto> createCartItem(@RequestBody CartItemDto cartItemDto) {
//        CartItemDto createdCartItem = cartItemService.createCartItem(cartItemDto);
//        return new ResponseEntity<>(createdCartItem, HttpStatus.CREATED);
//    }
//
//    // Get all cart items
//    @GetMapping
//    public ResponseEntity<List<CartItemDto>> getAllCartItems() {
//        List<CartItemDto> cartItems = cartItemService.getAllCartItems();
//        return new ResponseEntity<>(cartItems, HttpStatus.OK);
//    }
//
//    // Get a cart item by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<CartItemDto> getCartItemById(@PathVariable Integer id) {
//        CartItemDto cartItemDto = cartItemService.getCartItemById(id);
//        if (cartItemDto != null) {
//            return new ResponseEntity<>(cartItemDto, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    // Update a cart item
//    @PutMapping("/{id}")
//    public ResponseEntity<CartItemDto> updateCartItem(@PathVariable Integer id, @RequestBody CartItemDto cartItemDto) {
//        cartItemDto.setId(id); // Set the ID for the update
//        CartItemDto updatedCartItem = cartItemService.updateCartItem(cartItemDto);
//        if (updatedCartItem != null) {
//            return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    // Delete a cart item
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCartItem(@PathVariable Integer id) {
//        boolean isDeleted = cartItemService.deleteCartItem(id);
//        if (isDeleted) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//}



import java.util.List;
import java.util.Optional;

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

    @Autowired
    private ICartItemService cartItemService;

    @PostMapping("/add")
    public ResponseEntity<CartItem> addCartItem(@Valid @RequestBody CartItemDto cartItemDto) {
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
        return new ResponseEntity<>(savedCartItem, HttpStatus.CREATED);
    }



    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable int id) {
        Optional<CartItem> cartItem = cartItemService.getCartItemById(id);
        return cartItem.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("all")
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CartItem>> getCartsItemByCustomerId(@PathVariable int customerId) {
        List<CartItem> cartItems = cartItemService.getCartsItemByCustomerId(customerId);
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCartItem(@PathVariable int id) {
        String message = cartItemService.deleteCartItem(id);
        return ResponseEntity.ok(message);
    }
}