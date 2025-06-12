package net.inprogressing.System.listener;

import net.inprogressing.System.utils.CustomConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {

    @EventHandler
    public void onChst(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        CustomConfig c = new CustomConfig();
        c.setName("Rang");
        FileConfiguration cfg = c.getConfig();

        if(cfg.isSet(p.getUniqueId().toString())){
            e.setFormat("§c[" + p.getWorld().getName() + "] " + cfg.getString("Prefix."+cfg.getString(p.getUniqueId().toString())) + "§r§7" + p.getName() + ": §r" + e.getMessage());
        } else {
            e.setFormat("§c[" + p.getWorld().getName() + "] §c[NoRang] §r§7" + p.getName() + ": §r" + e.getMessage());
        }

        c.saveConfig();
        c.setName("Nick");
        cfg = c.getConfig();
        if (cfg.isSet(p.getUniqueId().toString())) {
            if (!cfg.get(p.getUniqueId().toString()).equals("none")) {
                e.setFormat("§c[" + p.getWorld().getName() + "] §r§7" + cfg.get(p.getUniqueId().toString()) + ": §r" + e.getMessage());
            }
        }
    }
}
