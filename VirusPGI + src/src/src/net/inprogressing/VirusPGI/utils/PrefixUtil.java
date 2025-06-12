package net.inprogressing.VirusPGI.utils;

import net.inprogressing.VirusPGI.main.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class PrefixUtil {

    public boolean isPrefixSet(String name){
        boolean set = false;
        FileConfiguration cfg = Main.getplugin().getConfig();
        if(cfg.isSet("Prefix."+name)){
            set = true;
        }
        return set;
    }

    public String getPrefix(String name){
        String x = null;
        try {
            FileConfiguration cfg = Main.getplugin().getConfig();
            x = cfg.get("Prefix." + name).toString();
            x = x.replace('&', '§');
        } catch (Exception e){
            System.out.println("[Fatal error] Plugin: System -> net.inprogressing.System.util.PrefixUtil-getprefix");
        }
        return x;
    }

    public void set(String name, String prefix){
        FileConfiguration cfg = Main.getplugin().getConfig();
        cfg.set("Prefix."+name, prefix);
        Main.getplugin().saveConfig();
    }
}
