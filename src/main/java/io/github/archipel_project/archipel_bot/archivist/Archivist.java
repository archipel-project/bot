package io.github.archipel_project.archipel_bot.archivist;

import io.github.archipel_project.archipel_bot.archivist.listeners.CommandListener;
import io.github.archipel_project.archipel_bot.archivist.utils.ConfigUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Archivist {
    private static final Archivist _instance = new Archivist();

    private final JDA jda;
    private final CommandListener commandListener;

    public Archivist() {
        this.jda = this.build();

        this.commandListener = new CommandListener();
    }

    public void start() {
        this.commandListener.setup(this.jda);

        this.commandListener.getCommands().forEach(c -> {
            this.jda.addEventListener(c.getListeners().toArray());
        });

        try { this.jda.awaitReady(); }
        catch (Exception e) { e.printStackTrace(); }
    }

    private JDA build() {
        final var builder = JDABuilder.createDefault(ConfigUtils.getToken());
        builder.setActivity(Activity.watching("Archipel's project"));
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);

        return builder.build();
    }

    public static Archivist get() {
        return _instance;
    }

    public JDA jda() {
        return this.jda;
    }
}