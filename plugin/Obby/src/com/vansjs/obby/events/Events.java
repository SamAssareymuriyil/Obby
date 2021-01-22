package com.vansjs.obby.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Arrays;

public class Events implements Listener {

//    @EventHandler
//    public static void onPlayerJoin(PlayerJoinEvent event) {
//        Player player = event.getPlayer();
//        player.sendMessage(ChatColor.LIGHT_PURPLE + "Welcome to the Server!! :)");
//    }

//    @EventHandler
//    public static void onPlayerWalk(PlayerMoveEvent event) {
//        Player player = event.getPlayer();
//
//        int x = player.getLocation().getBlockX();
//        int y = player.getLocation().getBlockY();
//        int z = player.getLocation().getBlockZ();
//
//        Material block = player.getWorld().getBlockAt(x, y-1, z).getType();
//
//        if (block == Material.STONE) {
//            player.sendMessage(ChatColor.GREEN + "You are standing on Stone!");
//        }
//    }
//


    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        int health = (int) Math.ceil(player.getHealth());
        int hunger = (int) Math.ceil(player.getFoodLevel());
//        double exhaustion = player.getExhaustion();
        int yaw = (int) Math.floor(player.getLocation().getYaw());
        if (yaw < 0) {
            yaw += 360;
        }
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        Material block = player.getWorld().getBlockAt(x, y, z).getType();

        if (block == Material.WATER) {
            player.sendMessage(ChatColor.GREEN + "You are standing on Water!");
        }
        player.sendMessage(ChatColor.DARK_RED + "Your health is at " + health);
        player.sendMessage(ChatColor.YELLOW + "Your hunger is at " + hunger);
//        player.sendMessage(ChatColor.AQUA + "Your exhaustion is at " + exhaustion);
        player.sendMessage(ChatColor.GOLD + "You are facing: " + yaw);
    }

}
