package net.inprogressing.System.listener;

import net.inprogressing.System.main.Main;
import net.inprogressing.System.utils.CustomConfig;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = (Player) e.getEntity();

        CustomConfig c = new CustomConfig();
        c.setName("Spawn");
        FileConfiguration cfg = c.getConfig();

        World world = p.getWorld();
        double x = cfg.getDouble(p.getWorld().getUID().toString() + ".X"),
                y = cfg.getDouble(p.getWorld().getUID().toString() + ".Y"),
                z = cfg.getDouble(p.getWorld().getUID().toString() + ".Z");
        float pitch = (float) cfg.getDouble(p.getWorld().getUID().toString() + ".Pitch");
        float yaw = (float) cfg.getDouble(p.getWorld().getUID().toString() + ".Yaw");
        p.teleport(new Location(world, x, y, z, yaw, pitch));
        p.sendMessage(Main.publicprefix + "Teleported to the spawn!");

        c.saveConfig();
    }
}
