package net.inprogressing.Worldeater.main;

import net.inprogressing.Worldeater.listener.Worldeater;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;

    public void onEnable() {
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new Worldeater(), this);
    }

    public static Main getplugin(){
        return  plugin;
    }
}