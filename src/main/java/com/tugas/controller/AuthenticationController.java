package com.tugas.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.ArrayList;


import com.tugas.model.User;

@Path("/auth")
public class AuthenticationController {
    private static List<User> registeredUsers = new ArrayList<>();
    public AuthenticationController() {
        registeredUsers.add(new User("user1@example.com", "password1", "John Doe"));
        registeredUsers.add(new User("user2@example.com", "password2", "Jane Smith"));
    }

    @POST
    @Path("/register")
    public Response register(User user) {
        for (User u : registeredUsers) {
            if (u.getEmail().equals(user.getEmail())) {
                return Response.status(400).entity("Email already registered.").build();
            }
        }
        registeredUsers.add(user);
        return Response.status(201).entity("User registered successfully.").build();
    }

    @POST
    @Path("/login")
    public Response login(User user) {
        for (User u : registeredUsers) {
            if (u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword())) {
                return Response.status(200).entity("Login successful.").build();
            }
        }
        return Response.status(401).entity("Login failed. Invalid email or password.").build();
    }
}