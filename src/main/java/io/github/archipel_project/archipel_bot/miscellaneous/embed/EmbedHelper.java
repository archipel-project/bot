package io.github.archipel_project.archipel_bot.miscellaneous.embed;

import io.github.archipel_project.archipel_bot.utils.ConfigUtils;
import net.dv8tion.jda.api.EmbedBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmbedHelper {
    public static EmbedBuilder createEmbed(final String title, final String description, final String thumbnail, List<EmbedField> fields) {
        final EmbedBuilder embed = new EmbedBuilder().setColor(ConfigUtils.PRIMARY_COLOR);
        setFooter(embed, "Archivist");

        if(title != null) embed.setTitle(title);
        if(description != null) embed.setDescription(description);
        if(thumbnail != null) embed.setThumbnail(thumbnail);

        if(!fields.isEmpty()) {
            fields.forEach(f -> EmbedHelper.addField(embed, f.getPrimary(), f.getSecondary(), f.isAlign()));
        }
        return embed;
    }

    public static EmbedBuilder createEmbed(final String title, final String description, final String thumbnail, EmbedField... fields) {
        return createEmbed(title, description, thumbnail, Arrays.stream(fields).toList());
    }

    public static EmbedBuilder createEmbed(final String title) {
        return EmbedHelper.createEmbed(title,null, null, new ArrayList<>());
    }

    public static EmbedBuilder createEmbed(final String title, final String description) {
        return EmbedHelper.createEmbed(title, description, null, new ArrayList<>());
    }
    public static EmbedBuilder createEmbed(final String title, final String description, EmbedField... fields) {
        return EmbedHelper.createEmbed(title, description, null, Arrays.stream(fields).toList());
    }

    public static EmbedBuilder createEmbed(final String title, EmbedField... fields) {
        return EmbedHelper.createEmbed(title, null, null, Arrays.stream(fields).toList());
    }

    public static EmbedBuilder createEmbed(final String title, final String description, final String thumbnail) {
        return EmbedHelper.createEmbed(title, description, thumbnail, new ArrayList<>());
    }

    public static void setFooter(final EmbedBuilder embed, String content) {
        embed.setFooter(content, ConfigUtils.getIcon()).setTimestamp(Instant.now());
    }

    public static void addField(final EmbedBuilder embed, final String firstValue, final String secondValue) {
        addField(embed, firstValue, secondValue);
    }

    public static void addField(final EmbedBuilder embed, final String firstValue, final String secondValue, final boolean inline) {
        embed.addField(firstValue, secondValue, inline);
    }

    public static void addBulletField(final EmbedBuilder embed, final String firstValue, final String secondValue, final boolean inline) {
        embed.addField(firstValue, "• " + secondValue, inline);
    }

    public static void addBulletField(final EmbedBuilder embed, final String firstValue, final String secondValue) {
        addBulletField(embed, firstValue, secondValue, false);
    }
}
