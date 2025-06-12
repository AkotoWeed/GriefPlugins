package net.inprogressing.System.commands;

import net.inprogressing.System.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null){
                    p.teleport(target);
                    p.sendMessage(Main.publicprefix + "You were teleported!");
                } else {
                    p.sendMessage(Main.publicprefix + "The player §6" + args[0] + "§7 is not online.");
                }
            } else {
                p.sendMessage(Main.publicprefix + "Use /tpo <player>");
            }
        }
        return false;
    }
}
