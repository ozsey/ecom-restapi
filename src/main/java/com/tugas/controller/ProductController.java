package com.tugas.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tugas.model.CartItem;
import com.tugas.model.Category;
import com.tugas.model.Product;

@Path("/products")
public class ProductController {

    // In-memory storage for products and categories
    private static List<Product> products = new ArrayList<>();
    private static List<Category> categories = new ArrayList<>();
    private static Map<Integer, List<CartItem>> cart = new HashMap<>();
    private static Map<Integer, List<Product>> categoryProducts = new HashMap<>();

    static {
        // Sample data for categories
        categories.add(new Category(1, "Electronics"));
        categories.add(new Category(2, "Books"));
        categories.add(new Category(3, "Clothing"));
        categories.add(new Category(4, "Home Appliances"));
        categories.add(new Category(5, "Sports"));
        categories.add(new Category(6, "Toys"));

        // Sample data for products
        products.add(new Product(1, "Laptop", 1000.0));
        products.add(new Product(2, "Smartphone", 500.0));
        products.add(new Product(3, "Novel", 20.0));
        products.add(new Product(4, "T-Shirt", 15.0));
        products.add(new Product(5, "Refrigerator", 700.0));
        products.add(new Product(6, "Microwave", 100.0));
        products.add(new Product(7, "Soccer Ball", 25.0));
        products.add(new Product(8, "Action Figure", 30.0));
        products.add(new Product(9, "Textbook", 50.0));
        products.add(new Product(10, "Jacket", 60.0));

        // Assign products to categories
        addProductToCategory(1, 1); // Laptop to Electronics
        addProductToCategory(2, 1); // Smartphone to Electronics
        addProductToCategory(3, 2); // Novel to Books
        addProductToCategory(4, 3); // T-Shirt to Clothing
        addProductToCategory(5, 4); // Refrigerator to Home Appliances
        addProductToCategory(6, 4); // Microwave to Home Appliances
        addProductToCategory(7, 5); // Soccer Ball to Sports
        addProductToCategory(8, 6); // Action Figure to Toys
        addProductToCategory(9, 2); // Textbook to Books
        addProductToCategory(10, 3); // Jacket to Clothing
    }

    private static void addProductToCategory(int productId, int categoryId) {
        categoryProducts.putIfAbsent(categoryId, new ArrayList<>());
        for (Product product : products) {
            if (product.getProductId() == productId) {
                categoryProducts.get(categoryId).add(product);
                break;
            }
        }
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static Map<Integer, List<CartItem>> getCart() {
        return cart;
    }

    @GET
    @Path("/{category_id}")
    public Response getProductsByCategory(@PathParam("category_id") int categoryId) {
        List<Product> productsByCategory = categoryProducts.get(categoryId);

        if (productsByCategory == null || productsByCategory.isEmpty()) {
            return Response.status(404).entity("No products found for category " + categoryId).build();
        }

        return Response.status(200).entity(productsByCategory).build();
    }

    @GET
    @Path("/details/{product_id}")
    public Response getProductDetails(@PathParam("product_id") int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return Response.status(200).entity(product).build();
            }
        }
        return Response.status(404).entity("Product not found").build();
    }

    @POST
    @Path("/add-to-cart")
    public Response addToCart(CartItem cartItem) {
        boolean productExists = false;

        for (Product product : products) {
            if (product.getProductId() == cartItem.getProductId()) {
                productExists = true;
                break;
            }
        }

        if (!productExists) {
            return Response.status(404).entity("Product not found").build();
        }

        List<CartItem> cartItems = cart.getOrDefault(cartItem.getProductId(), new ArrayList<>());

        boolean itemUpdated = false;
        for (CartItem item : cartItems) {
            if (item.getProductId() == cartItem.getProductId()) {
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                itemUpdated = true;
                break;
            }
        }

        if (!itemUpdated) {
            cartItems.add(cartItem);
        }

        cart.put(cartItem.getProductId(), cartItems);

        return Response.status(200).entity("Product added to cart successfully.").build();
    }
}