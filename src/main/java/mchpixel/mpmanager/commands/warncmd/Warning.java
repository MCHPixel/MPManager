package mchpixel.mpmanager.commands.warncmd;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Warning {
    private String playerName;
    private String reason;
    private String warnedBy;
    private String timestamp;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public Warning(String playerName, String reason, String warnedBy) {
        this.playerName = playerName;
        this.reason = reason;
        this.warnedBy = warnedBy;
        this.timestamp = LocalDateTime.now().format(formatter);
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getReason() {
        return reason;
    }

    public String getWarnedBy() {
        return warnedBy;
    }

    public String getTimestamp() {
        return timestamp;
    }
}