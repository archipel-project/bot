package io.github.archipel_project.archipel_bot.modules;

import io.github.archipel_project.archipel_bot.commands.Command;
import io.github.archipel_project.archipel_bot.miscellaneous.Emoji;
import io.github.archipel_project.archipel_bot.miscellaneous.embed.EmbedField;
import io.github.archipel_project.archipel_bot.miscellaneous.embed.EmbedHelper;
import io.github.archipel_project.archipel_bot.utils.ConfigUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

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
        final EmbedField docsField = new EmbedField(
                String.format("%sOur documentation to get started", Emoji.BOOKS.getDisplay()),
                ConfigUtils.getDocs()
        );

        final EmbedField supportField = new EmbedField(
                String.format("%sOur support server to discuss with the community", Emoji.CRYSTAL_BALL.getDisplay()),
                ConfigUtils.getHelp()
        );

        final EmbedField blogField = new EmbedField(
                String.format("%sOur dev blog to keep track of the project state", Emoji.NEWSPAPER.getDisplay()),
                ConfigUtils.getBlog()
        );

        return EmbedHelper.createEmbed(
                String.format("%sNeed help or having problems?", Emoji.TOOLS.getDisplay()),
                "I've listed some information you may need regardless of your case!",
                docsField, supportField, blogField
        );
    }
}
