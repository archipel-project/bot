package io.github.archipel_project.archipel_bot.archivist.commands;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class CommandArg {
    public final OptionType type;
    public final String name;
    public final String description;
    public final boolean isRequired;
    public final boolean isAutoComplete;

    public CommandArg(final OptionType type, final String name, final String description, final boolean isRequired, final boolean isAutoComplete) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.isRequired = isRequired;
        this.isAutoComplete = isAutoComplete;
    }

    public CommandArg(final OptionType type, final String name, final String description) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.isRequired = false;
        this.isAutoComplete = false;
    }

    public OptionData createOption() {
        return new OptionData(this.type, this.name, this.description, this.isRequired, this.isAutoComplete);
    }
}
