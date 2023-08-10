package io.github.archipel_project.archipel_bot.archivist.models;

import com.google.gson.annotations.SerializedName;

public class RepositoryModel {
    private final String name = null;
    private final String description = null;
    @SerializedName("html_url") private final String url = null;

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getUrl() {
        return this.url;
    }
}
