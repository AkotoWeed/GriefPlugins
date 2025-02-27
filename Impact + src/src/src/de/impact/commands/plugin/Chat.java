package de.impact.commands.plugin;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.*;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class Chat extends Command {

    public Chat() {
        super("Chat", "Chat (!User) <Message>", "Send a normal message in chat", CommandCategory.PLUGIN, "C");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if (aliases.length < 1) {
            sendUsage(p);
            return;
        }

        StringBuilder chatMessage = new StringBuilder();

        for (String s : aliases)
            chatMessage.append(ChatColor.translateAlternateColorCodes('&', s)).append(" ");

        if(aliases[0].startsWith("!") && aliases.length > 1) {

            Player pl = Bukkit.getPlayerExact(aliases[0].replaceFirst("!", ""));

            if(pl == null) {
                ChatUtils.sendMessage(p, "This player is not online");
                return;
            }

            String use = chatMessage.toString().replaceFirst("!(?i)" + pl.getName(), "").trim();

            if(use.startsWith(PrefixUtils.getPrefix() + "login")) {
                ChatUtils.sendMessage(p, "Please do not try to login twice");
                return;
            }

            boolean login = UserUtils.isLoggedIn(pl.getUniqueId());

            if(login)
                UserUtils.logoutSilent(pl.getUniqueId());

            pl.chat(use);

            if(login)
                UserUtils.loginSilent(pl.getUniqueId());

            return;
        }

        if(chatMessage.toString().trim().startsWith(PrefixUtils.getPrefix() + "login")) {
            ChatUtils.sendMessage(p, "Please do not try to login twice");
            return;
        }

        UserUtils.logoutSilent(p.getUniqueId());

        p.chat(chatMessage.toString().trim());

        UserUtils.loginSilent(p.getUniqueId());

    }

}
