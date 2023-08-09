package io.github.archipel_project.archipel_bot.modules;

import io.github.archipel_project.archipel_bot.commands.Command;
import io.github.archipel_project.archipel_bot.miscellaneous.Emoji;
import io.github.archipel_project.archipel_bot.utils.ConfigUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.time.Instant;

public class HelpModule implements Command {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Get useful information about the project.";
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.replyEmbeds(this.createEmbed().build()).queue();
    }

    private EmbedBuilder createEmbed() {
        final EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(ConfigUtils.PRIMARY_COLOR);
        embed.setTitle("%sNeed help or having problems?".formatted(Emoji.TOOLS.getDisplay()));
        embed.setDescription("I've listed some information you may need regardless of your case!");
        embed.setFooter(ConfigUtils.getName(), ConfigUtils.getIcon()).setTimestamp(Instant.now());

        embed.addField("%sOur documentation to get started".formatted(Emoji.BOOKS.getDisplay()), ConfigUtils.getDocs(), false);
        embed.addField("%sOur support server to discuss with the community".formatted(Emoji.CRYSTAL_BALL.getDisplay()), ConfigUtils.getHelp(), false);
        embed.addField("%sOur dev blog to keep track of the project state".formatted(Emoji.NEWSPAPER.getDisplay()), ConfigUtils.getBlog(), false);

        return embed;
    }
}
