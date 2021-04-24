package it.coachyt.zessentials;

import it.coachyt.zessentials.commands.fly;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    public FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Objects.requireNonNull(this.getCommand("fly")).setExecutor(new fly());
        System.out.println("[zEssentials] Plugin enabled successfully");

    }
}
