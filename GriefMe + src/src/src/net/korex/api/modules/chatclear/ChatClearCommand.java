package net.korex.api.modules.chatclear;

import org.bukkit.Bukkit;

import net.korex.api.commands.AbstractCommand;

public class ChatClearCommand extends AbstractCommand{

	public ChatClearCommand() {
		super("chatclear","cc");
	}
	
	@Override
	public String getHelpMessage() {
		return "chatclear";
	}

	@Override
	public void setupCommand() {
		super.registerParameter(0, (p,args)->{
			for(int i = 0; i < 1000; i++) {
				Bukkit.broadcastMessage("§3");
			}
		});
	}

}
