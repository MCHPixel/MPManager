package mchpixel.mpmanager.commands.warncmd;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WarningSystem {
    private static final String FILE_PATH = "warnings.json";
    private List<Warning> warnings;
    private Gson gson;

    public WarningSystem() {
        gson = new Gson();
        loadWarnings();
    }

    private void loadWarnings() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Warning>>() {}.getType();
            warnings = gson.fromJson(reader, listType);
            if (warnings == null) {
                warnings = new ArrayList<>();
            }
            System.out.println("Warnings loaded: " + warnings.size());
        } catch (IOException e) {
            warnings = new ArrayList<>();
            System.out.println("Could not load warnings: " + e.getMessage());
        }
    }

    public void saveWarnings() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(warnings, writer);
            System.out.println("Warnings saved: " + warnings.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addWarning(String playerName, String reason) {
        warnings.add(new Warning(playerName, reason, System.currentTimeMillis()));
        System.out.println("Added warning for player: " + playerName);
        saveWarnings();
    }

    public List<Warning> getWarnings(String playerName) {
        List<Warning> playerWarnings = new ArrayList<>();
        System.out.println("Getting warnings for player: " + playerName);
        for (Warning warning : warnings) {
            System.out.println("Checking warning for player: " + warning.getPlayerName());
            if (warning.getPlayerName().equalsIgnoreCase(playerName)) {
                playerWarnings.add(warning);
            }
        }
        System.out.println("Warnings found: " + playerWarnings.size());
        return playerWarnings;
    }
}
