package net.inprogressing.System.commands;

import net.inprogressing.System.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sudo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length >= 1) {
            Player p = Bukkit.getPlayer(args[0]);
            if (p != null) {
                String cmd = "";
                for (int i = 1; i < args.length; i++) {
                    if (i == args.length - 1) {
                        cmd += args[i];
                    } else {
                        cmd += args[i] + " ";
                    }
                }
                Bukkit.dispatchCommand(p, cmd);
            } else {
                sender.sendMessage(Main.publicprefix + "The player §6" + args[0] + "§7 is not online!");
            }
        } else {
            sender.sendMessage(Main.publicprefix + "Use /sudo <player> <command>");
        }
        return false;
    }
}
