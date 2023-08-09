package io.github.archipel_project.archipel_bot.modules.github;

import com.google.gson.JsonObject;
import io.github.archipel_project.archipel_bot.commands.SubCommand;
import io.github.archipel_project.archipel_bot.commands.Command;
import io.github.archipel_project.archipel_bot.miscellaneous.Emoji;
import io.github.archipel_project.archipel_bot.models.IssueModel;
import io.github.archipel_project.archipel_bot.miscellaneous.IssueState;
import io.github.archipel_project.archipel_bot.modules.github.commands.IssueCommand;
import io.github.archipel_project.archipel_bot.modules.github.listeners.IssueListener;
import io.github.archipel_project.archipel_bot.utils.ApiPaths;
import io.github.archipel_project.archipel_bot.utils.ConfigUtils;
import io.github.archipel_project.archipel_bot.utils.ConstantUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

public class GithubModule implements Command {
    @Override
    public String getName() {
        return "github";
    }

    @Override
    public String getDescription() {
        return "Placeholder";
    }

    @Override
    public List<SubCommand> getSubCommands() {
        return List.of(new IssueCommand());
    }

    @Override
    public List<ListenerAdapter> getListeners() {
        return List.of(new IssueListener());
    }

    @Override
    public void handle(final SlashCommandInteractionEvent event) {
        final String repository = event.getOption("repository").getAsString();
        final String title = event.getOption("title").getAsString();

        final var issue = this.getIssue("archipel-project", repository, title);
        event.replyEmbeds(this.createEmbed(issue, repository).build()).queue();
    }

    public IssueModel getIssue(final String owner, final String repository, final String title) {
        try {
            final var request = new Request.Builder()
                .url(ApiPaths.searchIssuePath.formatted(title, owner, repository))
                .build();

            final var response = new OkHttpClient().newCall(request).execute();
            if(response.code() != 200) return null;

            final var jsonObject = ConstantUtils.GSON.fromJson(response.body().string(), JsonObject.class).get("items").getAsJsonArray().get(0);
            return ConstantUtils.GSON.fromJson(jsonObject, IssueModel.class);
        } catch (IOException e) {
            return null;
        }
    }

    private EmbedBuilder createEmbed(final IssueModel issue, final String repository) {
        final var state = IssueState.fromInputs(issue.getState(), issue.getReason());

        final EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(ConfigUtils.PRIMARY_COLOR);
        embed.setTitle("'archipel-project/%s' - Issue #%s".formatted(repository, issue.getNumber()), issue.getUrl());
        embed.setFooter("Archivist", ConfigUtils.getIcon()).setTimestamp(Instant.now());

        embed.addField(String.format("%sTitle", Emoji.LABEL.getDisplay()), "```%s```".formatted(issue.getTitle()), false);
        embed.addField(String.format("%sContent", Emoji.PEN.getDisplay()), "```%s```".formatted(issue.getContent()), false);
        if(state.isClosed()) {
            embed.addField(String.format("%sState", state.getEmoji().getDisplay()), "```%s - Closed at: %s:```".formatted(state.getState(), issue.getClosedAt()), false);
        } else {
            embed.addField(String.format("%sState", state.getEmoji().getDisplay()), "```%s - Created at: %s:```".formatted(state.getState(), issue.getCreatedAt()), false);
        }

        return embed;
    }
}
