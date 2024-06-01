package mchpixel.mpmanager.features;

import mchpixel.mpmanager.commands.warncmd.Warning;
import mchpixel.mpmanager.commands.warncmd.WarningSystem;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import java.util.List;

public class PlayerJoinListener implements Listener {
    private WarningSystem warningSystem;

    public PlayerJoinListener(WarningSystem warningSystem) {
        this.warningSystem = warningSystem;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        List<Warning> warnings = warningSystem.getWarnings(playerName);

        player.sendMessage(ChatColor.DARK_PURPLE + "You have " + warnings.size() + " warnings.");

        for (Warning warning : warnings) {
            player.sendMessage(ChatColor.RED + "Warning: " + warning.getReason());
        }
    }
}