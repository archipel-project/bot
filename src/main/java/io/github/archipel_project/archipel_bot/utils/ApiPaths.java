package io.github.archipel_project.archipel_bot.utils;

public class ApiPaths {
    public static final String searchIssuePath = "https://api.github.com/search/issues?q=%s+repo:%s/%s";
    public static final String pullPath = "https://api.github.com/repos/%s/%s/pulls/%d";
    public static final String reposPath = "https://api.github.com/orgs/%s/repos";
}
