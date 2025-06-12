package net.inprogressing.System.commands;

import net.inprogressing.System.main.Main;
import net.inprogressing.System.utils.CustomConfig;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Setwarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length == 1){
                CustomConfig c = new CustomConfig();
                c.setName("Warp");
                FileConfiguration cfg = c.getConfig();
                Location loc = p.getLocation();
                cfg.set(args[0] + ".X", loc.getX());
                cfg.set(args[0] + ".Y", loc.getY());
                cfg.set(args[0] + ".Z", loc.getZ());
                cfg.set(args[0] + ".Pitch", loc.getPitch());
                cfg.set(args[0] + ".Yaw", loc.getYaw());
                cfg.set(args[0] + ".World", loc.getWorld().getUID().toString());

                if(cfg.isSet("Warps")) {
                    String set = cfg.getString("Warps");
                    cfg.set("Warps", set + ":" + args[0]);
                } else {
                    cfg.set("Warps", args[0]);
                }
                c.saveConfig();
            } else {
                p.sendMessage(Main.publicprefix + "Use /setwarp <name>");
            }
        }
        return false;
    }
}