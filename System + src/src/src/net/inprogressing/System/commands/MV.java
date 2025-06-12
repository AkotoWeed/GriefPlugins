package net.inprogressing.System.commands;

import net.inprogressing.System.main.Main;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Consumer;
import org.bukkit.util.Vector;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MV implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 2 && args[0].equals("create")) {
                if (!new File(new File("").getAbsolutePath() + "\\" + args[1]).exists()) {
                    Bukkit.getServer().createWorld(new WorldCreator(args[1]));
                    Bukkit.getWorld(args[1]).save();
                    p.sendMessage(Main.publicprefix + "Created world §6" + args[1] + "§7.");
                } else {
                    p.sendMessage(Main.publicprefix + "The world §6" + args[1] + "§7 already exists!");
                }
            } else if (args.length == 2 && args[0].equals("tp")) {
                if (new File(new File("").getAbsolutePath() + "\\" + args[1]).exists()) {
                    if (Bukkit.getServer().getWorld(args[1]) != null) {
                        p.teleport(Bukkit.getWorld(args[1]).getSpawnLocation());
                        p.sendMessage(Main.publicprefix + "Teleported to spawn from the world §6" + args[1] + "§7.");
                    } else {
                        p.sendMessage(Main.publicprefix + "Can not teleport to the world §6" + args[1] + "§7!");
                    }
                } else {
                    p.sendMessage(Main.publicprefix + "The world §6" + args[1] + "§7 not exists!");
                }
            } else if (args.length == 2 && args[0].equals("delete")) {
                if (new File(new File("").getAbsolutePath() + "\\" + args[1]).exists()) {
                    Bukkit.unloadWorld(Bukkit.getWorld(args[1]), true);
                    deleteFile(new File(new File("").getAbsolutePath() + "\\" + args[1]));
                    p.sendMessage(Main.publicprefix + "Deleted world §6" + args[1] + "§7.");
                } else {
                    p.sendMessage(Main.publicprefix + "The world §6" + args[1] + "§7 not exists!");
                }
            } else if (args.length == 1 && args[0].equals("list")) {
                p.sendMessage("Worlds: " + Bukkit.getWorlds().size());
                for (World world : Bukkit.getWorlds()) {
                    p.sendMessage("§a" + world.getName());
                }
            } else {
                p.sendMessage(Main.publicprefix + "Use /mv <tp:delete:list:create>");
            }
        }
        return false;
    }

    private void deleteFile(File file){
        File[] files = file.listFiles();
        for(File f : files){
            if(f.isDirectory()){
                deleteFile(f);
            } else {
                f.delete();
            }
        }
        file.delete();
    }
}