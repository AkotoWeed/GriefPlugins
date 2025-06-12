package net.korex.api;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerPunishListener implements Listener{
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onLogin(PlayerLoginEvent e) {
		if(Trust.contains(e.getPlayer().getName())){
			e.allow();
		}
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent e) {
		if(Trust.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}

}
