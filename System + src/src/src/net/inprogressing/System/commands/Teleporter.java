package net.inprogressing.System.commands;

import net.inprogressing.System.main.Main;
import net.inprogressing.System.utils.PrefixUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Teleporter implements CommandExecutor, Listener {
    private static PrefixUtil prefix = new PrefixUtil();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length == 0){
                Inventory inv = Bukkit.getServer().createInventory(null, ((Bukkit.getWorlds().size() / 9)+1) * 9, prefix.getPrefix("Inv.Teleporter"));
                int place = 0;
                for(Player all : Bukkit.getOnlinePlayers()){
                    if(!all.equals(p)) {
                        ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                        SkullMeta meta = (SkullMeta) stack.getItemMeta();
                        meta.setDisplayName("§f" + all.getName());
                        meta.setOwningPlayer(all);
                        stack.setItemMeta(meta);
                        inv.setItem(place, stack);
                        place++;
                    }
                }
                p.openInventory(inv);
            } else {
                p.sendMessage(Main.publicprefix + "Use /teleporter");
            }
        }
        return false;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getCurrentItem() != null) {
            if (e.getCurrentItem().hasItemMeta()) {
                if(e.getCurrentItem().getItemMeta().hasDisplayName()) {
                    String TP = e.getCurrentItem().getItemMeta().getDisplayName();
                    if(TP.contains("§f")) {
                        TP = TP.replaceAll("§f", "");
                        if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(TP))) {
                            p.teleport(Bukkit.getPlayer(TP));
                            p.sendMessage(Main.publicprefix + "Teleported to " + TP);
                            p.closeInventory();
                        } else {
                            if (e.getInventory().getName().equals(prefix.getPrefix("Inv.Teleporter"))) {
                                e.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }
}
