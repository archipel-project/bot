package io.github.archipel_project.archipel_bot.models;

import com.google.gson.annotations.SerializedName;
import io.github.archipel_project.archipel_bot.utils.ConstantUtils;

import java.time.Instant;
import java.time.ZoneId;

public class IssueModel {
    private final String number = null;
    private final String title = null;
    @SerializedName("body") private final String content = null;
    private final String state = null;
    @SerializedName("state_reason") private final String reason = null;
    @SerializedName("html_url") private final String url = null;
    @SerializedName("created_at") private final String createdAt = null;
    @SerializedName("closed_at") private final String closedAt = null;

    public String getNumber() {
        return this.number;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public String getState() {
        return this.state;
    }

    public String getUrl() {
        return url;
    }

    public String getReason() {
        return this.reason;
    }

    public String getClosedAt() {
        final var instant = Instant.parse(this.closedAt);
        final var zone = instant.atZone(ZoneId.of("UTC"));
        return zone.format(ConstantUtils.DATE_FORMAT);
    }

    public String getCreatedAt() {
        final var instant = Instant.parse(this.createdAt);
        final var zone = instant.atZone(ZoneId.of("UTC"));
        return zone.format(ConstantUtils.DATE_FORMAT);
    }
}
