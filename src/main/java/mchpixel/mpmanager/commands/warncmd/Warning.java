package mchpixel.mpmanager.commands.warncmd;

import java.time.LocalDateTime;

public class Warning {
    private String playerName;
    private String reason;
    private LocalDateTime timestamp;

    public Warning(String playerName, String reason) {
        this(playerName, reason, LocalDateTime.now());
    }

    public Warning(String playerName, String reason, LocalDateTime timestamp) {
        this.playerName = playerName;
        this.reason = reason;
        this.timestamp = timestamp;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
