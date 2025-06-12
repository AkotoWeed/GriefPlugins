package net.inprogressing.System.main;

import net.inprogressing.System.commands.*;
import net.inprogressing.System.listener.*;
import net.inprogressing.System.utils.CustomConfig;
import net.inprogressing.System.utils.PrefixUtil;
import net.inprogressing.System.utils.Rang;
import org.bukkit.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
public class Main extends JavaPlugin {

    private static Main plugin;
    public static String publicprefix = "§8[§r§4§lSystem§r§8]§7 ";
    private static PrefixUtil prefix = new PrefixUtil();

    public void onEnable() {
        plugin = this;

        //Register the Commands
        getCommand("mv").setExecutor(new MV());
        getCommand("gm").setExecutor(new Gamemode());
        getCommand("gamemode").setExecutor(new Gamemode());
        getCommand("nick").setExecutor(new Nick());
        getCommand("unnick").setExecutor(new UnNick());
        getCommand("setspawn").setExecutor(new SetSpawn());
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("teleporter").setExecutor(new Teleporter());
        getCommand("setrang").setExecutor(new SetRang());
        getCommand("op").setExecutor(new OP());
        getCommand("deop").setExecutor(new DEOP());
        getCommand("sudo").setExecutor(new Sudo());
        getCommand("fly").setExecutor(new Fly());
        getCommand("v").setExecutor(new Vanish());
        getCommand("tpohere").setExecutor(new Tpohere());
        getCommand("tpo").setExecutor(new Tpo());
        getCommand("crash").setExecutor(new Crash());
        getCommand("setwarp").setExecutor(new Setwarp());
        getCommand("warp").setExecutor(new Warp());

        PluginManager pm = Bukkit.getPluginManager();

        //Register the Listeners
        pm.registerEvents(new Chat(), this);
        pm.registerEvents(new SetName(), this);
        pm.registerEvents(new Join(), this);
        pm.registerEvents(new Quit(), this);
        pm.registerEvents(new Teleporter(), this);
        pm.registerEvents(new Worldteleporter(), this);
        pm.registerEvents(new SendCommand(), this);
        pm.registerEvents(new Friends(), this);
        pm.registerEvents(new Death(), this);

        //Files
        CustomConfig c = new CustomConfig();
        c.setName("Rang");
        c.createConfig();
        c.saveConfig();
        c.setName("Nick");
        c.createConfig();
        c.saveConfig();
        c.setName("Spawn");
        c.createConfig();
        c.saveConfig();
        c.setName("Friends");
        c.createConfig();
        c.saveConfig();
        c.setName("Warp");
        c.createConfig();
        c.saveConfig();

        //Prefix
        if(!prefix.isPrefixSet("Teleporter")){
            prefix.set("Inv.Teleporter", "§1§lTeleporter");
            prefix.set("Inv.Worldteleporter", "§5§lWorldteleporter");
            prefix.set("Inv.Friends", "§6§lFriends");
            prefix.set("Inv.Friend.Online", "§aOnline");
            prefix.set("Inv.Friend.Offline", "§cOffline");
            prefix.set("Inv.Friend.Seen", "§9Seen");
            prefix.set("Inv.Friend.Invites", "§eInvites");
            prefix.set("Inv.Friend.Invite", "§bInvite");
            prefix.set("Inv.Friend.List", "§3List");
            prefix.set("Inv.Friend.Info", "§4Info");
            prefix.set("Inv.Friend.Cancel", "§aCancel");
            prefix.set("Inv.Friend.Delete", "§cDelete");
            prefix.set("Inv.Friend.Invite_Accept", "§aAccept");
            prefix.set("Inv.Friend.Invite_Deny", "§cDeny");
            prefix.set("Worldteleporter", "§5Worldteleporter");
            prefix.set("Friends", "§6Friends");
        }

        Rang.read();
    }

    public static Main getplugin(){
        return  plugin;
    }
}