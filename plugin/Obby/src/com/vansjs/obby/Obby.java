package com.vansjs.obby;

import com.vansjs.obby.events.Events;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Obby extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Events(), this);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin Started");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Plugin Stopped.");
    }
}
