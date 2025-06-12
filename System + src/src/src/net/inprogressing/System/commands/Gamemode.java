package net.inprogressing.System.commands;

import net.inprogressing.System.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 1) {
                if (args[0].equals("0")) {
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(Main.publicprefix + "Set the gamemode to survival");
                } else if (args[0].equals("1")) {
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage(Main.publicprefix + "Set the gamemode to creative");
                } else if (args[0].equals("2")) {
                    p.setGameMode(GameMode.ADVENTURE);
                    p.sendMessage(Main.publicprefix + "Set the gamemode to adventure");
                } else if (args[0].equals("3")) {
                    p.setGameMode(GameMode.SPECTATOR);
                    p.sendMessage(Main.publicprefix + "Set the gamemode to spectator");
                }
            } else if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                if (Bukkit.getOnlinePlayers().contains(target)) {
                    if (args[1].equals("0")) {
                        target.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(Main.publicprefix + "Set the gamemode for §6" + target.getName() + "§7 to survival");
                        target.sendMessage(Main.publicprefix + "Set the gamemode to survival");
                    } else if (args[1].equals("1")) {
                        target.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(Main.publicprefix + "Set the gamemode for §6" + target.getName() + "§7 to creative");
                        target.sendMessage(Main.publicprefix + "Set the gamemode to creative");
                    } else if (args[1].equals("2")) {
                        target.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage(Main.publicprefix + "Set the gamemode for §6" + target.getName() + "§7 to adventure");
                        target.sendMessage(Main.publicprefix + "Set the gamemode to adventure");
                    } else if (args[1].equals("3")) {
                        target.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage(Main.publicprefix + "Set the gamemode for §6" + target.getName() + "§7 to spectator");
                        target.sendMessage(Main.publicprefix + "Set the gamemode to spectator");
                    }
                } else {
                    p.sendMessage(Main.publicprefix + "The player §6" + args[0] + "§7 is not online!");
                }
            } else {
                p.sendMessage(Main.publicprefix + "Use /gm <0:1:2:3> <player>");
            }
        }
        return false;
    }
}
