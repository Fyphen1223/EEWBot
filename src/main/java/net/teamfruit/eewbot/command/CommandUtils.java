package net.teamfruit.eewbot.command;

import java.awt.Color;
import java.util.Optional;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.object.util.Snowflake;
import discord4j.core.spec.EmbedCreateSpec;
import net.teamfruit.eewbot.EEWBot;
import net.teamfruit.eewbot.registry.Permission;

public class CommandUtils {

	public static boolean userHasPermission(final EEWBot bot, final long userid, final String command) {
		return bot.getPermissions().values().stream()
				.filter(permission -> permission.getUserid().contains(userid))
				.findAny().orElse(EEWBot.instance.getPermissions().getOrDefault("everyone", Permission.DEFAULT_EVERYONE))
				.getCommand().contains(command);
	}

	public static String getLangage(final EEWBot bot, final Optional<Snowflake> guildId) {
		return guildId.map(id -> bot.getGuilds().get(id.asLong()))
				.map(guild -> guild.getLang())
				.orElse(bot.getConfig().getDefaultLanuage());
	}

	public static String getLangage(final EEWBot bot, final MessageCreateEvent event) {
		return getLangage(bot, event.getGuildId());
	}

	public static String getLangage(final EEWBot bot, final ReactionAddEvent event) {
		return getLangage(bot, event.getGuildId());
	}

	public static EmbedCreateSpec createEmbed(final EmbedCreateSpec embed) {
		return embed.setColor(new Color(7506394))
				.setAuthor(EEWBot.instance.getUsername(), "https://github.com/Team-Fruit/EEWBot", EEWBot.instance.getAvatarUrl())
				.setFooter("Team-Fruit/EEWBot", "http://i.imgur.com/gFHBoZA.png");
	}

	public static EmbedCreateSpec createErrorEmbed(final EmbedCreateSpec embed) {
		return embed.setColor(new Color(255, 64, 64))
				.setAuthor(EEWBot.instance.getUsername(), "https://github.com/Team-Fruit/EEWBot", EEWBot.instance.getAvatarUrl())
				.setFooter("Team-Fruit/EEWBot", "http://i.imgur.com/gFHBoZA.png");
	}
}
