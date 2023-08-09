package io.github.archipel_project.archipel_bot;

import io.github.archipel_project.archipel_bot.listeners.CommandListener;
import io.github.archipel_project.archipel_bot.modules.github.listeners.IssueListener;
import io.github.archipel_project.archipel_bot.utils.ConfigUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
public class Archivist {
    private static final Archivist INSTANCE = new Archivist();
    public static void main(String[] args) {
        INSTANCE.setup();
        INSTANCE.ready();
    }

    private final JDA jda;
    private final CommandListener listener;

    public Archivist() {
        final var builder = JDABuilder.createDefault(ConfigUtils.getToken());
        builder.setActivity(Activity.watching("Archipel's project"));

        this.jda = builder.build();
        this.listener = new CommandListener();
    }

    public void setup() {
        this.jda.addEventListener(this.listener);
        this.jda.addEventListener(new IssueListener());

        this.listener.getCommands().forEach(c -> {
            final var listeners = c.getListeners();
            if (listeners == null) return;
            this.jda.addEventListener(listeners);
        });

        this.listener.setup();
    }

    public void ready() {
        try { this.jda.awaitReady(); }
        catch (Exception e) { e.printStackTrace(); }
    }

    public static Archivist get() {
        return INSTANCE;
    }

    public JDA jda() {
        return this.jda;
    }
}