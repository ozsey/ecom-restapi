package com.tugas.model;
import java.util.Objects;

public class Product {
    private int productId;
    private String productName;
    private double price;

    public Product() {
    }

    public Product(int productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product productId(int productId) {
        setProductId(productId);
        return this;
    }

    public Product productName(String productName) {
        setProductName(productName);
        return this;
    }

    public Product price(double price) {
        setPrice(price);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return productId == product.productId && Objects.equals(productName, product.productName) && price == product.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, price);
    }

    @Override
    public String toString() {
        return "{" +
            " productId='" + getProductId() + "'" +
            ", productName='" + getProductName() + "'" +
            ", price='" + getPrice() + "'" +
            "}";
    }
}