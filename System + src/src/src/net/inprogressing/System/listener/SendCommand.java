package net.inprogressing.System.listener;

import net.inprogressing.System.main.Main;
import net.inprogressing.System.utils.CustomConfig;
import net.inprogressing.System.utils.Rang;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class SendCommand implements Listener {
    @EventHandler
    public void onSend(PlayerCommandPreprocessEvent e){
        Player p = e.getPlayer();
        String msg1 = e.getMessage();
        msg1 = msg1.replaceAll("/", "");
        String[] msg2 = msg1.split(" ");
        String msg3 = msg2[0];
        String PRang = null;
        CustomConfig c = new CustomConfig();
        c.setName("Rang");
        FileConfiguration cfg = c.getConfig();
        if(cfg.isSet(p.getUniqueId().toString())){
            PRang = cfg.getString(p.getUniqueId().toString());
        }
        if(!Rang.checkCommand(msg3, PRang) && !p.isOp()){
            p.sendMessage(Main.publicprefix + "§aYou do not have any rights!");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onCommand(ServerCommandEvent e){
        for(Player all : Bukkit.getOnlinePlayers()){
            if(all.isOp()){
                all.sendMessage(Main.publicprefix + "Run command sender: " + e.getSender().getName() + " command: §6" + e.getCommand() + "§7!");
            }
        }
    }
}
