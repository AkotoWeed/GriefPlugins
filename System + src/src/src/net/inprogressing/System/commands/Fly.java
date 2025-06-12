package net.inprogressing.System.commands;

import net.inprogressing.System.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                if (p.isFlying()) {
                    p.setAllowFlight(false);
                    p.setFlying(false);
                    p.sendMessage(Main.publicprefix + "Disable fly");
                } else {
                    p.setAllowFlight(true);
                    p.setFlying(true);
                    p.sendMessage(Main.publicprefix + "Enable fly");
                }
            } else if(args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null){
                    if (target.isFlying()) {
                        target.setAllowFlight(false);
                        target.setFlying(false);
                        target.sendMessage(Main.publicprefix + "Disable fly");
                        p.sendMessage(Main.publicprefix + "Disabled fly for §6"+target.getName()+"§7.");
                    } else {
                        target.setAllowFlight(true);
                        target.setFlying(true);
                        target.sendMessage(Main.publicprefix + "Enable fly");
                        p.sendMessage(Main.publicprefix + "Enable fly for §6"+target.getName()+"§7.");
                    }
                } else {
                    p.sendMessage(Main.publicprefix + "This player is not online!");
                }
            } else if(args.length == 2 && args[0].equals("speed")){
                p.setFlySpeed((Float.valueOf(args[1]) / 10));
                p.sendMessage(Main.publicprefix + "Set fly to §6" + Float.valueOf(args[1]) + "§7.");
            } else {
                p.sendMessage(Main.publicprefix + "Use /fly <player> or /fly speed <number>");
            }
        }
        return false;
    }
}
