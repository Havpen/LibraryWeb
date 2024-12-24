module com.alisievich.javafxapp {
    exports com.alisievich.javafxapp.genre.dto to com.fasterxml.jackson.databind;
    exports com.alisievich.javafxapp.publisher.dto to com.fasterxml.jackson.databind;
    opens com.alisievich.javafxapp.publisher.dto to com.fasterxml.jackson.databind;

    requires javafx.controls;
    requires javafx.fxml;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires static lombok;
    requires org.mapstruct;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens com.alisievich.javafxapp to javafx.fxml;
    exports com.alisievich.javafxapp;
    exports com.alisievich.javafxapp.config;
    exports com.alisievich.javafxapp.book.mapper;
    exports com.alisievich.javafxapp.book.model;
    exports com.alisievich.javafxapp.book.dto;
    exports com.alisievich.javafxapp.author.dto;
    exports com.alisievich.javafxapp.author.mapper;
    exports com.alisievich.javafxapp.author.model;
    exports com.alisievich.javafxapp.author.service;
    exports com.alisievich.javafxapp.publisher.mapper;
    exports com.alisievich.javafxapp.publisher.model;
    exports com.alisievich.javafxapp.publisher.service;
    exports com.alisievich.javafxapp.genre.mapper;
    exports com.alisievich.javafxapp.genre.model;
    exports com.alisievich.javafxapp.genre.service;
    exports com.alisievich.javafxapp.reader.dto;
    exports com.alisievich.javafxapp.reader.model;
    exports com.alisievich.javafxapp.reader.service;
    exports com.alisievich.javafxapp.reader.mapper;
    exports com.alisievich.javafxapp.reservation.mapper;
    exports com.alisievich.javafxapp.reservation.dto;
    exports com.alisievich.javafxapp.reservation.service;
    exports com.alisievich.javafxapp.reservation.model;
    exports com.alisievich.javafxapp.issue.dto;
    exports com.alisievich.javafxapp.issue.model;
    exports com.alisievich.javafxapp.issue.service;
    exports com.alisievich.javafxapp.issue.mapper;
    exports com.alisievich.javafxapp.book.book_instance.model;
    exports com.alisievich.javafxapp.book.book_instance.dto;
    exports com.alisievich.javafxapp.book.book_instance.mapper;
    exports com.alisievich.javafxapp.book.book_instance.service;
}