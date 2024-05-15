package mchpixel.mpmanager.features;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class joinmessage {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Don't open auction house here
        Player player = event.getPlayer();

        String pref = ChatColor.stripColor(player.getDisplayName());

        player.sendMessage(ChatColor.WHITE + pref + "Welcome to the Server!");

        player.sendTitle(ChatColor.GOLD + "Welcome to MCHPixel", player.getName(), 20, 60, 20);
    }
}
