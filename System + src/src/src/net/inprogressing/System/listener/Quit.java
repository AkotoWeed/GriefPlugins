package net.inprogressing.System.listener;

import net.inprogressing.System.utils.CustomConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Quit implements Listener {
    @EventHandler
    public void onJoin(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage("§a" + p.getName() + " §7leaved!");
        CustomConfig c = new CustomConfig();
        c.setName("Nick");
        FileConfiguration cfg = c.getConfig();
        if (cfg.isSet(p.getUniqueId().toString())) {
            if (!cfg.get(p.getUniqueId().toString()).equals("none")) {
                e.setQuitMessage("§a" + cfg.get(p.getUniqueId().toString()) + " §7leaved!");
                p.setPlayerListName(p.getUniqueId().toString());
            }
        }
        p.getWorld().save();
        c.saveConfig();

        c.setName("Friends");
        cfg = c.getConfig();
        String day = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        cfg.set(p.getUniqueId().toString() + ".Seen", day + " " + time);
        c.saveConfig();
    }
}