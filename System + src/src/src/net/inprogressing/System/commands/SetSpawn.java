package net.inprogressing.System.commands;

import net.inprogressing.System.main.Main;
import net.inprogressing.System.utils.CustomConfig;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            CustomConfig c = new CustomConfig();
            c.setName("Spawn");
            FileConfiguration cfg = c.getConfig();
            if(args.length == 0) {
                Location loc = p.getLocation();
                cfg.set(p.getWorld().getUID().toString() + ".Name", p.getWorld().getName());
                cfg.set(p.getWorld().getUID().toString() + ".X", loc.getX());
                cfg.set(p.getWorld().getUID().toString() + ".Y", loc.getY());
                cfg.set(p.getWorld().getUID().toString() + ".Z", loc.getZ());
                cfg.set(p.getWorld().getUID().toString() + ".Pitch", loc.getPitch());
                cfg.set(p.getWorld().getUID().toString() + ".Yaw", loc.getYaw());
                p.sendMessage(Main.publicprefix + "Set the new spawn on §7" + Math.round(loc.getX()) + " " + Math.round(loc.getY()) + " " + Math.round(loc.getZ()));
            } else {
                p.sendMessage(Main.publicprefix + "Use /setspawn");
            }
            c.saveConfig();
        }
        return false;
    }
}
