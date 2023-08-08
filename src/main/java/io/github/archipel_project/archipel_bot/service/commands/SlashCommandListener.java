package io.github.archipel_project.archipel_bot.service.commands;

import io.github.archipel_project.archipel_bot.service.modules.HelpModule;
import io.github.archipel_project.archipel_bot.service.modules.PingModule;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommandListener extends ListenerAdapter {
    private final SlashCommandHandler handler;

    public SlashCommandListener(final JDA jda) {
        this.handler = new SlashCommandHandler();

        this.handler.register(new PingModule());
        this.handler.register(new HelpModule());
        this.handler.setup(jda);

        for (final String command : this.handler.getCommands().keySet()) {
            final var listener = this.handler.getCommand(command).getListener();
            if (listener == null) continue;

            jda.addEventListener(listener);
            System.out.printf("- Listener: \"%s\"\n", listener.getClass().getSimpleName());
        }
    }

    @Override
    public void onSlashCommandInteraction(final SlashCommandInteractionEvent event) {
        if (event.getUser().isBot()) return;

        this.handler.handle(event);
    }
}
