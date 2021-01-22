package com.vansjs.obby.events;

import com.fazecast.jSerialComm.SerialPort;
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
    public static void getPlayerDirection(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        float yawF = player.getLocation().getYaw();
        int yaw = (int) Math.floor(yawF);

        if (yaw < 0) {
            yaw += 360;
        }

        // player.sendMessage(ChatColor.GOLD + "You are facing: " + yaw);

        SerialPort[] ports = SerialPort.getCommPorts();
        player.sendMessage(ChatColor.BLUE + Arrays.toString(ports));
    }

    @EventHandler
    public static void getPlayerHealth(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        double healthF = player.getHealth();
        int health = (int) Math.ceil(healthF);

        player.sendMessage(ChatColor.DARK_RED + "Your health is at " + health);
    }
    @EventHandler
    public static void getPlayerHunger(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        double hungerF = player.getFoodLevel();
        int hunger = (int) Math.ceil(hungerF);

        player.sendMessage(ChatColor.RED + "Your hunger is at " + hunger);
    }
    @EventHandler
    public static void getPlayerExhaustion(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        double exhaustionF = player.getExhaustion();
        int exhaustion = (int) Math.ceil(exhaustionF);

        player.sendMessage(ChatColor.DARK_BLUE + "Your exhaustion is at " + exhaustion);
    }

}
