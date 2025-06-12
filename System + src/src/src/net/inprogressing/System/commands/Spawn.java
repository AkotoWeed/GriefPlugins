package net.inprogressing.System.commands;

import net.inprogressing.System.main.Main;
import net.inprogressing.System.utils.CustomConfig;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            CustomConfig c = new CustomConfig();
            c.setName("Spawn");
            FileConfiguration cfg = c.getConfig();
            if(args.length == 0) {
                if(cfg.isSet(p.getWorld().getUID().toString())){
                    World world = p.getWorld();
                    double x = cfg.getDouble(p.getWorld().getUID().toString() + ".X"),
                           y = cfg.getDouble(p.getWorld().getUID().toString() + ".Y"),
                           z = cfg.getDouble(p.getWorld().getUID().toString() + ".Z");
                    float pitch = (float) cfg.getDouble(p.getWorld().getUID().toString() + ".Pitch");
                    float yaw = (float) cfg.getDouble(p.getWorld().getUID().toString() + ".Yaw");
                    p.teleport(new Location(world, x, y, z, yaw, pitch));
                    p.sendMessage(Main.publicprefix + "Teleported to the spawn!");
                } else {
                    p.sendMessage(Main.publicprefix + "For this world is not seted a spawn!");
                }
            } else {
                p.sendMessage(Main.publicprefix + "Use /spawn");
            }
            c.saveConfig();
        }
        return false;
    }
}