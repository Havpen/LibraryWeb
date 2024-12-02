package com.alisievich.javafxapp;

import com.alisievich.javafxapp.config.AppConfig;
import com.alisievich.javafxapp.config.ConfigLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HelloController {
    @FXML
    private Label welcomeText;

    private final HttpClient client;
    private AppConfig config;

    public HelloController() {
        client = HttpClient.newHttpClient();

        try {
            config = ConfigLoader.loadConfig();
        } catch (IOException ex) {
            System.err.println("Failed to load config! Error: " + ex.getMessage());
        }
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onLoadBooks() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(config.getBackend().getUrl() + "books"))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(responseBody -> {
                    // Update UI or handle response
                    System.out.println(responseBody);
                })
                .exceptionally(e -> {
                    // Handle exceptions
                    return null;
                });
    }
}