package net.inprogressing.System.commands;

import net.inprogressing.System.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Vanish implements CommandExecutor {
    private static List<Player> vansih = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(args.length == 0){
            if(vansih.contains(p)) {
                vansih.remove(p);
                p.sendMessage(Main.publicprefix + "Disable vanish");
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.showPlayer(Main.getplugin(), p);
                }
            } else {
                vansih.add(p);
                p.sendMessage(Main.publicprefix + "Enabled vanish");
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.hidePlayer(Main.getplugin(), p);
                }
            }
        } else if(args.length == 1){
            Player target = Bukkit.getPlayer(args[0]);
            if(target != null){
                if(vansih.contains(target)) {
                    vansih.remove(target);
                    target.sendMessage(Main.publicprefix + "Disable vanish");
                    p.sendMessage(Main.publicprefix + "Disabled vanish for §6" + target.getName() + "§7.");
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.showPlayer(Main.getplugin(), target);
                    }
                } else {
                    vansih.add(target);
                    target.sendMessage(Main.publicprefix + "Enabled vanish");
                    p.sendMessage(Main.publicprefix + "Enabled vanish for §6" + target.getName() + "§7.");
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.hidePlayer(Main.getplugin(), target);
                    }
                }
            } else {
                p.sendMessage(Main.publicprefix + "The player §6" + args[0] + "§7 is not online.");
            }
        }
        return false;
    }

    public static List<Player> getVansih() {
        return vansih;
    }
}