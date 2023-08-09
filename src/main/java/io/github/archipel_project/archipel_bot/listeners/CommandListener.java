package io.github.archipel_project.archipel_bot.listeners;

import io.github.archipel_project.archipel_bot.Archivist;
import io.github.archipel_project.archipel_bot.commands.Command;
import io.github.archipel_project.archipel_bot.modules.github.GithubModule;
import io.github.archipel_project.archipel_bot.handlers.CommandHandler;
import io.github.archipel_project.archipel_bot.modules.HelpModule;
import io.github.archipel_project.archipel_bot.modules.PingModule;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class CommandListener extends ListenerAdapter {
    private final CommandHandler handler;

    public CommandListener() {
        this.handler = new CommandHandler();
    }

    public void setup() {
        final JDA jda = Archivist.get().jda();

        this.handler.register(new PingModule());
        this.handler.register(new HelpModule());
        this.handler.register(new GithubModule());

        this.handler.setup(jda);
    }

    @Override
    public void onSlashCommandInteraction(final SlashCommandInteractionEvent event) {
        if (event.getUser().isBot()) return;
        this.handler.handle(event);
    }

    public CommandHandler getHandler() {
        return this.handler;
    }

    public List<Command> getCommands() {
        return this.handler.getCommands();
    }
}
