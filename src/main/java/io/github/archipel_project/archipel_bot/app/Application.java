package io.github.archipel_project.archipel_bot.app;

import io.github.archipel_project.archipel_bot.service.Service;

public class Application {
    private final Service service = new Service();

    public Service getCooper() {
        return this.service;
    }

    private static Application INSTANCE;

    public static Application get() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        INSTANCE = new Application();
    }
}
