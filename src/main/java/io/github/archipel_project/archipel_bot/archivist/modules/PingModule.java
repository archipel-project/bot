package io.github.archipel_project.archipel_bot.archivist.modules;

import io.github.archipel_project.archipel_bot.archivist.commands.Command;
import io.github.archipel_project.archipel_bot.archivist.utils.ConfigUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import java.util.concurrent.TimeUnit;

public class PingModule implements Command {
    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getDescription() {
        return "Check whether or not the bot is available.";
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        final long now = System.currentTimeMillis();

        event.replyEmbeds(this.createFirst().build()).queue(response -> {
            response.deleteOriginal().delay(2, TimeUnit.SECONDS).queue();
            event.getChannel().sendMessageEmbeds(this.createFinal(now).build()).queue();
        });
    }

    private EmbedBuilder createFirst() {
        final EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(ConfigUtils.PRIMARY_COLOR);
        embed.setTitle("Calculating latency...");

        return embed;
    }

    private EmbedBuilder createFinal(final long prev) {
        final EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(ConfigUtils.PRIMARY_COLOR);
        embed.setTitle("Pong! Latency: %d ms".formatted(System.currentTimeMillis() - prev));

        return embed;
    }
}
