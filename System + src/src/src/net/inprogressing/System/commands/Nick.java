package net.inprogressing.System.commands;

import net.inprogressing.System.main.Main;
import net.inprogressing.System.utils.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Nick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            CustomConfig c = new CustomConfig();
            c.setName("Nick");
            FileConfiguration cfg = c.getConfig();
            if (args.length == 1) {
                args[0] = args[0].replaceAll("&", "§");
                p.setPlayerListName(args[0]);
                cfg.set(p.getUniqueId().toString(), args[0]);
                p.sendMessage(Main.publicprefix + "Your new name is §6" + args[0] + "§7.");
            } else if (args.length == 2) {
                args[1] = args[1].replaceAll("&", "§");
                Player target = Bukkit.getPlayer(args[0]);
                cfg.set(target.getUniqueId().toString(), args[1]);
                target.setPlayerListName(args[1]);
                p.sendMessage(Main.publicprefix + "New name from the player §6" + args[0] + "§7 is §6" + args[1] + "§7.");
            } else {
                p.sendMessage(Main.publicprefix + "Use /nick <name> or /nick <player> <name>");
            }
            c.saveConfig();
        }
        return false;
    }
}
