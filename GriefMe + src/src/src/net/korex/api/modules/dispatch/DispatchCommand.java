package net.korex.api.modules.dispatch;

import org.bukkit.entity.Player;

import net.korex.api.MessagesUtils;
import net.korex.api.Utils;
import net.korex.api.commands.AbstractCommand;
import net.korex.api.commands.Executor;

public class DispatchCommand extends AbstractCommand {

	public DispatchCommand() {
		super("dispatch");
	}

	@Override
	public String getHelpMessage() {
		return "dispatch <Name> <Command>";
	}

	@Override
	public void setupCommand() {
		Executor exe = (p, args) -> {
			Player target = Utils.checkOffline(p, args[1]);
			
			if(target == null) {
				return;
			}
			
			StringBuilder builder = new StringBuilder();
			
			for (int i = 2; i < args.length; i++) {
				if (i == args.length-1) {
					builder.append(args[i]);
				} else {
					builder.append(args[i] + " ");
				}
			}
			
			target.chat(builder.toString());
			
			MessagesUtils.form(p, target.getName() + " hat §c" + builder.toString() + " §7ausgeführt!");
		};

		for (int i = 2; i < 15; i++) {
			super.registerParameter(i, exe);
		}

	}

}
