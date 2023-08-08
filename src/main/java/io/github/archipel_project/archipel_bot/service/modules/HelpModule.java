package io.github.archipel_project.archipel_bot.service.modules;

import io.github.archipel_project.archipel_bot.service.commands.SlashCommand;
import io.github.archipel_project.archipel_bot.service.miscellaneous.Emojis;
import io.github.archipel_project.archipel_bot.service.miscellaneous.embed.EmbedField;
import io.github.archipel_project.archipel_bot.service.miscellaneous.embed.EmbedHelper;
import io.github.archipel_project.archipel_bot.service.utils.ConfigUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class HelpModule implements SlashCommand {
    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Get ";
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.replyEmbeds(this.createEmbed().build()).queue();
    }

    private EmbedBuilder createEmbed() {
        final EmbedField docsField = new EmbedField(
                String.format("%sOur documentation to get started", Emojis.BOOKS.getDisplay()),
                ConfigUtils.getDocs()
        );

        final EmbedField supportField = new EmbedField(
                String.format("%sOur support server to discuss with the community", Emojis.CRYSTAL_BALL.getDisplay()),
                ConfigUtils.getHelp()
        );

        final EmbedField blogField = new EmbedField(
                String.format("%sOur dev blog to keep track of the project state", Emojis.NEWSPAPER.getDisplay()),
                ConfigUtils.getBlog()
        );

        return EmbedHelper.createEmbed(
                String.format("%sNeed help or having problems?", Emojis.TOOLS.getDisplay()),
                "I've listed some information you may need regardless of your case!",
                docsField, supportField, blogField
        );
    }
}
