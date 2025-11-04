package com.tugas.model;
import java.util.Objects;

public class CartItem {
    private int productId;
    private int quantity;

    public CartItem() {
    }

    public CartItem(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartItem productId(int productId) {
        setProductId(productId);
        return this;
    }

    public CartItem quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CartItem)) {
            return false;
        }
        CartItem cartItem = (CartItem) o;
        return productId == cartItem.productId && quantity == cartItem.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity);
    }

    @Override
    public String toString() {
        return "{" +
            " productId='" + getProductId() + "'" +
            ", quantity='" + getQuantity() + "'" +
            "}";
    }
    
}