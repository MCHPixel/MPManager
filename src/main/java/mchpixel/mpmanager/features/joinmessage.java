package mchpixel.mpmanager.features;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class joinmessage implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        // Don't open auctionhouse here
        Player player = event.getPlayer();

        String username = ChatColor.stripColor(player.getDisplayName());

        player.sendMessage(ChatColor.DARK_PURPLE + "Welcome back " +
                ChatColor.BLUE + ChatColor.BOLD + username +
                ChatColor.DARK_PURPLE + " to MCHPixel!");

        player.sendTitle(ChatColor.GOLD +
                "Welcome to MCHPixel", ChatColor.GRAY + username, 20, 100, 20);

        System.out.println(ChatColor.BOLD + player.getDisplayName() + ChatColor.RESET + ChatColor.DARK_PURPLE + " has joined!");
    }

    @EventHandler
    public void newJoin(PlayerQuitEvent event) {

    }
}
