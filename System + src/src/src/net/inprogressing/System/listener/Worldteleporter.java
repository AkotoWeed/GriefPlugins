package net.inprogressing.System.listener;

import net.inprogressing.System.main.Main;
import net.inprogressing.System.utils.CustomConfig;
import net.inprogressing.System.utils.PrefixUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Worldteleporter implements Listener {
    private static PrefixUtil prefix = new PrefixUtil();

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        ItemStack stack = new ItemStack(Material.COMPASS);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(prefix.getPrefix("Worldteleporter"));
        stack.setItemMeta(meta);
        p.getInventory().setItem(3, stack);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        e.setKeepInventory(true);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getCurrentItem() != null) {
            if (e.getCurrentItem().hasItemMeta()) {
                if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Worldteleporter"))) {
                        e.setCancelled(true);
                    } else if (Bukkit.getWorlds().contains(Bukkit.getWorld(e.getCurrentItem().getItemMeta().getDisplayName()))) {
                        try {
                            CustomConfig c = new CustomConfig();
                            c.setName("Spawn");
                            FileConfiguration cfg = c.getConfig();
                            World world = Bukkit.getWorld(e.getCurrentItem().getItemMeta().getDisplayName());
                            if (cfg.isSet(world.getUID().toString())) {
                                double x = cfg.getDouble(world.getUID().toString() + ".X"),
                                        y = cfg.getDouble(world.getUID().toString() + ".Y"),
                                        z = cfg.getDouble(world.getUID().toString() + ".Z");
                                float pitch = (float) cfg.getDouble(world.getUID().toString() + ".Pitch");
                                float yaw = (float) cfg.getDouble(world.getUID().toString() + ".Yaw");
                                p.teleport(new Location(world, x, y, z, yaw, pitch));
                                p.sendMessage(Main.publicprefix + "Teleported!");
                            } else {
                                p.teleport(world.getSpawnLocation());
                                p.sendMessage(Main.publicprefix + "Teleported!");
                            }
                            c.saveConfig();
                            e.setCancelled(true);
                        } catch (Exception ee){
                            p.sendMessage(Main.publicprefix + "[Fatal ERROR] Can not teleport!");
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if(e.getItemDrop() != null){
            if(e.getItemDrop().getItemStack() != null){
                if(e.getItemDrop().getItemStack().hasItemMeta()){
                    if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(prefix.getPrefix("Worldteleporter"))){
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerIntract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getItem() != null){
                if(e.getItem().hasItemMeta()){
                    if(e.getItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Worldteleporter"))){
                        Inventory inv = Bukkit.getServer().createInventory(null, ((Bukkit.getWorlds().size() / 9)+1) * 9, prefix.getPrefix("Inv.Worldteleporter"));
                        int place = 0;
                        for(World world : Bukkit.getWorlds()){
                            ItemStack stack = new ItemStack(Material.MAP);
                            ItemMeta meta = stack.getItemMeta();
                            meta.setDisplayName(world.getName());
                            stack.setItemMeta(meta);
                            inv.setItem(place, stack);
                            place++;
                        }
                        p.openInventory(inv);
                    }
                }
            }
        }
    }
}
