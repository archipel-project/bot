package io.github.archipel_project.archipel_bot.service.commands;

import net.dv8tion.jda.api.interactions.commands.OptionType;

public record SlashCommandArgs(OptionType type, String name, String description, boolean required) {
}
