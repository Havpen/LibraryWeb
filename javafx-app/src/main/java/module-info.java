module com.alisievich.javafxapp {
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

    opens com.alisievich.javafxapp to javafx.fxml;
    exports com.alisievich.javafxapp;
    exports com.alisievich.javafxapp.config;
    exports com.alisievich.javafxapp.book.mapper;
    exports com.alisievich.javafxapp.book.model;
    exports com.alisievich.javafxapp.book.dto;
}