package me.amkgre.files;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.amkgre.files.command.PluginCommandExecutor;
import me.amkgre.files.helper.DiscordConnectionHelper;
import me.amkgre.files.listener.PluginAuthorListerner;
import me.amkgre.files.listener.PluginCommandListerner;

public class Files extends JavaPlugin implements Listener {

	public String Prefix = "§4" + Files.class.getSimpleName() + " §8| §7".replace("&", "\u00a7").replace("§", "\u00a7");

	private static Files plugin;

	public static Files getPlugin() {
		return plugin;
	}

	public void onEnable() {
		plugin = this;

		DiscordConnectionHelper.start();

		this.getServer().getPluginManager().registerEvents(new PluginAuthorListerner(), this);
		this.getServer().getPluginManager().registerEvents(new PluginCommandListerner(), this);
		
		this.getServer().getPluginCommand("anticrash").setExecutor(new PluginCommandExecutor());
	}
}
