package mchpixel.mpmanager.commands.warncmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

import java.util.List;

public class ListWarningsCommand implements CommandExecutor {
    private WarningSystem warningSystem;

    public ListWarningsCommand(WarningSystem warningSystem) {
        this.warningSystem = warningSystem;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("Usage: /listwarnings <player>");
            return false;
        }

        String playerName = args[0];
        List<Warning> warnings = warningSystem.getWarnings(playerName);
        if (warnings.isEmpty()) {
            sender.sendMessage("No warnings found for player " + playerName);
        } else {
            sender.sendMessage("Warnings for " + playerName + ":");
            for (Warning warning : warnings) {
                sender.sendMessage(ChatColor.RED + warning.getReason() + " - " + warning.getTimestamp());
            }
        }
        return true;
    }
}
