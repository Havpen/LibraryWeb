package com.alisievich.javafxapp;

import com.alisievich.javafxapp.author.dto.AuthorRequestDto;
import com.alisievich.javafxapp.author.model.Author;
import com.alisievich.javafxapp.author.service.AuthorService;
import com.alisievich.javafxapp.book.book_instance.model.BookInstance;
import com.alisievich.javafxapp.book.dto.BookAuthorRequestDto;
import com.alisievich.javafxapp.book.dto.BookIdDto;
import com.alisievich.javafxapp.book.dto.BookRequestDto;
import com.alisievich.javafxapp.book.model.Book;
import com.alisievich.javafxapp.book.service.BookService;
import com.alisievich.javafxapp.genre.dto.GenreIdDto;
import com.alisievich.javafxapp.genre.dto.GenreRequestDto;
import com.alisievich.javafxapp.genre.model.Genre;
import com.alisievich.javafxapp.genre.service.GenreService;
import com.alisievich.javafxapp.publisher.dto.PublisherIdDto;
import com.alisievich.javafxapp.publisher.dto.PublisherRequestDto;
import com.alisievich.javafxapp.publisher.model.Publisher;
import com.alisievich.javafxapp.publisher.service.PublisherService;
import com.alisievich.javafxapp.reader.dto.ReaderRequestDto;
import com.alisievich.javafxapp.reader.model.Reader;
import com.alisievich.javafxapp.reader.service.ReaderService;
import com.alisievich.javafxapp.reservation.dto.ReservationRequestDto;
import com.alisievich.javafxapp.reservation.model.Reservation;
import com.alisievich.javafxapp.reservation.service.ReservationService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryManagementApp extends Application {
    private final TableView<Book> table = new TableView<>();
    private final ObservableList<Book> bookData = FXCollections.observableArrayList();
    private final BookService bookService = new BookService();
    private final PublisherService publisherService = new PublisherService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();
    private final ReaderService readerService = new ReaderService();
    private final ReservationService reservationService = new ReservationService();
    private ToolBar toolBar;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");
        Button addButton = new Button("Добавить книгу");
        Button editButton = new Button("Редактировать");
        Button deleteButton = new Button("Удалить");
        Button authorsButton = new Button("Авторы");
        Button genresButton = new Button("Жанры");
        Button publisherButton = new Button("Издатели");
        Button readersButton = new Button("Читатели");
        Button reservationButton = new Button("Резервирование");
        toolBar = new ToolBar(addButton, editButton, deleteButton);
        HBox toolBarBox = new HBox(3, authorsButton, genresButton, publisherButton, readersButton, reservationButton);
        toolBarBox.setPadding(new Insets(3));
        toolBar.getItems().add(toolBarBox);
        addButton.setOnAction(e -> showBookForm(null));
        editButton.setOnAction(e -> {
            Book selectedBook = table.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                showBookForm(selectedBook);
            } else {
                showAlert("Ошибка", "Выберите книгу для редактирования.");
            }
        });
        deleteButton.setOnAction(e -> {
            Book selectedBook = table.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                bookService.deleteBook(selectedBook.getId());
                refreshTable();
            } else {
                showAlert("Ошибка", "Выберите книгу для удаления.");
            }
        });
        authorsButton.setOnAction(e -> showAuthorsWindow());
        genresButton.setOnAction(e -> showGenresWindow());
        publisherButton.setOnAction(e->showPublishersWindow());
        readersButton.setOnAction(e -> showReadersWindow());
        reservationButton.setOnAction(e -> showReservationsWindow());
        TableColumn<Book, String> titleColumn = new TableColumn<>("Название");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Book, Integer> yearColumn = new TableColumn<>("Год издания");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        TableColumn<Book, String> languageColumn = new TableColumn<>("Язык");
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
        TableColumn<Book, String> publisherColumn = new TableColumn<>("Издатель");
        publisherColumn.setCellValueFactory(cellData -> {
            Publisher publisher = cellData.getValue().getPublisher();
            if (publisher != null) {
                return new SimpleStringProperty(publisher.getName());
            } else {
                return new SimpleStringProperty("Неизвестно");
            }
        });
        TableColumn<Book, String> genreColumn = new TableColumn<>("Жанр");
        genreColumn.setCellValueFactory(cellData -> {
            Genre genre = cellData.getValue().getGenre();
            if (genre != null) {
                return new SimpleStringProperty(genre.getName());
            } else {
                return new SimpleStringProperty("Неизвестно");
            }
        });
        TableColumn<Book, String> authorColumn = new TableColumn<>("Автор");
        authorColumn.setCellValueFactory(cellData -> {
            List<Author> authors = cellData.getValue().getAuthors();
            if (authors != null && !authors.isEmpty()) {
                return new SimpleStringProperty(authors.stream().map(Author::getName).collect(Collectors.joining(", ")));
            } else {
                return new SimpleStringProperty("Неизвестно");
            }
        });
        table.getColumns().addAll(titleColumn, yearColumn, languageColumn, publisherColumn, genreColumn, authorColumn);
        refreshTable();
        VBox layout = new VBox(toolBar, table);  // Все в одном лэйауте
        primaryStage.setScene(new Scene(layout, 800, 600));
        primaryStage.show();
    }
    private void showBookForm(Book book) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(book == null ? "Добавить книгу" : "Редактировать книгу");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);
        TextField titleInput = new TextField();
        titleInput.setPromptText("Название");
        TextField yearInput = new TextField();
        yearInput.setPromptText("Год издания");
        TextField languageInput = new TextField();
        languageInput.setPromptText("Язык");
        TextField publisherInput = new TextField();
        publisherInput.setPromptText("Издатель");
        TextField genreInput = new TextField();
        genreInput.setPromptText("Жанр");
        TextField authorInput = new TextField();
        authorInput.setPromptText("Автор");
        ComboBox<Publisher> publisherComboBox = new ComboBox<>();
        publisherComboBox.setItems(FXCollections.observableArrayList(
                publisherService.getAllPublishers().join()
        ));
        ComboBox<Genre> genreComboBox = new ComboBox<>();
        genreComboBox.setItems(FXCollections.observableArrayList(
                genreService.getAllGenres().join()
        ));
        ComboBox<Author> authorComboBox = new ComboBox<>();
        authorComboBox.setItems(FXCollections.observableArrayList(
                authorService.getAllAuthors().join()
        ));
        if (book != null) {
            titleInput.setText(book.getTitle());
            yearInput.setText(book.getYear().toString());
            languageInput.setText(book.getLanguage());
            publisherComboBox.setValue(book.getPublisher());
            genreComboBox.setValue(book.getGenre());
            authorComboBox.setValue(book.getAuthors().get(0));
        }
        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(e -> {
            PublisherIdDto publisherIdDto = new PublisherIdDto(publisherComboBox.getValue().getId());
            GenreIdDto genreIdDto = new GenreIdDto(genreComboBox.getValue().getId());
            AuthorRequestDto authorRequestDto = new AuthorRequestDto(
                    authorComboBox.getValue().getId(),
                    authorComboBox.getValue().getName()
            );
            BookAuthorRequestDto bookAuthorRequestDto = BookAuthorRequestDto.builder()
                    .author(authorRequestDto)
                    .book(new BookIdDto(null))
                    .build();
            BookRequestDto bookRequestDto = new BookRequestDto(
                    null,
                    titleInput.getText(),
                    Integer.parseInt(yearInput.getText()),
                    languageInput.getText(),
                    publisherIdDto,
                    genreIdDto,
                    List.of(bookAuthorRequestDto)
            );
            if (book == null) {
                bookService.createBook(bookRequestDto).thenAccept(newBook -> {
                    table.refresh();
                });
            } else {
                bookRequestDto.setId(book.getId());  // Для редактирования книги
                bookService.updateBook(book.getId(), bookRequestDto)
                        .thenAccept(newBook -> {
                            table.refresh();
                        });
            }
            dialog.close();
            table.refresh();
        });
        grid.add(new Label("Название:"), 0, 0);
        grid.add(titleInput, 1, 0);
        grid.add(new Label("Год издания:"), 0, 1);
        grid.add(yearInput, 1, 1);
        grid.add(new Label("Язык:"), 0, 2);
        grid.add(languageInput, 1, 2);
        grid.add(new Label("Издатель:"), 0, 3);
        grid.add(publisherComboBox, 1, 3);
        grid.add(new Label("Жанр:"), 0, 4);
        grid.add(genreComboBox, 1, 4);
        grid.add(new Label("Автор:"), 0, 5);
        grid.add(authorComboBox, 1, 5);
        grid.add(saveButton, 1, 6);
        Scene scene = new Scene(grid, 400, 300);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
    private void showAuthorsWindow() {
        Stage authorsStage = new Stage();
        authorsStage.setTitle("Список авторов");
        TableView<Author> authorsTable = new TableView<>();
        ObservableList<Author> authorData = FXCollections.observableArrayList(authorService.getAllAuthors().join());
        TableColumn<Author, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Author, String> nameColumn = new TableColumn<>("ФИО");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorsTable.getColumns().addAll(idColumn, nameColumn);
        authorsTable.setItems(authorData);
        Button addAuthorButton = new Button("Добавить");
        Button editAuthorButton = new Button("Редактировать");
        Button deleteAuthorButton = new Button("Удалить");
        addAuthorButton.setOnAction(e -> {
            showAuthorForm(null, authorData, authorsTable);
            authorsTable.refresh();
        });
        editAuthorButton.setOnAction(e -> {
            Author selectedAuthor = authorsTable.getSelectionModel().getSelectedItem();
            if (selectedAuthor != null) {
                showAuthorForm(selectedAuthor, authorData, authorsTable);
                authorsTable.refresh();
            } else {
                showAlert("Ошибка", "Выберите автора для редактирования.");
            }
        });
        deleteAuthorButton.setOnAction(e -> {
            Author selectedAuthor = authorsTable.getSelectionModel().getSelectedItem();
            if (selectedAuthor != null) {
                authorService.deleteAuthor(selectedAuthor.getId());
                authorData.setAll(authorService.getAllAuthors().join());
                authorsTable.refresh();
            } else {
                showAlert("Ошибка", "Выберите автора для удаления.");
            }
        });
        HBox buttonsBox = new HBox(10, addAuthorButton, editAuthorButton, deleteAuthorButton);
        VBox authorsLayout = new VBox(10, authorsTable, buttonsBox);
        authorsLayout.setPadding(new Insets(10));
        authorsStage.setScene(new Scene(authorsLayout, 400, 300));
        authorsStage.show();
    }
    private void showAuthorForm(Author author, ObservableList<Author> authorData, TableView<Author> authorTableView) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(author == null ? "Добавить автора" : "Редактировать автора");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);
        TextField nameInput = new TextField();
        nameInput.setPromptText("ФИО");
        if (author != null) {
            nameInput.setText(author.getName());
        }
        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(e -> {
            String name = nameInput.getText().trim();
            if (name.isEmpty()) {
                showAlert("Ошибка", "Поле 'ФИО' не может быть пустым.");
                return;
            }
            AuthorRequestDto authorRequestDto = new AuthorRequestDto(null, name);
            if (author == null) {
                authorService.createAuthor(authorRequestDto)
                        .thenAccept(savedAuthor -> {
                            authorData.setAll(authorService.getAllAuthors().join());
                            authorTableView.refresh();
                        });
            } else {
                authorRequestDto.setId(author.getId());
                authorService.updateAuthor(author.getId(), authorRequestDto)
                        .thenAccept(savedAuthor -> {
                            authorData.setAll(authorService.getAllAuthors().join());
                            authorTableView.refresh();
                        });
            }
            dialog.close();
        });
        grid.add(new Label("ФИО:"), 0, 0);
        grid.add(nameInput, 1, 0);
        grid.add(saveButton, 1, 1);
        Scene scene = new Scene(grid, 300, 150);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
    private void showGenresWindow() {
        Stage genresStage = new Stage();
        genresStage.setTitle("Список жанров");
        TableView<Genre> genresTable = new TableView<>();
        ObservableList<Genre> genreData = FXCollections.observableArrayList(genreService.getAllGenres().join());
        TableColumn<Genre, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Genre, String> nameColumn = new TableColumn<>("Название");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genresTable.getColumns().addAll(idColumn, nameColumn);
        genresTable.setItems(genreData);
        Button addGenreButton = new Button("Добавить");
        Button editGenreButton = new Button("Редактировать");
        Button deleteGenreButton = new Button("Удалить");
        addGenreButton.setOnAction(e -> {
            showGenreForm(null, genreData, genresTable);
            genresTable.refresh();
        });
        editGenreButton.setOnAction(e -> {
            Genre selectedGenre = genresTable.getSelectionModel().getSelectedItem();
            if (selectedGenre != null) {
                showGenreForm(selectedGenre, genreData, genresTable);
                genresTable.refresh();
            } else {
                showAlert("Ошибка", "Выберите жанр для редактирования.");
            }
        });
        deleteGenreButton.setOnAction(e -> {
            Genre selectedGenre = genresTable.getSelectionModel().getSelectedItem();
            if (selectedGenre != null) {
                genreService.deleteGenre(selectedGenre.getId());
                genreData.setAll(genreService.getAllGenres().join());
                genresTable.refresh();
            } else {
                showAlert("Ошибка", "Выберите жанр для удаления.");
            }
        });
        HBox buttonsBox = new HBox(10, addGenreButton, editGenreButton, deleteGenreButton);
        VBox genresLayout = new VBox(10, genresTable, buttonsBox);
        genresLayout.setPadding(new Insets(10));
        genresStage.setScene(new Scene(genresLayout, 400, 300));
        genresStage.show();
    }
    private void showGenreForm(Genre genre, ObservableList<Genre> genreData, TableView<Genre> genreTableView) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(genre == null ? "Добавить жанр" : "Редактировать жанр");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);
        TextField nameInput = new TextField();
        nameInput.setPromptText("Название жанра");
        if (genre != null) {
            nameInput.setText(genre.getName());
        }
        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(e -> {
            String name = nameInput.getText().trim();
            if (name.isEmpty()) {
                showAlert("Ошибка", "Поле 'Название жанра' не может быть пустым.");
                return;
            }
            GenreRequestDto genreRequestDto = new GenreRequestDto(null, name);
            if (genre == null) {
                genreService.createGenre(genreRequestDto).thenAccept(savedGenre-> {
                   genreData.setAll(genreService.getAllGenres().join());
                   genreTableView.refresh();
                });
            } else {
                genreRequestDto.setId(genre.getId());
                genreService.updateGenre(genre.getId(), genreRequestDto).thenAccept(savedGenre->{
                   genreData.setAll(genreService.getAllGenres().join());
                   genreTableView.refresh();
                });
            }
            dialog.close();
        });
        grid.add(new Label("Название жанра:"), 0, 0);
        grid.add(nameInput, 1, 0);
        grid.add(saveButton, 1, 1);
        Scene scene = new Scene(grid, 300, 150);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
    private void showPublishersWindow() {
        Stage publishersStage = new Stage();
        publishersStage.setTitle("Список издателей");
        TableView<Publisher> publishersTable = new TableView<>();
        ObservableList<Publisher> publisherData = FXCollections.observableArrayList(publisherService.getAllPublishers().join());
        TableColumn<Publisher, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Publisher, String> nameColumn = new TableColumn<>("Название");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Publisher, String> addressColumn = new TableColumn<>("Адрес");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        publishersTable.getColumns().addAll(idColumn, nameColumn, addressColumn);
        publishersTable.setItems(publisherData);
        Button addPublisherButton = new Button("Добавить");
        Button editPublisherButton = new Button("Редактировать");
        Button deletePublisherButton = new Button("Удалить");
        addPublisherButton.setOnAction(e -> {
            showPublisherForm(null, publisherData, publishersTable);
            publishersTable.refresh();
        });
        editPublisherButton.setOnAction(e -> {
            Publisher selectedPublisher = publishersTable.getSelectionModel().getSelectedItem();
            if (selectedPublisher != null) {
                showPublisherForm(selectedPublisher, publisherData, publishersTable);
                publishersTable.refresh();
            } else {
                showAlert("Ошибка", "Выберите издателя для редактирования.");
            }
        });
        deletePublisherButton.setOnAction(e -> {
            Publisher selectedPublisher = publishersTable.getSelectionModel().getSelectedItem();
            if (selectedPublisher != null) {
                publisherService.deletePublisher(selectedPublisher.getId());
                publisherData.setAll(publisherService.getAllPublishers().join());
                publishersTable.refresh();
            } else {
                showAlert("Ошибка", "Выберите издателя для удаления.");
            }
        });
        HBox buttonsBox = new HBox(10, addPublisherButton, editPublisherButton, deletePublisherButton);
        VBox publishersLayout = new VBox(10, publishersTable, buttonsBox);
        publishersLayout.setPadding(new Insets(10));
        publishersStage.setScene(new Scene(publishersLayout, 500, 400));
        publishersStage.show();
    }
    private void showPublisherForm(Publisher publisher, ObservableList<Publisher> publisherData, TableView<Publisher> publisherTableView) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(publisher == null ? "Добавить издателя" : "Редактировать издателя");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);
        TextField nameInput = new TextField();
        nameInput.setPromptText("Название издателя");
        TextField addressInput = new TextField();
        addressInput.setPromptText("Адрес издателя");
        if (publisher != null) {
            nameInput.setText(publisher.getName());
            addressInput.setText(publisher.getAddress());
        }
        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(e -> {
            String name = nameInput.getText().trim();
            String address = addressInput.getText().trim();
            if (name.isEmpty() || address.isEmpty()) {
                showAlert("Ошибка", "Все поля должны быть заполнены.");
                return;
            }
            PublisherRequestDto publisherRequestDto = new PublisherRequestDto(null, name, address);
            if (publisher == null) {
                publisherService.createPublisher(publisherRequestDto).thenAccept(savedPublisher->{
                    publisherData.setAll(publisherService.getAllPublishers().join());
                    publisherTableView.refresh();
                });
            } else {
                publisherRequestDto.setId(publisher.getId());
                publisherService.updatePublisher(publisher.getId(), publisherRequestDto).thenAccept(savedPublisher->{
                    publisherData.setAll(publisherService.getAllPublishers().join());
                    publisherTableView.refresh();
                });
            }
            dialog.close();
        });
        grid.add(new Label("Название издателя:"), 0, 0);
        grid.add(nameInput, 1, 0);
        grid.add(new Label("Адрес издателя:"), 0, 1);
        grid.add(addressInput, 1, 1);
        grid.add(saveButton, 1, 2);
        Scene scene = new Scene(grid, 350, 200);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
    private void showReadersWindow() {
        Stage readersStage = new Stage();
        readersStage.setTitle("Список читателей");
        TableView<Reader> readersTable = new TableView<>();
        ObservableList<Reader> readerData = FXCollections.observableArrayList(readerService.getAllReaders().join());
        TableColumn<Reader, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Reader, String> nameColumn = new TableColumn<>("ФИО");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Reader, String> birthdayColumn = new TableColumn<>("Дата рождения");
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        TableColumn<Reader, String> addressColumn = new TableColumn<>("Адрес");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        TableColumn<Reader, String> phoneColumn = new TableColumn<>("Телефон");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        TableColumn<Reader, String> emailColumn = new TableColumn<>("Почта");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<Reader, String> registrationDateColumn = new TableColumn<>("Дата регистрации");
        registrationDateColumn.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        TableColumn<Reader, String> cardNumberColumn = new TableColumn<>("Номер карты");
        cardNumberColumn.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
        readersTable.getColumns().addAll(idColumn, nameColumn, birthdayColumn, addressColumn, phoneColumn, emailColumn, registrationDateColumn, cardNumberColumn);
        readersTable.setItems(readerData);
        Button addReaderButton = new Button("Добавить");
        Button editReaderButton = new Button("Редактировать");
        Button deleteReaderButton = new Button("Удалить");
        addReaderButton.setOnAction(e -> {
            showReaderForm(null, readerData, readersTable);
            readersTable.refresh();
        });
        editReaderButton.setOnAction(e -> {
            Reader selectedReader = readersTable.getSelectionModel().getSelectedItem();
            if (selectedReader != null) {
                showReaderForm(selectedReader, readerData, readersTable);
                readersTable.refresh();
            } else {
                showAlert("Ошибка", "Выберите читателя для редактирования.");
            }
        });
        deleteReaderButton.setOnAction(e -> {
            Reader selectedReader = readersTable.getSelectionModel().getSelectedItem();
            if (selectedReader != null) {
                readerService.deleteReader(selectedReader.getId());
                readerData.setAll(readerService.getAllReaders().join());
                readersTable.refresh();
            } else {
                showAlert("Ошибка", "Выберите читателя для удаления.");
            }
        });
        HBox buttonsBox = new HBox(10, addReaderButton, editReaderButton, deleteReaderButton);
        VBox readersLayout = new VBox(10, readersTable, buttonsBox);
        readersLayout.setPadding(new Insets(10));
        readersStage.setScene(new Scene(readersLayout, 600, 400));
        readersStage.show();
    }
    private void showReaderForm(Reader reader, ObservableList<Reader> readerData, TableView<Reader> readerTableView) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(reader == null ? "Добавить читателя" : "Редактировать читателя");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);
        TextField nameInput = new TextField();
        nameInput.setPromptText("ФИО");
        DatePicker birthdayInput = new DatePicker();
        TextField addressInput = new TextField();
        addressInput.setPromptText("Адрес");
        TextField phoneInput = new TextField();
        phoneInput.setPromptText("Телефон");
        TextField emailInput = new TextField();
        emailInput.setPromptText("Почта");
        DatePicker registrationDateInput = new DatePicker();
        TextField cardNumberInput = new TextField();
        cardNumberInput.setPromptText("Номер карты");
        if (reader != null) {
            nameInput.setText(reader.getName());
            birthdayInput.setValue(reader.getBirthday());
            addressInput.setText(reader.getAddress());
            phoneInput.setText(reader.getPhone());
            emailInput.setText(reader.getEmail());
            registrationDateInput.setValue(reader.getRegistrationDate());
            cardNumberInput.setText(String.valueOf(reader.getCardNumber()));
        }
        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(e -> {
            String name = nameInput.getText();
            LocalDate birthday = birthdayInput.getValue();
            String address = addressInput.getText();
            String phone = phoneInput.getText();
            String email = emailInput.getText();
            LocalDate registrationDate = registrationDateInput.getValue();
            Integer cardNumber = Integer.parseInt(cardNumberInput.getText());
            if (name.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                showAlert("Ошибка", "Все поля должны быть заполнены.");
                return;
            }
            ReaderRequestDto readerRequestDto = new ReaderRequestDto(
                    null, // ID will be generated automatically
                    name,
                    birthday,
                    address,
                    phone,
                    email,
                    registrationDate,
                    cardNumber
            );
            if (reader == null) {
                readerService.createReader(readerRequestDto).thenAccept(savedReader->{
                    readerData.setAll(readerService.getAllReaders().join());
                    readerTableView.refresh();
                });
            } else {
                readerRequestDto.setId(reader.getId());
                readerService.updateReader(reader.getId(), readerRequestDto).thenAccept(savedReader->{
                    readerData.setAll(readerService.getAllReaders().join());
                    readerTableView.refresh();
                });
            }
            dialog.close();
        });
        grid.add(new Label("ФИО:"), 0, 0);
        grid.add(nameInput, 1, 0);
        grid.add(new Label("Дата рождения:"), 0, 1);
        grid.add(birthdayInput, 1, 1);
        grid.add(new Label("Адрес:"), 0, 2);
        grid.add(addressInput, 1, 2);
        grid.add(new Label("Телефон:"), 0, 3);
        grid.add(phoneInput, 1, 3);
        grid.add(new Label("Почта:"), 0, 4);
        grid.add(emailInput, 1, 4);
        grid.add(new Label("Дата регистрации:"), 0, 5);
        grid.add(registrationDateInput, 1, 5);
        grid.add(new Label("Номер карты:"), 0, 6);
        grid.add(cardNumberInput, 1, 6);
        grid.add(saveButton, 1, 7);
        Scene scene = new Scene(grid, 400, 400);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
    private void showReservationsWindow() {
        Stage reservationsStage = new Stage();
        reservationsStage.setTitle("Список резервирований");
        TableView<Reservation> reservationsTable = new TableView<>();
        ObservableList<Reservation> reservationData = FXCollections.observableArrayList(reservationService.getAllReservations().join());
        TableColumn<Reservation, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Reservation, String> readerColumn = new TableColumn<>("Читатель");
        readerColumn.setCellValueFactory(cellData -> {
            Reader reader = cellData.getValue().getReader();
            return new SimpleStringProperty(reader != null ? reader.getName() : "Неизвестно");
        });
        TableColumn<Reservation, LocalDate> reservationDateColumn = new TableColumn<>("Дата резервирования");
        reservationDateColumn.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        TableColumn<Reservation, LocalDate> reservationDeadLineColumn = new TableColumn<>("Срок резервирования");
        reservationDeadLineColumn.setCellValueFactory(new PropertyValueFactory<>("reservationDeadLine"));
        TableColumn<Reservation, String> statusColumn = new TableColumn<>("Статус");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        TableColumn<Reservation, String> bookInstanceColumn = new TableColumn<>("Экземпляр книги");
        bookInstanceColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getBookInstance().getBook().getTitle()));
        reservationsTable.getColumns().addAll(idColumn, readerColumn, reservationDateColumn, reservationDeadLineColumn, statusColumn, bookInstanceColumn);
        reservationsTable.setItems(reservationData);
        Button addReservationButton = new Button("Добавить");
        Button editReservationButton = new Button("Редактировать");
        Button deleteReservationButton = new Button("Удалить");
        addReservationButton.setOnAction(e -> showReservationForm(null, reservationData, reservationsTable));
        editReservationButton.setOnAction(e -> {
            Reservation selectedReservation = reservationsTable.getSelectionModel().getSelectedItem();
            if (selectedReservation != null) {
                showReservationForm(selectedReservation, reservationData, reservationsTable);
            } else {
                showAlert("Ошибка", "Выберите резервирование для редактирования.");
            }
        });
        deleteReservationButton.setOnAction(e -> {
            Reservation selectedReservation = reservationsTable.getSelectionModel().getSelectedItem();
            if (selectedReservation != null) {
                reservationService.deleteReservation(selectedReservation.getId());
                reservationData.setAll(reservationService.getAllReservations().join());
            } else {
                showAlert("Ошибка", "Выберите резервирование для удаления.");
            }
        });
        HBox buttonsBox = new HBox(10, addReservationButton, editReservationButton, deleteReservationButton);
        VBox reservationsLayout = new VBox(10, reservationsTable, buttonsBox);
        reservationsLayout.setPadding(new Insets(10));
        reservationsStage.setScene(new Scene(reservationsLayout, 800, 500));
        reservationsStage.show();
    }
    private void showReservationForm(Reservation existingReservation, ObservableList<Reservation> reservationData, TableView<Reservation> reservationTableView) {
        TextField readerInput = new TextField();
        readerInput.setPromptText("Читатель");
        DatePicker reservationDatePicker = new DatePicker();
        DatePicker deadlineDatePicker = new DatePicker();
        TextField statusField = new TextField();
        statusField.setPromptText("Введите статус резервирования");
        TextField bookInput = new TextField();
        bookInput.setPromptText("Книга");
        TextField barcodeField = new TextField();
        barcodeField.setPromptText("Введите баркод");
        ComboBox<Book> bookComboBox = new ComboBox<>();
        ComboBox<Reader> readerComboBox = new ComboBox<>();
        Button saveButton = new Button("Сохранить");
        if (existingReservation != null) {
            bookComboBox.setDisable(true);
            readerComboBox.setDisable(true);
            barcodeField.setDisable(true);
        }
        VBox layout = new VBox(10, new Label("Читатель:"), readerComboBox,
                new Label("Дата резервирования:"), reservationDatePicker,
                new Label("Срок резервирования:"), deadlineDatePicker,
                new Label("Статус резервирования:"), statusField,
                new Label("Книга:"), bookComboBox, new Label("Баркод:"), barcodeField,
                saveButton);
        layout.setPadding(new Insets(10));
        Stage stage = new Stage();
        stage.setTitle(existingReservation == null ? "Добавить резервацию" : "Редактировать резервацию");
        Scene scene = new Scene(layout, 400, 500);
        stage.setScene(scene);
        stage.show();
        var books = FXCollections.observableArrayList(
                bookService.getAllBooks().join()
        );
        bookComboBox.setItems(books);
        readerComboBox.setItems(FXCollections.observableArrayList(
                readerService.getAllReaders().join()
        ));
        if (existingReservation != null) {
            readerComboBox.setValue(existingReservation.getReader());
            reservationDatePicker.setValue(existingReservation.getReservationDate());
            deadlineDatePicker.setValue(existingReservation.getReservationDeadLine());
            statusField.setText(existingReservation.getStatus());
            Book currentBook = existingReservation.getBookInstance().getBook();
            bookComboBox.setValue(currentBook);
            barcodeField.setText(existingReservation.getBookInstance().getBarcode());
        }
        saveButton.setOnAction(e -> {
            Reader selectedReader = readerComboBox.getValue();
            Book selectedBook = bookComboBox.getValue();
            LocalDate reservationDate = reservationDatePicker.getValue();
            LocalDate reservationDeadLine = deadlineDatePicker.getValue();
            String status = statusField.getText();
            String barcode = barcodeField.getText();
            if (selectedReader == null || selectedBook == null || reservationDate == null ||
                    reservationDeadLine == null || status.isEmpty() || barcode.isEmpty()) {
                showAlert("Ошибка", "Все поля должны быть заполнены.");
                return;
            }
            BookInstance bookInstance = BookInstance.builder()
                    .barcode(barcode)
                    .book(selectedBook)
                    .build();
            ReservationRequestDto reservationRequestDto = ReservationRequestDto.builder()
                    .reader(new ReaderRequestDto(selectedReader.getId(), selectedReader.getName(), selectedReader.getBirthday(), selectedReader.getAddress(), selectedReader.getPhone(), selectedReader.getEmail(), selectedReader.getRegistrationDate(), selectedReader.getCardNumber())) // Пример ReaderRequestDto
                    .reservationDate(reservationDate)
                    .reservationDeadLine(reservationDeadLine)
                    .status(status)
                    .bookInstance(bookInstance)
                    .build();
            if (existingReservation == null) {
                reservationService.createReservation(reservationRequestDto)
                        .handle((result, ex) -> {
                            if (ex != null) {
                                Platform.runLater(() -> showAlert("Ошибка", ex.getMessage()));
                                return null;
                            }
                            return result;
                        });
            } else {
                reservationRequestDto.setId(existingReservation.getId());
                reservationService.updateReservation(existingReservation.getId(), reservationRequestDto);
            }
            stage.close();
        });
    }

    public static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void refreshTable() {
        List<Book> books = bookService.getAllBooks().join(); // Получаем список книг
        bookData.setAll(books); // Обновляем ObservableList
        table.setItems(bookData); // Устанавливаем новые данные в таблицу
    }
}


