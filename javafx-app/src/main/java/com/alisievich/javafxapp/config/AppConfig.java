package com.alisievich.javafxapp.config;

public class AppConfig {
    private BackendConfig backend;

    public AppConfig() {

    }

    public void setBackend(BackendConfig backend) {
        this.backend = backend;
    }

    public BackendConfig getBackend() {
        return backend;
    }
}
