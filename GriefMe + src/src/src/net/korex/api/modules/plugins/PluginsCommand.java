package net.korex.api.modules.plugins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import net.korex.api.MessagesUtils;
import net.korex.api.commands.AbstractCommand;

public class PluginsCommand extends AbstractCommand {

	public PluginsCommand() {
		super("plugins", "pl");
	}

	@Override
	public String getHelpMessage() {
		return "plugins";
	}

	@Override
	public void setupCommand() {
		super.registerParameter(0, (p, args) -> {
			
			MessagesUtils.form(p, "---> Alle Plugins <--");
			
			List<String> list = new ArrayList<>();
			
			for (Plugin pl : Bukkit.getServer().getPluginManager().getPlugins()) {
				if(pl.isEnabled()) {
					list.add("§a-"+pl.getName());
				} else {
					list.add("§c-"+pl.getName());
				}
				
			}
			
			Collections.sort(list);
			
			for(String pl : list) {
				p.sendMessage(pl);
			}
		});
	}

}
