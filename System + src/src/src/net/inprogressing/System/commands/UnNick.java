package net.inprogressing.System.commands;

import net.inprogressing.System.main.Main;
import net.inprogressing.System.utils.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class UnNick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            CustomConfig c = new CustomConfig();
            c.setName("Nick");
            FileConfiguration cfg = c.getConfig();
            if (args.length == 0) {
                if (cfg.isSet(p.getUniqueId().toString())) {
                    if (!cfg.get(p.getUniqueId().toString()).equals("none")) {
                        cfg.set(p.getUniqueId().toString(), "none");
                        p.setPlayerListName(p.getName());
                        p.sendMessage(Main.publicprefix + "Unnicked!");
                    } else {
                        p.sendMessage(Main.publicprefix + "You were not nicked!");
                    }
                } else {
                    p.sendMessage(Main.publicprefix + "You were not nicked!");
                }
            } else if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (cfg.isSet(target.getUniqueId().toString())) {
                    if (!cfg.get(target.getUniqueId().toString()).equals("none")) {
                        cfg.set(target.getUniqueId().toString(), "none");
                        target.setPlayerListName(target.getName());
                        p.sendMessage(Main.publicprefix + "Unnicked §6" + args[0] + "§7.");
                    } else {
                        p.sendMessage(Main.publicprefix + "The player §6" + target.getName() + "§7 is not nicked!");
                    }
                } else {
                    p.sendMessage(Main.publicprefix + "The player §6" + target.getName() + "§7 is not nicked!");
                }
            } else {
                p.sendMessage(Main.publicprefix + "Use /unnick or /unnick <player>");
            }
            c.saveConfig();
        }
        return false;
    }
}
