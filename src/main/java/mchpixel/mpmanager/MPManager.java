package mchpixel.mpmanager;

import mchpixel.mpmanager.features.joinmessage;
import org.bukkit.plugin.java.JavaPlugin;

public final class MPManager extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new joinmessage(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Disabled the Plugin");
    }
}
