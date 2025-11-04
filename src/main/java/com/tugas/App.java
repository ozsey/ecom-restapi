/* package com.tugas;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

//import com.tugas.controller.AuthenticationController;
//import com.tugas.controller.ProductController;
//import com.tugas.controller.CartController;

import java.io.IOException;
import java.net.URI;

public class App {
    public static void main(String[] args) {
        URI baseUri = URI.create("http://localhost:5000/api/");
        ResourceConfig config = new ResourceConfig(CartController.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        
        try {
            server.start();
            System.out.println("Server running at " + baseUri);
            System.out.println("Hit enter to stop it...");
            System.in.read();
            server.shutdownNow();
        } catch (IOException e) {
            System.err.println("Error starting Grizzly HTTP server: " + e.getMessage());
        }
    }
} */

package com.tugas;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

//import com.tugas.controller.*;

import java.io.IOException;
import java.net.URI;

public class App {
    public static void main(String[] args) {
        URI baseUri = URI.create("http://localhost:5000/api/");
        ResourceConfig config = new ResourceConfig();
        config.packages("com.tugas.controller");
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        
        try {
            server.start();
            System.out.println("Server running at " + baseUri);
            System.out.println("Hit enter to stop it...");
            System.in.read();
            server.shutdownNow();
        } catch (IOException e) {
            System.err.println("Error starting Grizzly HTTP server: " + e.getMessage());
        }
    }
}