package mchpixel.mpmanager.commands.warncmd;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WarningSystem {
    private List<Warning> warnings = new ArrayList<>();
    private File dataFile;
    private Gson gson;

    public WarningSystem(File dataFolder) {
        File pluginFolder = new File(dataFolder, "MPManager");
        if (!pluginFolder.exists()) {
            pluginFolder.mkdirs();
        }
        dataFile = new File(pluginFolder, "warnings.json");
        gson = new Gson();
        loadWarnings();
    }

    public void addWarning(String playerName, String reason) {
        Warning warning = new Warning(playerName, reason);
        warnings.add(warning);
        saveWarnings();
    }

    public List<Warning> getWarnings(String playerName) {
        List<Warning> playerWarnings = new ArrayList<>();
        for (Warning warning : warnings) {
            if (warning.getPlayerName().equalsIgnoreCase(playerName)) {
                playerWarnings.add(warning);
            }
        }
        return playerWarnings;
    }

    public void removeAllWarnings(String playerName) {
        Iterator<Warning> iterator = warnings.iterator();
        while (iterator.hasNext()) {
            Warning warning = iterator.next();
            if (warning.getPlayerName().equalsIgnoreCase(playerName)) {
                iterator.remove();
            }
        }
        saveWarnings();
    }

    public boolean removeWarning(String playerName, String reason) {
        Iterator<Warning> iterator = warnings.iterator();
        while (iterator.hasNext()) {
            Warning warning = iterator.next();
            if (warning.getPlayerName().equalsIgnoreCase(playerName) && warning.getReason().equalsIgnoreCase(reason)) {
                iterator.remove();
                saveWarnings();
                return true;
            }
        }
        return false;
    }

    private void saveWarnings() {
        try (FileWriter writer = new FileWriter(dataFile)) {
            gson.toJson(warnings, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadWarnings() {
        if (!dataFile.exists()) {
            return;
        }
        try (FileReader reader = new FileReader(dataFile)) {
            Type listType = new TypeToken<List<Warning>>() {}.getType();
            warnings = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
