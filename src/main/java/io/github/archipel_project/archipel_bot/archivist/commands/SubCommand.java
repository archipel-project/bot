package io.github.archipel_project.archipel_bot.archivist.commands;

import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

import java.util.ArrayList;
import java.util.List;

public interface SubCommand {
    String getName();

    String getDescription();

    default List<CommandArg> getArgs() {
        return new ArrayList<>();
    }

    default SubcommandData create() {
        final var command = new SubcommandData(this.getName(), this.getDescription());
        command.addOptions(this.getArgs().stream().map(CommandArg::createOption).toList());
        return command;
    }
}
