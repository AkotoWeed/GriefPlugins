package net.inprogressing.System.commands;

import net.inprogressing.System.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DEOP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1){
            Player p = Bukkit.getPlayer(args[0]);
            OfflinePlayer of = Bukkit.getOfflinePlayer(args[0]);
            if(p != null){
                p.setOp(false);
            } else {
                Bukkit.getServer().getOperators().remove(of);
            }
        } else {
            sender.sendMessage(Main.publicprefix + "Use /deop <player>");
        }
        return false;
    }
}
