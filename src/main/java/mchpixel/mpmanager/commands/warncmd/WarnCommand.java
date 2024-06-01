package mchpixel.mpmanager.commands.warncmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class WarnCommand implements CommandExecutor {
    private WarningSystem warningSystem;

    public WarnCommand(WarningSystem warningSystem) {
        this.warningSystem = warningSystem;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Command received: " + command.getName());
        sender.sendMessage("Label: " + label);

        if (args.length < 2) {
            sender.sendMessage("Usage: /warn <player> <reason>");
            return false;
        }

        String playerName = args[0];
        String reason = joinArguments(args, 1);

        warningSystem.addWarning(playerName, reason);
        sender.sendMessage("Player " + playerName + " has been warned for: " + reason);

        Player player = sender.getServer().getPlayerExact(playerName);
        if (player != null && player.isOnline()) {
            player.sendMessage(ChatColor.RED + "You have been warned for: " + reason);
        } else {
            sender.sendMessage("Player " + playerName + " is not online or does not exist.");
        }

        return true;
    }

    private String joinArguments(String[] args, int startIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i < args.length; i++) {
            sb.append(args[i]);
            if (i < args.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
