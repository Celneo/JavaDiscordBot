package com.celneo.tutorialbot.commands;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Registers and manages slash commands.
 *
 * @author TechnoVision
 */
public class CommandManager extends ListenerAdapter {

    /**
     * Listens for slash commands and responds accordingly
     */
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("welcome")) {
            // Run the 'ping' command
            String userTag = event.getUser().getAsTag();
            event.reply("Welcome to the server, **" + userTag + "**!").queue();
        } else if (command.equals("roles")) {
            // run the 'roles' command
            event.deferReply().queue();
            String response = "";
            for (Role role : event.getGuild().getRoles()) {
                response += role.getAsMention() + "\n";
            }
            event.getHook().sendMessage(response).queue();
        }
    }



    /**
     * Registers slash commands as GUILD commands (max 100).
     * These commands will update instantly and are great for testing.
     */
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("welcome", "Get welcomed by the bot"));
        commandData.add(Commands.slash("roles", "Display all roles on the server"));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    /**
     * Registers slash commands as GLOBAL commands (unlimited).
     * These commands may take up to an hour to update.
     */
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("welcome", "Get welcomed by the bot"));
        event.getJDA().updateCommands().addCommands(commandData).queue();
    }

}

