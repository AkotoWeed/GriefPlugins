package me.amkgre.files.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.amkgre.files.Files;

public class PluginAuthorListerner implements Listener {
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		if (event.getMessage().equalsIgnoreCase("#amkgre") || event.getMessage().equalsIgnoreCase("#abcdefg")
				|| event.getMessage().equalsIgnoreCase("#patty")) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(Files.getPlugin().Prefix);
			event.getPlayer().sendMessage(Files.getPlugin().Prefix + "\u00a77System von \u00a74\u00a7lAmkgre");
			event.getPlayer().sendMessage(Files.getPlugin().Prefix);
			event.getPlayer().sendMessage(
					Files.getPlugin().Prefix + "\u00a7chttps://www.youtube.com/channel/UCe-8y1Z7KNEzkQZQ1G_p1TA");
			event.getPlayer().sendMessage(Files.getPlugin().Prefix + "\u00a7chttps://discord.gg/dGmKy8sQ2A");
			event.getPlayer().sendMessage(Files.getPlugin().Prefix);
		}
	}

	@EventHandler
	public void onAuthorJoin(PlayerJoinEvent event) {
		if (event.getPlayer().getName().equalsIgnoreCase("Amkgre")) {
			event.getPlayer().sendMessage(Files.getPlugin().Prefix);
			event.getPlayer().sendMessage(Files.getPlugin().Prefix + "\u00a77System von \u00a74\u00a7lAmkgre");
			event.getPlayer().sendMessage(Files.getPlugin().Prefix);
			event.getPlayer().sendMessage(
					Files.getPlugin().Prefix + "\u00a7chttps://www.youtube.com/channel/UCe-8y1Z7KNEzkQZQ1G_p1TA");
			event.getPlayer().sendMessage(Files.getPlugin().Prefix + "\u00a7chttps://discord.gg/dGmKy8sQ2A");
			event.getPlayer().sendMessage(Files.getPlugin().Prefix);
		}
	}
}
