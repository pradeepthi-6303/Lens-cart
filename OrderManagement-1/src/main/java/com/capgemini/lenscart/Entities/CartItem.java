package com.capgemini.lenscart.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

@Entity
public class CartItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartItemId")
    private int cartItemId;

    @Positive(message = "Quantity must be a positive value")
    private int quantity;  // Quantity of the item in the cart

    private int customerId;

    // Many-to-one relationship with Cart
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "itemId") // Ensure the column name matches the actual column in the Cart table
    private Cart cart;

    // Default constructor
    public CartItem() {
        super();
    }

    // Constructor with parameters
    public CartItem(int cartItemId, int quantity, int customerId) {
        this.cartItemId = cartItemId;
        this.quantity = quantity;
        this.customerId = customerId;
    }

    // Getters and Setters
    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

//	public Object getCategoryId() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
}
