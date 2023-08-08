package io.github.archipel_project.archipel_bot.service.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface SlashCommand {
    String getCommand();

    String getDescription();

    default List<SlashCommandArgs> getArgs() {
        return new ArrayList<>();
    }

    void handle(final SlashCommandInteractionEvent event);

    default Collection<Permission> permissionsNeeded() {
        return Collections.emptyList();
    }

    default ListenerAdapter getListener() {
        return null;
    }
}
