package io.github.archipel_project.archipel_bot.utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.awt.*;

public class ConfigUtils {
    private static final Dotenv ENV = Dotenv.load();

    public static Color PRIMARY_COLOR = Color.decode("#11806A");

    public static String getToken() {
        return ENV.get("TOKEN");
    }

    public static String getName() {
        return ENV.get("NAME");
    }

    public static String getIcon() {
        return "https://cdn.discordapp.com/attachments/1133722789635956757/1138033055987155055/logo.png";
    }

    public static String getDocs() {
        return "https://archipel-project.github.io/docs";
    }

    public static String getBlog() {
        return "https://archipel-project.github.io/dev-blog";
    }

    public static String getHelp() {
        return "https://archipel-project.github.io/help";
    }
}
