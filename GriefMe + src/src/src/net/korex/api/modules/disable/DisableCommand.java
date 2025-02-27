package net.korex.api.modules.disable;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import net.korex.api.MessagesUtils;
import net.korex.api.commands.AbstractCommand;

public class DisableCommand extends AbstractCommand {

	public DisableCommand() {
		super("disable");
	}

	@Override
	public String getHelpMessage() {
		return "disable <PluginName>";
	}

	@Override
	public void setupCommand() {
		super.registerParameter(1, (p, args) -> {
			String plName = args[1];
			
			Plugin pl = Bukkit.getServer().getPluginManager().getPlugin(plName);
			
			if(pl ==null) {
				MessagesUtils.form(p, "Das Plugin existiert §cnicht§7. Liste aller Plugins: #plugins!");
				return;
			}
			
			Bukkit.getServer().getPluginManager().disablePlugin(pl);
			MessagesUtils.form(p, "Du hast das Plugin " + plName + " §cdeaktiviert§7!");
		});
	}

}
