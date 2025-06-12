package net.inprogressing.VirusPGI.main;

import net.inprogressing.VirusPGI.all.All;
import org.bukkit.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;

    public void onEnable() {
        plugin = this;

        PluginManager pm = Bukkit.getPluginManager();

        //Register the Listeners
        pm.registerEvents(new All(), this);
    }

    public static Main getplugin(){
        return  plugin;
    }
}