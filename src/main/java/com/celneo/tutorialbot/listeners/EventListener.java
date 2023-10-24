package com.celneo.tutorialbot.listeners;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import net.dv8tion.jda.api.entities.channel.unions.DefaultGuildChannelUnion;

import java.util.Objects;

public class EventListener extends ListenerAdapter {



    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        User user = event.getUser();
        String jumpLink = event.getJumpUrl();
        String emoji = event.getReaction().getEmoji().getAsReactionCode();
        String channel = event.getChannel().getAsMention();

        String message = user.getAsTag() + " reacted to a [message]("+jumpLink+") with " + emoji + " in the " + channel + " channel!";
        event.getGuild().getDefaultChannel().asStandardGuildMessageChannel().sendMessage(message).queue();    
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        // After August 2022 will require gateway intent
        String message = event.getMessage().getContentRaw();
        if (message.contains("ping")) {
            event.getChannel().sendMessage("pong").queue();
        }
    }
    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        // WILL NOT WORK WITHOUT GATEWAY INTENT!
        String avatar = event.getUser().getEffectiveAvatarUrl();
        System.out.println(avatar);
    }
    @Override
    public void onUserUpdateOnlineStatus(@NotNull UserUpdateOnlineStatusEvent event) {

    }
}
