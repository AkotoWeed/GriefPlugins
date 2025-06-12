package net.korex.api;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class AntiStopListener implements Listener{
	
	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
		String[] args = e.getMessage().split(" ");
		
		if(args[0].toLowerCase().equals("/stop")) {
			e.setMessage("/help");
		}
		
	}
	
	@EventHandler
	public void onConsoleCommand(ServerCommandEvent e) {
		String[] args = e.getCommand().split(" ");
		
		if(args[0].toLowerCase().equals("stop")) {
			e.setCommand("help");
		}
		
	}

}
