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

        String pref = ChatColor.stripColor(player.getName());

        player.sendMessage(ChatColor.WHITE + pref + " Welcome to the Server!");

        player.sendTitle(ChatColor.GOLD + "Welcome to MCHPixel", player.getName(), 20, 100, 20);
    }

}
