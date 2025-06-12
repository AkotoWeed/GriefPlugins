package net.inprogressing.System.listener;

import net.inprogressing.System.utils.CustomConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class SetName implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        CustomConfig c = new CustomConfig();
        c.setName("Nick");
        FileConfiguration cfg = c.getConfig();

        cfg = c.getConfig();
        if (cfg.isSet(p.getUniqueId().toString())) {
            if (!cfg.get(p.getUniqueId().toString()).equals("none")) {
                p.setPlayerListName(cfg.getString(p.getUniqueId().toString()));
                p.setDisplayName(cfg.getString(p.getUniqueId().toString()));
            } else {
                c.saveConfig();
                c.setName("Rang");
                cfg = c.getConfig();
                if(cfg.isSet(p.getUniqueId().toString())){
                    p.setPlayerListName(cfg.getString("Prefix."+cfg.getString(p.getUniqueId().toString())) + p.getName());
                    p.setDisplayName(cfg.getString("Prefix."+cfg.getString(p.getUniqueId().toString())) + p.getName());
                } else {
                    cfg.set(p.getUniqueId().toString(), "Player");
                }
            }
        }
        c.saveConfig();
    }
}
