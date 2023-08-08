package io.github.archipel_project.archipel_bot.service.commands;

import io.github.archipel_project.archipel_bot.service.miscellaneous.embed.EmbedField;
import io.github.archipel_project.archipel_bot.service.miscellaneous.embed.EmbedHelper;
import io.github.archipel_project.archipel_bot.service.miscellaneous.Emojis;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlashCommandHandler {
    private final Map<String, SlashCommand> commands = new HashMap<>();

    public void register(final SlashCommand command) {
        this.commands.putIfAbsent(command.getCommand(), command);
    }

    public void handle(final SlashCommandInteractionEvent event) {
        final var command = this.getCommand(event.getName());
        if(command == null) return;

        if (!this.hasPermission(event, command)) {
            this.displayPermissionError(event);
            return;
        }

        try { command.handle(event); }
        catch (Exception e) { this.displayHandleError(event, e); }
    }

    public void finalise(final JDA jda) {
        final List<CommandData> commands = new ArrayList<>();

        this.getCommands().forEach((k, v) -> {
            if (v.getArgs().isEmpty()) {
                commands.add(Commands.slash(v.getCommand(), v.getDescription()));
            } else {
                final var options = v.getArgs().stream().map(arg -> new OptionData(arg.type(), arg.name(), arg.description(), arg.required())).toList();
                commands.add(Commands.slash(v.getCommand(), v.getDescription()).addOptions(options));
            }

            System.out.printf("- Registered: \"/%s\" with %d arguments\n", v.getCommand(), v.getArgs().size());
        });

        jda.updateCommands().addCommands(commands).queue();
    }

    void displayHandleError(final SlashCommandInteractionEvent event, final Exception e) {
        final EmbedField embedField = new EmbedField(
            String.format("%sException", Emojis.RED_SQUARE.getDisplay()),
            e.getMessage()
        );

        final EmbedBuilder embed = EmbedHelper.createEmbed(
            String.format("%sError occurred while executing this command!\n", Emojis.TOOLS.getDisplay()),
            embedField
        );

        event.replyEmbeds(embed.build()).setEphemeral(true).queue();
    }

    void displayPermissionError(final SlashCommandInteractionEvent event) {
        final EmbedField embedField = new EmbedField(
            "You are missing permission for this command!",
            "If you think it's a mistake, please contact our support team..."
        );

        final EmbedBuilder embed = EmbedHelper.createEmbed(
            "You don't have permission to do that!",
            embedField
        );

        event.replyEmbeds(embed.build()).setEphemeral(true).queue();
    }

    boolean hasPermission(final SlashCommandInteractionEvent event, final SlashCommand command) {
        return event.getMember().hasPermission(command.permissionsNeeded());
    }

    public SlashCommand getCommand(final String name) {
        return this.commands.get(name);
    }

    public Map<String, SlashCommand> getCommands() {
        return this.commands;
    }
}
