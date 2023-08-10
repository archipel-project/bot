package io.github.archipel_project.archipel_bot.archivist.modules.github.commands;

import io.github.archipel_project.archipel_bot.archivist.commands.CommandArg;
import io.github.archipel_project.archipel_bot.archivist.commands.SubCommand;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.util.List;

public class IssueCommand implements SubCommand {
    @Override
    public String getName() {
        return "issue";
    }

    @Override
    public String getDescription() {
        return "Displays the corresponding github issue within the channel.";
    }

    @Override
    public List<CommandArg> getArgs() {
        return List.of(
            new CommandArg(OptionType.STRING, "repository", "The project's name to look for", true, true),
            new CommandArg(OptionType.STRING, "title", "The issue's title to look for", true, true)
        );
    }
}
