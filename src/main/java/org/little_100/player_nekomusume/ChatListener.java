package org.little_100.player_nekomusume;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    private Player_Nekomusume plugin;
    private PluginConfig config;

    public ChatListener(Player_Nekomusume plugin, PluginConfig config) {
        this.plugin = plugin;
        this.config = config;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        // 检查插件是否启用
        if (!config.isEnabled()) return;

        String originalMessage = event.getMessage();

        // 获取随机前缀和后缀
        String prefix = config.getRandomPrefix();
        String suffix = config.getRandomSuffix();

        // 获取随机颜色
        String prefixColor = config.getRandomPrefixColor();
        String suffixColor = config.getRandomSuffixColor();

        // 组装新消息
        String modifiedMessage = String.format("%s[%s]%s %s %s[%s]",
                prefixColor, prefix,
                "§f", originalMessage,
                suffixColor, suffix);

        // 设置新的聊天消息
        event.setMessage(modifiedMessage);
    }
}