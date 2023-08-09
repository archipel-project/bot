package io.github.archipel_project.archipel_bot.utils;

import com.google.gson.Gson;

import java.time.format.DateTimeFormatter;

public class ConstantUtils {
    public static final Gson GSON = new Gson();

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
