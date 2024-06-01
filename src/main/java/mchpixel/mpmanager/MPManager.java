package mchpixel.mpmanager;

import mchpixel.mpmanager.commands.warncmd.ListWarningsCommand;
import mchpixel.mpmanager.commands.warncmd.WarnCommand;
import mchpixel.mpmanager.commands.warncmd.Warning;
import mchpixel.mpmanager.commands.warncmd.WarningSystem;
import mchpixel.mpmanager.features.joinmessage;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class MPManager extends JavaPlugin {
    private WarningSystem warningSystem;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new joinmessage(), this);

        warningSystem = new WarningSystem();
        this.getCommand("warn").setExecutor(new WarnCommand(warningSystem));
        this.getCommand("listwarnings").setExecutor(new ListWarningsCommand(warningSystem));

        getServer().getPluginManager().registerEvents((Listener) new PlayerJoinListener(warningSystem), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Disabled the Plugin");
    }

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
}
