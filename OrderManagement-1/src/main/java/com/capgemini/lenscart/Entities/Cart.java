package com.capgemini.lenscart.Entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;

@Entity
public class Cart {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemId")
    private int itemId;

    private String name;
    private String brand;
    private double price;
    private String image;
    private int customerId;
    private double total;
    
    
    
    
 public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	// One-to-many relationship with CartItem
    @OneToMany(mappedBy = "cart") // 'ca[rt' refers to the Cart object in CartItem class
    private List<CartItem> cartItems;

    public Cart() {
        super();
    }

    public Cart(int itemId, String name, String brand, double price, String image, int customerId) {
        this.itemId = itemId;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.image = image;
        this.customerId = customerId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}