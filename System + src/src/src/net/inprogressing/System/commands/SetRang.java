package net.inprogressing.System.commands;

import net.inprogressing.System.main.Main;
import net.inprogressing.System.utils.CustomConfig;
import net.inprogressing.System.utils.Rang;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetRang implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        CustomConfig c = new CustomConfig();
        c.setName("Rang");
        FileConfiguration cfg = c.getConfig();
        if (args.length == 2) {
            Player ont = Bukkit.getPlayer(args[0]);
            OfflinePlayer oft = Bukkit.getOfflinePlayer(args[0]);
            if(ont != null) {
                if (cfg.isSet(ont.getUniqueId().toString())) {
                    if (Rang.Range.contains(args[1])) {
                        cfg.set(ont.getUniqueId().toString(), args[1]);
                        ont.sendMessage(Main.publicprefix + "You get the rang §6" + args[1] + "§7.");
                    } else {
                        sender.sendMessage(Main.publicprefix + "The rang §6" + args[1] + "§7 not exists!");
                    }
                } else {
                    sender.sendMessage(Main.publicprefix + "This player is not registered!");
                }
            } else {
                if (cfg.isSet(oft.getUniqueId().toString())) {
                    if (Rang.Range.contains(args[1])) {
                        cfg.set(oft.getUniqueId().toString(), args[1]);
                    } else {
                        sender.sendMessage(Main.publicprefix + "The rang §6" + args[1] + "§7 not exists!");
                    }
                } else {
                    sender.sendMessage(Main.publicprefix + "This player is not registered!");
                }
            }
        } else {
            sender.sendMessage(Main.publicprefix + "Use /setrang <player> <rang>");
        }
        c.saveConfig();
        return false;
    }
}
