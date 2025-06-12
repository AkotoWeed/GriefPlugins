package net.inprogressing.Worldeater.listener;

import net.inprogressing.Worldeater.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Timer;
import java.util.TimerTask;

public class Worldeater implements Listener {

    private static BukkitTask move;
    private static int range = 1;

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (e.getBlock().getType() == Material.BEDROCK) {
            Location loc = new Location(p.getWorld(), e.getBlock().getX(), e.getBlock().getY(), e.getBlock().getZ(), 0 ,0);
            start(p, loc);
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.sendMessage("§4§lStarted Worldeater!");
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        if (e.getMessage().equals("#STOP")) {
            move.cancel();
            e.setCancelled(true);
        }
    }

    private void start(Player p, Location loc) {
        move = new BukkitRunnable() {
            @Override
            public void run() {
                range++;
                int x1 = range;
                x1 /= 2;
                for(int y=0;y<250;y++){
                    for(int x=0;x<x1;x++){
                        for(int z=0;z<x1;z++){
                            Location floc = new Location(p.getWorld(), (loc.getX()-(x1/2))+x, y, (loc.getZ()-(x1/2))+z, 0, 0);
                            floc.getBlock().setType(Material.AIR);
                        }
                    }
                }
            }
        }.runTaskTimer(Main.getplugin(),0,0);
    }
}
