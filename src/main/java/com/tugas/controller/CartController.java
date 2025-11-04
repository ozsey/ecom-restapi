package com.tugas.controller;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tugas.model.CartItem;

@Path("/cart")
public class CartController {

    private static Map<Integer, List<CartItem>> cart = ProductController.getCart();

    @GET
    public Response viewCart() {
        List<CartItem> allCartItems = new ArrayList<>();
        for (List<CartItem> items : cart.values()) {
            allCartItems.addAll(items);
        }

        if (allCartItems.isEmpty()) {
            return Response.status(404).entity("Cart is empty").build();
        }

        return Response.status(200).entity(allCartItems).build();
    }

    @POST
    @Path("/update")
    public Response updateCartItem(CartItem cartItem) {
        List<CartItem> cartItems = cart.get(cartItem.getProductId());

        if (cartItems == null) {
            return Response.status(404).entity("Product not found in cart").build();
        }

        boolean itemUpdated = false;
        for (CartItem item : cartItems) {
            if (item.getProductId() == cartItem.getProductId()) {
                item.setQuantity(cartItem.getQuantity());
                itemUpdated = true;
                break;
            }
        }

        if (!itemUpdated) {
            return Response.status(404).entity("Product not found in cart").build();
        }

        return Response.status(200).entity("Cart item updated successfully.").build();
    }

    @DELETE
    @Path("/remove/{product_id}")
    public Response removeCartItem(@PathParam("product_id") int productId) {
        List<CartItem> cartItems = cart.get(productId);

        if (cartItems == null || cartItems.isEmpty()) {
            return Response.status(404).entity("Product not found in cart").build();
        }

        cart.remove(productId);
        return Response.status(200).entity("Cart item removed successfully.").build();
    }

    @POST
    @Path("/checkout")
    public Response checkout() {
        List<CartItem> allCartItems = new ArrayList<>();
        for (List<CartItem> items : cart.values()) {
            allCartItems.addAll(items);
        }

        if (allCartItems.isEmpty()) {
            return Response.status(404).entity("Cart is empty").build();
        }

        // Simulate checkout process
        cart.clear();
        return Response.status(200).entity("Checkout process completed.").build();
    }
}