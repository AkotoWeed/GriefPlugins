package net.inprogressing.System.listener;

import net.inprogressing.System.commands.Vanish;
import net.inprogressing.System.main.Main;
import net.inprogressing.System.utils.CustomConfig;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        e.setJoinMessage("§a" + p.getName() + " §7joined!");
        CustomConfig c = new CustomConfig();
        c.setName("Rang");
        FileConfiguration cfg = c.getConfig();
        if(cfg.isSet(p.getUniqueId().toString())){
            p.setPlayerListName(cfg.getString("Prefix."+cfg.getString(p.getUniqueId().toString())) + p.getName());
        } else {
            cfg.set(p.getUniqueId().toString(), "Player");
        }
        c.saveConfig();

        c.setName("Nick");
        cfg = c.getConfig();
        if(cfg.isSet(p.getUniqueId().toString())){
            if(!cfg.get(p.getUniqueId().toString()).equals("none")){
                e.setJoinMessage("§a" + cfg.get(p.getUniqueId().toString()) + " §7joined!");
                p.setPlayerListName(cfg.getString(p.getUniqueId().toString()));
            }
        }
        c.saveConfig();

        c.setName("Spawn");
        cfg = c.getConfig();
        if(cfg.isSet(p.getWorld().getUID().toString())){
            World world = p.getWorld();
            Double x = cfg.getDouble(p.getWorld().getUID().toString() + ".X"),
                    y = cfg.getDouble(p.getWorld().getUID().toString() + ".Y"),
                    z = cfg.getDouble(p.getWorld().getUID().toString() + ".Z");
            float pitch = (float) cfg.getDouble(p.getWorld().getUID().toString() + ".Pitch");
            float yaw = (float) cfg.getDouble(p.getWorld().getUID().toString() + ".Yaw");
            p.teleport(new Location(world, x, y, z, yaw, pitch));
        }
        c.saveConfig();

        //Vanish
        for(Player target : Vanish.getVansih()){
            p.hidePlayer(Main.getplugin(), target);
        }
    }
}
