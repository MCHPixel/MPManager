package mchpixel.mpmanager.commands.warncmd;

public class Warning {
    private String playerName;
    private String reason;
    private long timestamp;

    public Warning(String playerName, String reason, long timestamp) {
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

    public long getTimestamp() {
        return timestamp;
    }
}
