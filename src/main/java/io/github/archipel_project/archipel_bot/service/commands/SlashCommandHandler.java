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
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlashCommandHandler {
    private final Map<String, SlashCommand> commands = new HashMap<>();

    public void register(final SlashCommand command) {
        if (!this.commands.containsKey(command.getCommand())) {
            this.commands.put(command.getCommand(), command);
        }
    }

    public void handle(final SlashCommandInteractionEvent event) {
        final String invoke = event.getName();

        if (this.commands.containsKey(invoke)) {
            final var command = getCommand(invoke);
            if (!this.displayPermissionError(event, command)) {
                try {
                    command.handle(event);
                } catch (Exception e) {
                    this.displayHandleError(event, e);
                }
            }
        }
    }


    public SlashCommand getCommand(final String name) {
        return this.commands.get(name);
    }

    public Map<String, SlashCommand> getCommands() {
        return this.commands;
    }

    public void setup(final JDA jda) {
        List<CommandData> commands = new ArrayList<>();

        this.getCommands().forEach((name, slashCommand) -> {
            if (slashCommand.getArgs().isEmpty()) {
                commands.add(Commands.slash(slashCommand.getCommand(), slashCommand.getDescription()));
                System.out.printf(" - Registered: \"/%s\"\n", slashCommand.getCommand());
            } else {
                final SlashCommandData command = Commands.slash(slashCommand.getCommand(), slashCommand.getDescription());
                final List<OptionData> options = new ArrayList<>();

                for (final var arg : slashCommand.getArgs()) {
                    options.add(new OptionData(arg.type(), arg.name(), arg.description(), arg.required()));
                }

                command.addOptions(options);
                commands.add(command);
                System.out.printf("- Registered: \"/%s\" with %d arguments\n", slashCommand.getCommand(), slashCommand.getArgs().size());
            }
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

    boolean displayPermissionError(final SlashCommandInteractionEvent event, final SlashCommand command) {
        final var member = event.getMember();
        if(member == null) return false;

        if (!event.getMember().hasPermission(command.permissionsNeeded())) {
            final EmbedField embedField = new EmbedField(
                    "You are missing permission for this command!",
                    "If you think it's a mistake, please contact our support team..."
            );

            final EmbedBuilder embed = EmbedHelper.createEmbed(
                    "You don't have permission to do that!",
                    embedField
            );

            event.replyEmbeds(embed.build()).setEphemeral(true).queue();
            return true;
        }
        return false;
    }
}
