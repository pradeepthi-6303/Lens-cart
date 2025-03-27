package com.capgemini.lenscart.Dto;



import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CartItemDto {

    private int cartItemId; // Assuming you want to track the ID of the cart item

    @NotNull(message = "Cart customer ID should not be empty")
    @Positive(message = "customerId must be positive")
    private int customerId;

    @NotNull(message = "Cart item quantity should not be empty")
    @Positive(message = "Quantity must be positive")
    private int quantity;
    
    
    @NotNull(message = "Cart item should not be empty")
    @Valid
    private CartDto cart; 

	

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	} // Assuming you want to track the quantity of the item in the cart

	public CartDto getCart() {
		return cart;
	}

	public void setCart(CartDto cart) {
		this.cart = cart;
	}

	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	
}  