package net.korex.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Utils {
	
	public static Player checkOffline(Player p, String name) {
		Player target = Bukkit.getPlayer(name);
		
		if(target == null) {
			MessagesUtils.form(p, "Der angegebene Spieler ist §coffline§7!");
			return null;
		}
		return target;
	}
	
}
