package mchpixel.mpmanager.commands.warncmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class RemoveWarningsCommand implements CommandExecutor {
    private WarningSystem warningSystem;

    public RemoveWarningsCommand(WarningSystem warningSystem) {
        this.warningSystem = warningSystem;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("Usage: /removewarning <player> <reason>");
            return false;
        }

        String playerName = args[0];
        String reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        boolean removed = warningSystem.removeWarning(playerName, reason);
        if (removed) {
            sender.sendMessage("Warning removed for player " + playerName);
        } else {
            sender.sendMessage("No matching warning found for player " + playerName);
        }
        return true;
    }
}
