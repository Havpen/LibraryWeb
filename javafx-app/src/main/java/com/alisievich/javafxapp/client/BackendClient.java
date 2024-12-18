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
import java.net.http.HttpHeaders;
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
                    return null;
                });

        return future;
    }

    // Метод для получения объекта по ID (GET)
    public <T> CompletableFuture<T> getById(String url, Class<T> clazz) {
        return apiRequest(url, clazz);
    }

    // Метод для создания нового объекта (POST)
    public <TRequest, TResponse> CompletableFuture<TResponse> create(String url, TRequest requestBody, Class<TResponse> responseType) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(config.getBackend().getUrl() + url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(toJson(requestBody))) // сериализация тела запроса в JSON
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> parseJson(response.body(), responseType)); // десериализация ответа
    }

    public <TRequest, TResponse> CompletableFuture<TResponse> update(String url, TRequest requestBody, Class<TResponse> responseType) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(config.getBackend().getUrl() + url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(toJson(requestBody))) // аналогично для PUT-запроса
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> parseJson(response.body(), responseType)); // десериализация ответа
    }

    private String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize object", e);
        }
    }

    private <T> T parseJson(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize JSON", e);
        }
    }


    // Метод для удаления объекта (DELETE)
    public CompletableFuture<Void> delete(String url) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(config.getBackend().getUrl() + url))
                .DELETE()
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(responseBody -> future.complete(null))
                .exceptionally(e -> {
                    future.completeExceptionally(e);
                    return null;
                });

        return future;
    }
}

