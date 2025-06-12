package net.inprogressing.System.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfig {
    private File file;
    private FileConfiguration config;
    private String Name;

    public void createConfig(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("System").getDataFolder(), Name + ".yml");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig(){
        return config;
    }

    public void saveConfig(){
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setName(String Namex){
        this.Name = Namex;
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("System").getDataFolder(), Name + ".yml");
        config = YamlConfiguration.loadConfiguration(file);
    }
}
