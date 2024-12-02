package com.alisievich.javafxapp.client;

import com.alisievich.javafxapp.config.AppConfig;
import com.alisievich.javafxapp.config.ConfigLoader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class BackendClient {
    private static BackendClient instance;

    private final HttpClient client;
    private final ObjectMapper objectMapper;
    private AppConfig config;

    public static BackendClient getInstance() {
        if (instance == null) {
            instance = new BackendClient();
        }

        return instance;
    }

    public BackendClient() {
        client = HttpClient.newHttpClient();

        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            config = ConfigLoader.loadConfig();
        } catch (IOException ex) {
            System.err.println("Failed to load config! Error: " + ex.getMessage());
        }
    }

    public <T> CompletableFuture<T> apiRequest(String url, Class<T> clazz) {
        CompletableFuture<T> future = new CompletableFuture<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(config.getBackend().getUrl() + url))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(responseBody -> {
                    try {
                        future.complete(objectMapper.readValue(responseBody, clazz));
                    } catch (JsonProcessingException exception) {
                        System.err.println("Failed to parse JSON: " + exception.getMessage());
                        future.completeExceptionally(exception);
                    }
                })
                .exceptionally(e -> {
                    future.completeExceptionally(e);

                    // Handle exceptions
                    return null;
                });

        return future;
    }
}
