package io.github.archipel_project.archipel_bot.service;

import io.github.archipel_project.archipel_bot.service.commands.SlashCommandListener;
import io.github.archipel_project.archipel_bot.service.utils.ConfigUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
public class Service {
    private JDA jda;

    public Service() {
        try {
            final JDABuilder builder = JDABuilder.createDefault(ConfigUtils.getToken());
            builder.setActivity(Activity.watching("Archipel's project"));

            jda = builder.build().awaitReady();
            jda.addEventListener(new SlashCommandListener(jda));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public JDA jda() {
        return this.jda;
    }
}