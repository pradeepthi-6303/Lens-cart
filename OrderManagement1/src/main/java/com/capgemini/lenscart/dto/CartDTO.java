package com.capgemini.lenscart.dto;

public class CartDTO {

   // @Column(name = "itemId")
    private int itemId;

    private String name;
    private String brand;
    private double price;
    private String image;
    private int customerId;

    // One-to-many relationship with CartItem
   // @OneToMany(mappedBy = "cart") // 'ca[rt' refers to the Cart object in CartItem class
    //private List<CartItem> cartItems;





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
