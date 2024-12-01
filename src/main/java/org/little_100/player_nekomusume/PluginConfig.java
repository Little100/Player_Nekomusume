package org.little_100.player_nekomusume;

import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class PluginConfig {
    private Player_Nekomusume plugin;
    private boolean enabled;
    private List<String> prefixList;
    private List<String> suffixList;
    private List<String> prefixColorList;
    private List<String> suffixColorList;
    private Random random;

    public PluginConfig(Player_Nekomusume plugin) {
        this.plugin = plugin;
        this.random = new Random();
        loadConfig();
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();

        FileConfiguration config = plugin.getConfig();

        this.enabled = config.getBoolean("enabled", true);
        this.prefixList = config.getStringList("text.prefix");  // 修改这里
        this.suffixList = config.getStringList("text.suffix");  // 修改这里
        this.prefixColorList = config.getStringList("color.prefix");
        this.suffixColorList = config.getStringList("color.suffix");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getRandomPrefix() {
        return prefixList.isEmpty() ? "" : prefixList.get(random.nextInt(prefixList.size()));
    }

    public String getRandomSuffix() {
        return suffixList.isEmpty() ? "" : suffixList.get(random.nextInt(suffixList.size()));
    }

    public String getRandomPrefixColor() {
        return prefixColorList.isEmpty() ? "§f" : translateColorCodes(prefixColorList.get(random.nextInt(prefixColorList.size())));
    }

    public String getRandomSuffixColor() {
        return suffixColorList.isEmpty() ? "§f" : translateColorCodes(suffixColorList.get(random.nextInt(suffixColorList.size())));
    }

    // 转换颜色代码
    public String translateColorCodes(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}