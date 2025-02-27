package net.korex.api;

import org.bukkit.entity.Player;

public class MessagesUtils {

	public static void form(Player p, String message) {
		p.sendMessage(formPrefix("GriefMe") + " §7" + message);
	}

	public static String formPrefix(String pr) {
		return "§6" + pr + " §e|>";
	}

}
