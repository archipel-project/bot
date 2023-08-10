package io.github.archipel_project.archipel_bot.archivist.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface Command {
    String getName();

    String getDescription();

    default List<SubCommand> getSubCommands() {
        return new ArrayList<>();
    }

    default List<CommandArg> getArgs() {
        return new ArrayList<>();
    }

    default Collection<Permission> getPermissions() {
        return Collections.emptyList();
    }

    default List<ListenerAdapter> getListeners(){
        return new ArrayList<>();
    }

    void handle(final SlashCommandInteractionEvent event);

    default SlashCommandData create() {
        final var command = Commands.slash(this.getName(), this.getDescription());
        command.setDefaultPermissions(DefaultMemberPermissions.enabledFor(this.getPermissions()));
        this.getSubCommands().forEach(c -> command.addSubcommands(c.create()));
        return command;
    }
}
