package com.alisievich.javafxapp;

import com.alisievich.javafxapp.book.service.BookService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    private final BookService bookService;

    public HelloController() {
        bookService = new BookService();
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onLoadBooks() {
        bookService.getAllBooks()
                .thenAccept((books) -> {
                    // Update the Label on the JavaFX Application Thread
                    Platform.runLater(() -> welcomeText.setText("Books loaded!"));
                });
    }
}