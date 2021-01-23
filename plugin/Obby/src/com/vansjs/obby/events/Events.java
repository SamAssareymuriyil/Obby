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
import org.bukkit.event.player.PlayerQuitEvent;
import java.io.IOException;
import com.fazecast.jSerialComm.SerialPort;

import java.util.Arrays;




public class Events implements Listener {
static String port = "COM4"; // TODO: device name, must be changed
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
    public static void onPlayerJoin (PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.LIGHT_PURPLE + "Welcome to the Server!! :)");
        SerialPort sp = SerialPort.getCommPort(port);
        sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written

        if (sp.openPort()) {
            System.out.println("Port is open :)");
        } else {
            System.out.println("Failed to open port :(");
            return;
        }
    }

    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent event) throws IOException, InterruptedException {
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

        int water;
        if (block == Material.WATER) {
            player.sendMessage(ChatColor.GREEN + "You are standing on Water!");
            water = 1;
        }else{
            water = 0;
        }
        player.sendMessage(ChatColor.DARK_RED + "Your health is at " + health);
        player.sendMessage(ChatColor.YELLOW + "Your hunger is at " + hunger);
//        player.sendMessage(ChatColor.AQUA + "Your exhaustion is at " + exhaustion);
        player.sendMessage(ChatColor.GOLD + "You are facing: " + yaw);

        String num = "";
        if(yaw < 100){num = "0" + Integer.toString(yaw);}
        else{num = Integer.toString(yaw);}
        if(health < 10){num+= "0" + Integer.toString(health) + Integer.toString(water);}
        else{num+= Integer.toString(health) + Integer.toString(water);}
        if(hunger < 10){num+= "0" + Integer.toString(hunger);}
        else{num+= Integer.toString(hunger);}
        int i = Integer.parseInt(num);

        SerialPort sp = SerialPort.getCommPort(port);
        sp.getOutputStream().write(i);
        sp.getOutputStream().flush();
        System.out.println("Sent number: " + i);

        Thread.sleep(250);
    }

    @EventHandler
    public static void onPlayerLeave (PlayerQuitEvent event){
        SerialPort sp = SerialPort.getCommPort(port);
        if (sp.closePort()) {
            System.out.println("Port is closed :)");
        } else {
            System.out.println("Failed to close port :(");
            return;
        }
    }

}
