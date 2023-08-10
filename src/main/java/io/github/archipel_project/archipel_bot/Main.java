package io.github.archipel_project.archipel_bot;

import io.github.archipel_project.archipel_bot.archivist.Archivist;

public class Main {
    public static void main(String[] args) {
        try { Archivist.get().start(); }
        catch (Exception e) { e.printStackTrace(); }
    }
}