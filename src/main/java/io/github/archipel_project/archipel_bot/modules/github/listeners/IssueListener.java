package io.github.archipel_project.archipel_bot.modules.github.listeners;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.archipel_project.archipel_bot.models.IssueModel;
import io.github.archipel_project.archipel_bot.models.RepositoryModel;
import io.github.archipel_project.archipel_bot.utils.ApiPaths;
import io.github.archipel_project.archipel_bot.utils.ConstantUtils;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class IssueListener extends ListenerAdapter {
    @Override
    public void onCommandAutoCompleteInteraction(final CommandAutoCompleteInteractionEvent event) {
        final String command = event.getName();
        final String subCommand = event.getSubcommandName();

        System.out.println(command);
        System.out.println(subCommand);

        if(subCommand == null) return;

        if (command.equals("github") && subCommand.equals("issue")) {
            final String field = event.getFocusedOption().getName();

            if (field.equals("repository")) {
                final var repositories = this.getRepositories("archipel-project");
                final String[] names = repositories.stream().map(RepositoryModel::getName).toArray(String[]::new);

                final var choices = Stream.of(names)
                    .filter(r -> r.toLowerCase().contains(event.getFocusedOption().getValue().toLowerCase()))
                    .map(r -> new Command.Choice(r, r))
                    .toList();

                event.replyChoices(choices).queue();
            } else if (field.equals("title")){
                final var repoOption = event.getOption("repository");
                if(repoOption == null) return;

                final var issues = this.getMatchingIssues("archipel-project", repoOption.getAsString(), event.getFocusedOption().getValue());
                final String[] titles = issues.stream().map(IssueModel::getTitle).toArray(String[]::new);

                final var choices = Stream.of(titles)
                    .filter(r -> r.toLowerCase().contains(event.getFocusedOption().getValue().toLowerCase()))
                    .map(r -> new Command.Choice(r, r))
                    .toList();

                event.replyChoices(choices).queue();
            }
        }
    }

    public List<RepositoryModel> getRepositories(final String owner) {
        try {
            final var request = new Request.Builder()
                .url(ApiPaths.reposPath.formatted(owner))
                .build();

            final var response = new OkHttpClient().newCall(request).execute();
            if(response.code() != 200) return null;

            final var jsonArray = ConstantUtils.GSON.fromJson(response.body().string(), JsonArray.class);
            return StreamSupport.stream(jsonArray.spliterator(), false)
                .map(e -> ConstantUtils.GSON.fromJson(e, RepositoryModel.class))
                .toList();
        } catch (IOException e) {
            return null;
        }
    }

    public List<IssueModel> getMatchingIssues(final String owner, final String repository, final String query) {
        try {
            final var request = new Request.Builder()
                .url(ApiPaths.searchIssuePath.formatted(query, owner, repository))
                .build();

            final var response = new OkHttpClient().newCall(request).execute();
            if(response.code() != 200) return null;

            final var jsonObject = ConstantUtils.GSON.fromJson(response.body().string(), JsonObject.class);
            final var jsonArray = jsonObject.get("items").getAsJsonArray();
            return StreamSupport.stream(jsonArray.spliterator(), false)
                .map(e -> ConstantUtils.GSON.fromJson(e, IssueModel.class))
                .toList();
        } catch (IOException e) {
            return null;
        }
    }
}
