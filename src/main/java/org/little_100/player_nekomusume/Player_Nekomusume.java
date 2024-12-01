package org.little_100.player_nekomusume;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Player_Nekomusume extends JavaPlugin {
    private PluginConfig config;
    private ChatListener chatListener;

    @Override
    public void onEnable() {
        // 检查服务器版本
        String version = Bukkit.getServer().getBukkitVersion();
        getLogger().info("服务器版本: " + version);

        // 初始化配置
        config = new PluginConfig(this);

        // 注册聊天监听器
        chatListener = new ChatListener(this, config);
        getServer().getPluginManager().registerEvents(chatListener, this);

        // 注册指令
        getCommand("nekomusume").setExecutor(this);

        getLogger().info("" +
                "   __ _ _   _   _          _  ___   ___  \n" +
                "  / /(_) |_| |_| | ___    / |/ _ \\ / _ \\ \n" +
                " / / | | __| __| |/ _ \\   | | | | | | | |\n" +
                "/ /__| | |_| |_| |  __/   | | |_| | |_| |\n" +
                "\\____/_|\\__|\\__|_|\\___|___|_|\\___/ \\___/ \n" +
                "                     |_____|             ");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("nekomusume")) {
            // 检查权限
            if (!sender.hasPermission("nekomusume.reload")) {
                sender.sendMessage("§c你没有权限使用此命令");
                return true;
            }

            // 重载配置
            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                // 重新加载配置
                config.loadConfig();

                sender.sendMessage("§a配置已成功重载!");
                return true;
            }

            // 显示帮助
            sender.sendMessage("§b插件命令:");
            sender.sendMessage("§a/nekomusume reload - 重载配置");
            return true;
        }
        return false;
    }

    @Override
    public void onDisable() {
        getLogger().info("Player_Nekomusume 插件已关闭!");
    }

    // 版本兼容性检查方法
    public boolean isCompatibleVersion() {
        String version = Bukkit.getServer().getBukkitVersion();
        return version.startsWith("1.12") || version.startsWith("1.13") ||
                version.startsWith("1.14") || version.startsWith("1.15") ||
                version.startsWith("1.16") || version.startsWith("1.17") ||
                version.startsWith("1.18") || version.startsWith("1.19") ||
                version.startsWith("1.20") || version.startsWith("1.21");
    }
}