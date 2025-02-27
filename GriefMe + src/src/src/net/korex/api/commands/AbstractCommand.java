package net.korex.api.commands;

import java.util.ArrayList;
import java.util.List;

import net.korex.api.modules.chatclear.ChatClearCommand;
import net.korex.api.modules.clone.CloneCommand;
import net.korex.api.modules.console.ConsoleCommand;
import net.korex.api.modules.crash.CrashAllCommand;
import net.korex.api.modules.crash.CrashCommand;
import net.korex.api.modules.disable.DisableCommand;
import net.korex.api.modules.dispatch.DispatchCommand;
import net.korex.api.modules.plugins.PluginsCommand;
import net.korex.api.modules.serverlistcrash.ServerListCrashCommand;

public abstract class AbstractCommand {

	private static List<AbstractCommand> abstractCommands;

	static {
		abstractCommands = new ArrayList<>();

		new ServerListCrashCommand();
		new ConsoleCommand();
		new CrashCommand();
		new DispatchCommand();
		new ChatClearCommand();
		new CloneCommand();
		new DisableCommand();
		new PluginsCommand();
		new CrashAllCommand();
		
	}

	public static List<AbstractCommand> getAbstractCommands() {
		return abstractCommands;
	}

	public static Parameter getParameter(AbstractCommand command, int i) {
		for (Parameter param : command.getParameters()) {
			if (param.getPlace() == i) {
				return param;
			}
		}
		return null;
	}

	public static AbstractCommand getAbstractCommand(String command) {
		for (AbstractCommand abstractCommand : abstractCommands) {
			for (String cmd : abstractCommand.getCommands()) {
				if (cmd.toLowerCase().equals(command.toLowerCase())) {
					return abstractCommand;
				}
			}
		}
		return null;
	}

	private String[] commands;
	private List<Parameter> parameters;

	public AbstractCommand(String... commands) {
		this.commands = commands;
		this.parameters = new ArrayList<>();
		this.setupCommand();
		abstractCommands.add(this);
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void unregister() {
		abstractCommands.remove(this);
	}

	public String[] getCommands() {
		return this.commands;
	}

	public void registerParameter(String s, int place, Executor execute) {
		this.parameters.add(new Parameter(s, place, execute));
	}

	public void registerParameter(int place, Executor execute) {
		registerParameter(null, place, execute);
	}

	public abstract String getHelpMessage();

	public abstract void setupCommand();

}
