package net.inprogressing.System.commands;

import net.inprogressing.System.main.Main;
import net.inprogressing.System.utils.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Warp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length == 1){
                CustomConfig c = new CustomConfig();
                c.setName("Warp");
                FileConfiguration cfg = c.getConfig();
                if(cfg.getString("Warps").contains(args[0])) {
                    double x = cfg.getDouble(args[0] + ".X");
                    double y = cfg.getDouble(args[0] + ".Y");
                    double z = cfg.getDouble(args[0] + ".Z");
                    float pitch = (float) cfg.getDouble(args[0] + ".Pitch");
                    float yaw = (float) cfg.getDouble(args[0] + ".Yaw");
                    World world = Bukkit.getWorld(UUID.fromString(cfg.get(args[0] + ".World").toString()));
                    p.teleport(new Location(world, x, y, z, yaw, pitch));
                } else {
                    p.sendMessage(Main.publicprefix + "This warp not exists.");
                }
                c.saveConfig();
            } else {
                p.sendMessage(Main.publicprefix + "Use /warp <name>");
            }
        }
        return false;
    }
}
