package me.amkgre.files.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PluginCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String arg2, String[] arg3) {
		commandSender.sendMessage("\u00a7a\u00a7lDieser Server wurde durch ein Anticrash Plugin geschützt :D");

		return false;
	}
}
