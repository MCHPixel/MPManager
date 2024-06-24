package mchpixel.mpmanager.commands.warncmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import java.util.Arrays;

public class WarnCommand implements CommandExecutor {
    private WarningSystem warningSystem;

    public WarnCommand(WarningSystem warningSystem) {
        this.warningSystem = warningSystem;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("Usage: /warn <player> <reason>");
            return false;
        }

        String playerName = args[0];
        String reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        String warnedBy = sender.getName();

        warningSystem.addWarning(playerName, reason, warnedBy);
        sender.sendMessage("Player " + playerName + " has been warned for: " + reason);

        Player player = sender.getServer().getPlayerExact(playerName);
        if (player != null && player.isOnline()) {
            player.sendMessage(ChatColor.RED + "You have been warned for: " + reason + " by " + warnedBy);
        }

        return true;
    }
}
