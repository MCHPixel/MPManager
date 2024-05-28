package mchpixel.mpmanager.features;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class joinmessage implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Don't open auction house here
        Player player = event.getPlayer();

        String username = ChatColor.stripColor(player.getName());

        player.sendMessage(ChatColor.DARK_PURPLE + "Welcome back " +
                ChatColor.MAGIC + username +
                ChatColor.DARK_PURPLE + " to MCHPixel!");

        player.sendTitle(ChatColor.GOLD + "Welcome to MCHPixel",ChatColor.GRAY + username, 20, 100, 20);
    }

}
