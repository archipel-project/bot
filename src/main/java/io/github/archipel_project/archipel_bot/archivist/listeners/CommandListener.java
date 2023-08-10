package io.github.archipel_project.archipel_bot.archivist.listeners;

import io.github.archipel_project.archipel_bot.archivist.Archivist;
import io.github.archipel_project.archipel_bot.archivist.commands.Command;
import io.github.archipel_project.archipel_bot.archivist.miscellaneous.Emoji;
import io.github.archipel_project.archipel_bot.archivist.modules.github.GithubModule;
import io.github.archipel_project.archipel_bot.archivist.modules.HelpModule;
import io.github.archipel_project.archipel_bot.archivist.modules.PingModule;
import io.github.archipel_project.archipel_bot.archivist.utils.ConfigUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandListener extends ListenerAdapter {
    private final Map<String, Command> commands = new HashMap<>();

    private void register(final Command command) {
        this.commands.putIfAbsent(command.getName(), command);
    }

    public void setup(final JDA jda) {
        this.register(new PingModule());
        this.register(new HelpModule());
        this.register(new GithubModule());

        final var commands = this.getCommands().stream().map(Command::create).toList();
        jda.updateCommands().addCommands(commands).queue();

        jda.addEventListener(this);
    }

    public Command getCommand(final String name) {
        return this.commands.get(name);
    }

    public List<Command> getCommands() {
        return this.commands.values().stream().toList();
    }

    @Override
    public void onSlashCommandInteraction(final SlashCommandInteractionEvent event) {
        if (event.getUser().isBot()) return;

        final var command = this.getCommand(event.getName());
        if(command == null) return;

        try { command.handle(event); }
        catch (Exception e) { this.displayHandleError(event, e); }
    }

    // JDA Utils
    void displayHandleError(final SlashCommandInteractionEvent event, final Exception e) {
        final EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(ConfigUtils.PRIMARY_COLOR);
        embed.setTitle("%s  Error occurred while executing this command!".formatted(Emoji.TOOLS.get()));
        embed.setFooter(ConfigUtils.getName(), ConfigUtils.getIcon()).setTimestamp(Instant.now());

        embed.addField(String.format("%s  Exception", Emoji.RED_SQUARE.get()), e.getMessage(), false);

        event.replyEmbeds(embed.build()).setEphemeral(true).queue();
    }
}
