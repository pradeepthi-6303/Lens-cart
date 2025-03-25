
package com.capgemini.lenscart.Controller;

import com.capgemini.lenscart.Dto.CartDto;
import com.capgemini.lenscart.Entities.Cart;
import com.capgemini.lenscart.Exception.CartNotFoundException;
import com.capgemini.lenscart.Service.CartServiceImpl;
import com.capgemini.lenscart.Service.ICartService;

import jakarta.validation.Valid;

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
	@Autowired
	private CartServiceImpl cartService;

    //private final ICartService cartService;
//    @Autowired
//    public CartController(ICartService cartService) {
//        this.cartService = cartService;
//    }

    // Add a new cart
//    @PostMapping("/add")
//    public <CartDto> ResponseEntity<Cart> addCart(@Validated @RequestBody CartDto cartDto) {
//        // Manually convert CartDto to Cart entity
//        Cart cart = new Cart();
//        cart.setName(((Cart) cartDto).getName());
//        cart.setBrand(((Cart) cartDto).getBrand());
//        cart.setPrice(((Cart) cartDto).getPrice());
//        cart.setImage(((Cart) cartDto).getImage());
//        cart.setCustomerId(((Cart) cartDto).getCustomerId());
//        
//        // Save the Cart
//        Cart savedCart = cartService.addCart(cart);
//        return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
//    }
    @PostMapping("/add")
    public ResponseEntity<Cart> addCart( @RequestBody @Valid CartDto cartDto) {
        try {
            // Manually convert CartDto to Cart entity
            Cart cart = new Cart();
            cart.setName(cartDto.getName());
            cart.setBrand(cartDto.getBrand());
            cart.setPrice(cartDto.getPrice());
            cart.setImage(cartDto.getImage());
            cart.setCustomerId(cartDto.getCustomerId());

            // Save the Cart
            Cart savedCart = cartService.addCart(cart);
            return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error adding cart: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Get a cart by ID
    @GetMapping("/{itemId}")
    public ResponseEntity<Cart> getCartById(@PathVariable int itemId) {
        Optional<Cart> cart = cartService.getCartById(itemId);
        if (cart.isPresent()) {
            return ResponseEntity.ok(cart.get());
        } else {
            throw new CartNotFoundException("Cart not found with ID: " + itemId);
        }
    }

    // Get all carts
    @GetMapping("/all")
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    // Get carts by customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Cart>> getCartsByCustomerId(@PathVariable int customerId) {
        List<Cart> carts = cartService.getCartsByCustomerId(customerId);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    // Delete a cart by ID
    @DeleteMapping("/{itemId}")
    public String deleteCart(@PathVariable int itemId) {
        return cartService.deleteCart(itemId)? "cart item deleted successfully": "cart not found";
        		
        
    }
}
