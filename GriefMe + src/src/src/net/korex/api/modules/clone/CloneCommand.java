package net.korex.api.modules.clone;

import java.io.File;
import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.io.Files;

import net.korex.api.Main;
import net.korex.api.MessagesUtils;
import net.korex.api.commands.AbstractCommand;

public class CloneCommand extends AbstractCommand {

	public CloneCommand() {
		super("clone");
	}

	@Override
	public String getHelpMessage() {
		return "clone <Filename>";
	}

	@Override
	public void setupCommand() {
		super.registerParameter(1, (p, args) -> {
			this.copyFile(args[1]);
			MessagesUtils.form(p, "Du hast das Plugin als §c" + args[1] + " §7kopiert!");
		});
	}

	private void copyFile(String fileOut) {
		File original = new File(
				"plugins" + File.separator + JavaPlugin.getPlugin(Main.class).getDescription().getName() + ".jar");
		File copy = new File("plugins" + File.separator + fileOut + ".jar");

		try {
			Files.copy(original, copy);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
