package mchpixel.mpmanager.commands.warncmd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import mchpixel.mpmanager.LocalDateTimeAdapter;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class WarningSystem {
    private List<Warning> warnings = new ArrayList<>();
    private File dataFile;
    private Gson gson;

    public WarningSystem(File dataFolder) {
        File pluginFolder = new File(String.valueOf(dataFolder));
        if (!pluginFolder.exists()) {
            pluginFolder.mkdirs();
        }
        dataFile = new File(pluginFolder, "warnings.json");

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        gson = gsonBuilder.create();

        loadWarnings();
    }

    public void addWarning(String playerName, String reason, String warnedBy) {
        Warning warning = new Warning(playerName, reason, warnedBy);
        warnings.add(warning);
        saveWarnings();
    }

    public List<Warning> getWarnings(String playerName) {
        return warnings.stream()
                .filter(warning -> warning.getPlayerName().equalsIgnoreCase(playerName))
                .collect(Collectors.toList());
    }

    public void removeAllWarnings(String playerName) {
        warnings.removeIf(warning -> warning.getPlayerName().equalsIgnoreCase(playerName));
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
        try (Writer writer = new FileWriter(dataFile)) {
            gson.toJson(warnings, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadWarnings() {
        if (dataFile.exists()) {
            try (Reader reader = new FileReader(dataFile)) {
                Type warningListType = new TypeToken<ArrayList<Warning>>() {}.getType();
                warnings = gson.fromJson(reader, warningListType);
                if (warnings == null) {
                    warnings = new ArrayList<>();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (com.google.gson.JsonSyntaxException e) {
                // Handle the case where the file is not correctly formatted
                warnings = new ArrayList<>();
            }
        }
    }
}
