package net.inprogressing.System.listener;

import net.inprogressing.System.utils.CustomConfig;
import net.inprogressing.System.utils.PrefixUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class Friends implements Listener {
    private static PrefixUtil prefix = new PrefixUtil();

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();
        meta.setDisplayName(prefix.getPrefix("Friends"));
        meta.setOwningPlayer(p);
        stack.setItemMeta(meta);
        p.getInventory().setItem(5, stack);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        e.setKeepInventory(true);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getCurrentItem() != null) {
            if (e.getCurrentItem().hasItemMeta()) {
                if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
                    int friendlist;
                    String[] friends = new String[0];
                    String[] invites = new String[0];
                    CustomConfig c = new CustomConfig();
                    c.setName("Friends");
                    FileConfiguration cfg = c.getConfig();
                    if (cfg.isSet(p.getUniqueId().toString() + ".Friends")) {
                        if (cfg.getString(p.getUniqueId().toString() + ".Friends").contains(":")) {
                            friends = cfg.getString(p.getUniqueId().toString() + ".Friends").split(":");
                        }
                        friendlist = friends.length;
                    } else {
                        cfg.set(p.getUniqueId().toString() + ".Friends", "");
                        friendlist = 1;
                    }

                    if(cfg.isSet(p.getUniqueId().toString() + ".Invites")){
                        invites = cfg.getString(p.getUniqueId().toString() + ".Invites").split(":");
                    } else {
                        cfg.set(p.getUniqueId().toString() + ".Invites", ":");
                    }

                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Friends"))) {
                        e.setCancelled(true);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Inv.Friend.Online"))) {
                        int place = 0;
                        Inventory inv = Bukkit.getServer().createInventory(null, (Math.round(friendlist / 9)) + 1 * 9, prefix.getPrefix("Inv.Friend.Online"));
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                for (String friend : friends) {
                                    if (!friend.equals("")) {
                                        if (all.getUniqueId().toString().equals(friend)) {
                                            if (!all.equals(p)) {
                                                ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                                                SkullMeta meta = (SkullMeta) stack.getItemMeta();
                                                meta.setDisplayName(all.getName());
                                                meta.setOwningPlayer(all);
                                                stack.setItemMeta(meta);
                                                inv.setItem(place, stack);
                                                place++;
                                            }
                                        }
                                    }
                                }
                            }
                        p.openInventory(inv);
                        e.setCancelled(true);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Inv.Friend.Offline"))) {
                        int place = 0;
                        Inventory inv = Bukkit.getServer().createInventory(null, (Math.round(friendlist / 9)) + 1 * 9, prefix.getPrefix("Inv.Friend.Offline"));
                            for(String friend : friends){
                                OfflinePlayer of = Bukkit.getOfflinePlayer(UUID.fromString(friend));
                                if(of != null) {
                                    if (!Bukkit.getOnlinePlayers().contains(of)){
                                        ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                                        SkullMeta meta = (SkullMeta) stack.getItemMeta();
                                        meta.setDisplayName(of.getName());
                                        meta.setOwningPlayer(of);
                                        stack.setItemMeta(meta);
                                        inv.setItem(place, stack);
                                        place++;
                                    }
                                }
                            }
                        p.openInventory(inv);
                        e.setCancelled(true);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Inv.Friend.Seen"))) {
                        int place = 0;
                        Inventory inv = Bukkit.getServer().createInventory(null, (Math.round(friendlist / 9)) + 1 * 9, prefix.getPrefix("Inv.Friend.Seen"));
                            for (OfflinePlayer all : Bukkit.getOfflinePlayers()) {
                                for (String friend : friends) {
                                    if (!friend.equals("")) {
                                        if (all.getUniqueId().toString().equals(friend)) {
                                            if (!all.equals(p)) {
                                                if(cfg.isSet(all.getUniqueId().toString() + ".Seen")){
                                                    String seen = cfg.getString(all.getUniqueId().toString() + ".Seen");
                                                    ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                                                    SkullMeta meta = (SkullMeta) stack.getItemMeta();
                                                    meta.setDisplayName(all.getName() + "§8 "+seen);
                                                    meta.setOwningPlayer(all);
                                                    stack.setItemMeta(meta);
                                                    inv.setItem(place, stack);
                                                    place++;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        p.openInventory(inv);
                        e.setCancelled(true);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Inv.Friend.Invites"))) {
                        Inventory inv = Bukkit.getServer().createInventory(null, ((friendlist / 9) + 1) * 9, prefix.getPrefix("Inv.Friend.Invites"));
                        int place = 0;
                        for(String invite : invites){
                            if(!invite.equals("")) {
                                Player on = Bukkit.getPlayer(UUID.fromString(invite));
                                OfflinePlayer of = Bukkit.getOfflinePlayer(UUID.fromString(invite));
                                if (on != null) {
                                    ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                                    SkullMeta meta = (SkullMeta) stack.getItemMeta();
                                    meta.setDisplayName("§1§l" + on.getName());
                                    meta.setOwningPlayer(on);
                                    stack.setItemMeta(meta);
                                    inv.setItem(place, stack);
                                    place++;
                                } else {
                                    ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                                    SkullMeta meta = (SkullMeta) stack.getItemMeta();
                                    meta.setDisplayName("§1§l" + of.getName());
                                    meta.setOwningPlayer(of);
                                    stack.setItemMeta(meta);
                                    inv.setItem(place, stack);
                                    place++;
                                }
                            }
                        }
                        p.openInventory(inv);
                        e.setCancelled(true);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Inv.Friend.Invite"))) {
                        Inventory inv = Bukkit.getServer().createInventory(null, ((Bukkit.getOnlinePlayers().size() / 9) + 1) * 9, prefix.getPrefix("Inv.Friend.List"));

                        int place = 0;

                        for(Player all : Bukkit.getOnlinePlayers()){
                            if(friends.length != 0) {
                                for (String fr : friends) {
                                    if (!all.getUniqueId().toString().equals(fr) && !all.equals(p)) {
                                        ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                                        SkullMeta meta = (SkullMeta) stack.getItemMeta();
                                        meta.setDisplayName("§fInvite " + all.getName());
                                        meta.setOwningPlayer(all);
                                        stack.setItemMeta(meta);
                                        inv.setItem(place, stack);
                                        place++;
                                    }
                                }
                            } else {
                                if (!all.equals(p)) {
                                    ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                                    SkullMeta meta = (SkullMeta) stack.getItemMeta();
                                    meta.setDisplayName("§fInvite " + all.getName());
                                    meta.setOwningPlayer(all);
                                    stack.setItemMeta(meta);
                                    inv.setItem(place, stack);
                                    place++;
                                }
                            }
                        }

                        p.openInventory(inv);
                        e.setCancelled(true);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Inv.Friend.List"))) {
                        int place = 0;
                        Inventory inv = Bukkit.getServer().createInventory(null, ((friendlist / 9) + 1) * 9, prefix.getPrefix("Inv.Friend.List"));
                        for(String fr : friends){
                            Player on = Bukkit.getPlayer(UUID.fromString(fr));
                            OfflinePlayer of = Bukkit.getOfflinePlayer(UUID.fromString(fr));
                            if(on != null){
                                ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                                SkullMeta meta = (SkullMeta) stack.getItemMeta();
                                meta.setDisplayName("§2" + on.getName());
                                meta.setOwningPlayer(on);
                                stack.setItemMeta(meta);
                                inv.setItem(place, stack);
                                place++;
                            } else {
                                ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                                SkullMeta meta = (SkullMeta) stack.getItemMeta();
                                meta.setDisplayName("§2" + of.getName());
                                meta.setOwningPlayer(of);
                                stack.setItemMeta(meta);
                                inv.setItem(place, stack);
                                place++;
                            }
                        }
                        p.openInventory(inv);
                        e.setCancelled(true);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Inv.Friend.List"))) {
                        e.setCancelled(true);
                    } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Inv.Friend.Info"))) {
                        e.setCancelled(true);
                    } else {
                        for(String fr : friends){
                            Player on = Bukkit.getPlayer(UUID.fromString(fr));
                            OfflinePlayer of = Bukkit.getOfflinePlayer(UUID.fromString(fr));
                            if(on != null){
                                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§2" + on.getName())) {
                                    p.closeInventory();
                                    Inventory inv = Bukkit.getServer().createInventory(null, ((friendlist / 9) + 1) * 9, prefix.getPrefix("Inv.Friend.List"));
                                    ItemStack stack1 = new ItemStack(Material.getMaterial(351), 1, (short) 10);
                                    ItemMeta meta1 = stack1.getItemMeta();
                                    meta1.setDisplayName(prefix.getPrefix("Inv.Friend.Cancel") + " " + on.getName());
                                    stack1.setItemMeta(meta1);
                                    inv.setItem(2, stack1);

                                    ItemStack stack2 = new ItemStack(Material.getMaterial(351), 1, (short) 1);
                                    ItemMeta meta2 = stack2.getItemMeta();
                                    meta2.setDisplayName(prefix.getPrefix("Inv.Friend.Delete") + " " + on.getName());
                                    stack2.setItemMeta(meta2);
                                    inv.setItem(6, stack2);
                                    p.openInventory(inv);
                                    e.setCancelled(true);
                                } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Inv.Friend.Delete") + " " + on.getName())) {
                                    String SET = null;
                                    for(String bf : friends){
                                        if(!bf.equals(on.getUniqueId().toString())){
                                            SET += bf + ":";
                                        }
                                    }
                                    if(SET != null){
                                        cfg.set(p.getUniqueId().toString() + ".Friends", SET);
                                    } else {
                                        cfg.set(p.getUniqueId().toString() + ".Friends", "");
                                    }

                                    String SET2 = null;
                                    if(cfg.isSet(on.getUniqueId().toString() + ".Friends") && !cfg.get(on.getUniqueId().toString() + ".Friends").equals("")) {
                                        String ONfriends[] = cfg.getString(on.getUniqueId().toString() + ".Friends").split(":");
                                        for(String bf : ONfriends){
                                            if(!bf.equals(of.getUniqueId().toString())){
                                                SET2 += bf + ":";
                                            }
                                        }
                                        if(SET != null){
                                            cfg.set(of.getUniqueId().toString() + ".Friends", SET2);
                                        } else {
                                            cfg.set(of.getUniqueId().toString() + ".Friends", "");
                                        }
                                    }

                                    int place = 0;
                                    Inventory inv = Bukkit.getServer().createInventory(null, ((friendlist / 9) + 1) * 9, prefix.getPrefix("Inv.Friend.List"));
                                    for(String fr1 : friends){
                                        Player on1 = Bukkit.getPlayer(UUID.fromString(fr1));
                                        OfflinePlayer of1 = Bukkit.getOfflinePlayer(UUID.fromString(fr1));
                                        if(on1 != null){
                                            ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                                            SkullMeta meta = (SkullMeta) stack.getItemMeta();
                                            meta.setDisplayName("§2" + on1.getName());
                                            meta.setOwningPlayer(on1);
                                            stack.setItemMeta(meta);
                                            inv.setItem(place, stack);
                                            place++;
                                        } else {
                                            ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                                            SkullMeta meta = (SkullMeta) stack.getItemMeta();
                                            meta.setDisplayName("§2" + of1.getName());
                                            meta.setOwningPlayer(of1);
                                            stack.setItemMeta(meta);
                                            inv.setItem(place, stack);
                                            place++;
                                        }
                                    }
                                    p.openInventory(inv);
                                    c.saveConfig();
                                    e.setCancelled(true);
                                } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Inv.Friend.Cancel") + " " + on.getName())) {
                                    int place = 0;
                                    Inventory inv = Bukkit.getServer().createInventory(null, ((friendlist / 9) + 1) * 9, prefix.getPrefix("Inv.Friend.List"));
                                    for(String fr1 : friends){
                                        Player on1 = Bukkit.getPlayer(UUID.fromString(fr1));
                                        OfflinePlayer of1 = Bukkit.getOfflinePlayer(UUID.fromString(fr1));
                                        if(on1 != null){
                                            ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                                            SkullMeta meta = (SkullMeta) stack.getItemMeta();
                                            meta.setDisplayName("§2" + on1.getName());
                                            meta.setOwningPlayer(on1);
                                            stack.setItemMeta(meta);
                                            inv.setItem(place, stack);
                                            place++;
                                            c.saveConfig();
                                        } else {
                                            ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                                            SkullMeta meta = (SkullMeta) stack.getItemMeta();
                                            meta.setDisplayName("§2" + of1.getName());
                                            meta.setOwningPlayer(of1);
                                            stack.setItemMeta(meta);
                                            inv.setItem(place, stack);
                                            place++;
                                            c.saveConfig();
                                        }
                                    }
                                    p.openInventory(inv);
                                    e.setCancelled(true);
                                }
                            } else {
                                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§2" + of.getName())) {
                                    p.closeInventory();
                                    Inventory inv = Bukkit.getServer().createInventory(null, ((friendlist / 9) + 1) * 9, prefix.getPrefix("Inv.Friend.List"));
                                    ItemStack stack1 = new ItemStack(Material.getMaterial(351), 1, (short) 10);
                                    ItemMeta meta1 = stack1.getItemMeta();
                                    meta1.setDisplayName(prefix.getPrefix("Inv.Friend.Cancel") + " "+of.getName());
                                    stack1.setItemMeta(meta1);
                                    inv.setItem(2, stack1);

                                    ItemStack stack2 = new ItemStack(Material.getMaterial(351), 1, (short) 1);
                                    ItemMeta meta2 = stack2.getItemMeta();
                                    meta2.setDisplayName(prefix.getPrefix("Inv.Friend.Delete") + " " + of.getName());
                                    stack2.setItemMeta(meta2);
                                    inv.setItem(6, stack2);
                                    p.openInventory(inv);
                                } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Inv.Friend.Delete") + " " + of.getName())) {
                                    String SET = null;
                                    for(String bf : friends){
                                        if(!bf.equals(of.getUniqueId().toString())){
                                            SET += bf + ":";
                                        }
                                    }
                                    if(SET != null){
                                        cfg.set(p.getUniqueId().toString() + ".Friends", SET);
                                    } else {
                                        cfg.set(p.getUniqueId().toString() + ".Friends", "");
                                    }

                                    String SET2 = null;
                                    if(cfg.isSet(of.getUniqueId().toString() + ".Friends") && !cfg.get(of.getUniqueId().toString() + ".Friends").equals("")) {
                                        String OFfriends[] = cfg.getString(of.getUniqueId().toString() + ".Friends").split(":");
                                        for(String bf : OFfriends){
                                            if(!bf.equals(of.getUniqueId().toString())){
                                                SET2 += bf + ":";
                                            }
                                        }
                                        if(SET != null){
                                            cfg.set(of.getUniqueId().toString() + ".Friends", SET2);
                                        } else {
                                            cfg.set(of.getUniqueId().toString() + ".Friends", "");
                                        }
                                    }
                                    int place = 0;
                                    Inventory inv = Bukkit.getServer().createInventory(null, ((friendlist / 9) + 1) * 9, prefix.getPrefix("Inv.Friend.List"));
                                    for(String fr1 : friends){
                                        Player on1 = Bukkit.getPlayer(UUID.fromString(fr1));
                                        OfflinePlayer of1 = Bukkit.getOfflinePlayer(UUID.fromString(fr1));
                                        if(on1 != null){
                                            ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                                            SkullMeta meta = (SkullMeta) stack.getItemMeta();
                                            meta.setDisplayName("§2" + on1.getName());
                                            meta.setOwningPlayer(on1);
                                            stack.setItemMeta(meta);
                                            inv.setItem(place, stack);
                                            place++;
                                        } else {
                                            ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                                            SkullMeta meta = (SkullMeta) stack.getItemMeta();
                                            meta.setDisplayName("§2" + of1.getName());
                                            meta.setOwningPlayer(of1);
                                            stack.setItemMeta(meta);
                                            inv.setItem(place, stack);
                                            place++;
                                        }
                                    }
                                    p.openInventory(inv);
                                    e.setCancelled(true);
                                } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Inv.Friend.Cancel") + " " + of.getName())) {
                                    int place = 0;
                                    Inventory inv = Bukkit.getServer().createInventory(null, ((friendlist / 9) + 1) * 9, prefix.getPrefix("Inv.Friend.List"));
                                    for(String fr1 : friends){
                                        Player on1 = Bukkit.getPlayer(UUID.fromString(fr1));
                                        OfflinePlayer of1 = Bukkit.getOfflinePlayer(UUID.fromString(fr1));
                                        if(on1 != null){
                                            ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                                            SkullMeta meta = (SkullMeta) stack.getItemMeta();
                                            meta.setDisplayName("§2" + on1.getName());
                                            meta.setOwningPlayer(on1);
                                            stack.setItemMeta(meta);
                                            inv.setItem(place, stack);
                                            place++;
                                        } else {
                                            ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                                            SkullMeta meta = (SkullMeta) stack.getItemMeta();
                                            meta.setDisplayName("§2" + of1.getName());
                                            meta.setOwningPlayer(of1);
                                            stack.setItemMeta(meta);
                                            inv.setItem(place, stack);
                                            place++;
                                        }
                                    }
                                    p.openInventory(inv);
                                    e.setCancelled(true);
                                }
                            }
                        }

                        //Invites
                        for(String invite : invites){
                            if(!invite.equals("")) {
                                Player on = Bukkit.getPlayer(UUID.fromString(invite));
                                OfflinePlayer of = Bukkit.getOfflinePlayer(UUID.fromString(invite));
                                if (on != null) {
                                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§1§l" + of.getName())) {
                                        Inventory inv = Bukkit.getServer().createInventory(null, ((friendlist / 9) + 1) * 9, prefix.getPrefix("Inv.Friend.Invites"));

                                        ItemStack stack1 = new ItemStack(Material.getMaterial(351), 1, (short) 10);
                                        ItemMeta meta1 = stack1.getItemMeta();
                                        meta1.setDisplayName(prefix.getPrefix("Inv.Friend.Invite_Accept") + " " + on.getName());
                                        stack1.setItemMeta(meta1);
                                        inv.setItem(2, stack1);

                                        ItemStack stack2 = new ItemStack(Material.getMaterial(351), 1, (short) 1);
                                        ItemMeta meta2 = stack2.getItemMeta();
                                        meta2.setDisplayName(prefix.getPrefix("Inv.Friend.Invite_Deny") + " " + on.getName());
                                        stack2.setItemMeta(meta2);
                                        inv.setItem(6, stack2);

                                        p.openInventory(inv);
                                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Inv.Friend.Invite_Accept") + " " + on.getName())) {
                                        String psetin = "";
                                        for (String in : invites) {
                                            if (!in.equals(invite)) {
                                                psetin += in + ":";
                                            }
                                        }
                                        cfg.set(p.getUniqueId().toString() + ".Invites", psetin);

                                        String psetfr = cfg.getString(p.getUniqueId().toString() + ".Friends");
                                        psetfr += on.getUniqueId().toString() + ":";
                                        cfg.set(p.getUniqueId().toString() + ".Friends", psetfr);

                                        String osetfr = "";
                                        if (!cfg.getString(on.getUniqueId().toString() + ".Friends").equals("")) {
                                            osetfr = cfg.getString(on.getUniqueId().toString() + ".Friends");
                                        }
                                        osetfr += p.getUniqueId().toString() + ":";
                                        cfg.set(on.getUniqueId().toString() + ".Friends", osetfr);
                                        p.closeInventory();
                                        e.setCancelled(true);
                                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Inv.Friend.Invite_Deny") + " " + of.getName())) {
                                        String psetin = "";
                                        for (String in : invites) {
                                            if (!in.equals(invite)) {
                                                psetin += in + ":";
                                            }
                                        }
                                        cfg.set(p.getUniqueId().toString() + ".Invites", psetin);
                                        p.closeInventory();
                                        e.setCancelled(true);
                                    }
                                    e.setCancelled(true);
                                } else {
                                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§1§l" + of.getName())) {
                                        Inventory inv = Bukkit.getServer().createInventory(null, ((friendlist / 9) + 1) * 9, prefix.getPrefix("Inv.Friend.Invites"));

                                        ItemStack stack1 = new ItemStack(Material.getMaterial(351), 1, (short) 10);
                                        ItemMeta meta1 = stack1.getItemMeta();
                                        meta1.setDisplayName(prefix.getPrefix("Inv.Friend.Invite_Accept") + " " + of.getName());
                                        stack1.setItemMeta(meta1);
                                        inv.setItem(2, stack1);

                                        ItemStack stack2 = new ItemStack(Material.getMaterial(351), 1, (short) 1);
                                        ItemMeta meta2 = stack2.getItemMeta();
                                        meta2.setDisplayName(prefix.getPrefix("Inv.Friend.Invite_Deny") + " " + of.getName());
                                        stack2.setItemMeta(meta2);
                                        inv.setItem(6, stack2);

                                        p.openInventory(inv);
                                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Inv.Friend.Invite_Accept") + " " + of.getName())) {
                                        String psetin = "";
                                        for (String in : invites) {
                                            if (!in.equals(invite)) {
                                                psetin += in + ":";
                                            }
                                        }
                                        cfg.set(p.getUniqueId().toString() + ".Invites", psetin);

                                        String psetfr = cfg.getString(p.getUniqueId().toString() + ".Friends");
                                        psetfr += of.getUniqueId().toString() + ":";
                                        cfg.set(p.getUniqueId().toString() + ".Friends", psetfr);

                                        String osetfr = "";
                                        if (!cfg.getString(of.getUniqueId().toString() + ".Friends").equals("")) {
                                            osetfr = cfg.getString(of.getUniqueId().toString() + ".Friends");
                                        }
                                        osetfr += p.getUniqueId().toString() + ":";
                                        cfg.set(of.getUniqueId().toString() + ".Friends", osetfr);
                                        p.closeInventory();
                                        e.setCancelled(true);
                                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Inv.Friend.Invite_Deny") + " " + of.getName())) {
                                        String psetin = "";
                                        for (String in : invites) {
                                            if (!in.equals(invite)) {
                                                psetin += in + ":";
                                            }
                                        }
                                        cfg.set(p.getUniqueId().toString() + ".Invites", psetin);
                                        p.closeInventory();
                                        e.setCancelled(true);
                                    }
                                    e.setCancelled(true);
                                }
                            }
                        }

                        //Invites
                        for(Player all : Bukkit.getOnlinePlayers()){
                            if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§fInvite "+all.getName())){
                                if(cfg.isSet(all.getUniqueId().toString() + ".Invites")) {
                                    String SET = cfg.getString(all.getUniqueId().toString() + ".Invites");
                                    SET += p.getUniqueId().toString() + ":";
                                    cfg.set(all.getUniqueId().toString() + ".Invites", SET);
                                }
                                e.setCancelled(true);
                            }
                        }
                    }
                    for(Player all : Bukkit.getOnlinePlayers()){
                        if(e.getCurrentItem().getItemMeta().getDisplayName().contains(all.getName())){
                            e.setCancelled(true);
                        }
                    }
                    c.saveConfig();
                }
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if(e.getItemDrop() != null){
            if(e.getItemDrop().getItemStack() != null){
                if(e.getItemDrop().getItemStack().hasItemMeta()){
                    if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(prefix.getPrefix("Friends"))){
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
                    if(e.getItem().getItemMeta().getDisplayName() != null) {
                        if (e.getItem().getItemMeta().getDisplayName().equals(prefix.getPrefix("Friends"))) {
                            Inventory inv = Bukkit.getServer().createInventory(null, 3 * 9, prefix.getPrefix("Inv.Friends"));

                            ItemStack stack1 = new ItemStack(Material.getMaterial(351), 1, (short) 10);
                            ItemMeta meta1 = stack1.getItemMeta();
                            meta1.setDisplayName(prefix.getPrefix("Inv.Friend.Online"));
                            stack1.setItemMeta(meta1);
                            inv.setItem(10, stack1);

                            ItemStack stack2 = new ItemStack(Material.getMaterial(351), 1, (short) 10);
                            ItemMeta meta2 = stack2.getItemMeta();
                            meta2.setDisplayName(prefix.getPrefix("Inv.Friend.Offline"));
                            stack2.setItemMeta(meta2);
                            inv.setItem(11, stack2);

                            ItemStack stack3 = new ItemStack(Material.getMaterial(351), 1, (short) 10);
                            ItemMeta meta3 = stack3.getItemMeta();
                            meta3.setDisplayName(prefix.getPrefix("Inv.Friend.Seen"));
                            stack3.setItemMeta(meta3);
                            inv.setItem(12, stack3);

                            ItemStack stack4 = new ItemStack(Material.getMaterial(351), 1, (short) 10);
                            ItemMeta meta4 = stack4.getItemMeta();
                            meta4.setDisplayName(prefix.getPrefix("Inv.Friend.Invites"));
                            stack4.setItemMeta(meta4);
                            inv.setItem(13, stack4);

                            ItemStack stack5 = new ItemStack(Material.getMaterial(351), 1, (short) 10);
                            ItemMeta meta5 = stack5.getItemMeta();
                            meta5.setDisplayName(prefix.getPrefix("Inv.Friend.Invite"));
                            stack5.setItemMeta(meta5);
                            inv.setItem(14, stack5);

                            ItemStack stack6 = new ItemStack(Material.getMaterial(351), 1, (short) 10);
                            ItemMeta meta6 = stack1.getItemMeta();
                            meta6.setDisplayName(prefix.getPrefix("Inv.Friend.List"));
                            stack6.setItemMeta(meta6);
                            inv.setItem(15, stack6);

                            ItemStack stack7 = new ItemStack(Material.getMaterial(351), 1, (short) 10);
                            ItemMeta meta7 = stack7.getItemMeta();
                            meta7.setDisplayName(prefix.getPrefix("Inv.Friend.Info"));
                            stack7.setItemMeta(meta7);
                            inv.setItem(16, stack7);

                            p.openInventory(inv);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(e.getItemInHand().hasItemMeta()){
            if(e.getItemInHand().getItemMeta().hasDisplayName()) {
                if(e.getItemInHand().getItemMeta().getDisplayName().equals(prefix.getPrefix("Friends"))) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
