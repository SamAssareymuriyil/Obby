package com.vansjs.obby.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import com.fazecast.jSerialComm.SerialPort;
import java.io.PrintWriter;

public class Events implements Listener {
    static String port = "COM4"; // TODO: device name, must be changed
    static SerialPort chosenPort = SerialPort.getCommPort(port);
    static Thread thread = new Thread();
    static PrintWriter output = new PrintWriter(chosenPort.getOutputStream());

    // connect to serial port, if accessible
    @EventHandler
    public static void onPlayerJoin (PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.LIGHT_PURPLE + "Welcome to the Server!! :)");
        if(chosenPort.openPort()){
            try {Thread.sleep(100); } catch(Exception e) {}
            player.sendMessage(ChatColor.GREEN + "Obby is connected. :)");
        }
        else{
            player.sendMessage(ChatColor.RED + "OBBY IS NOT CONNECTED, CHECK COM PORT");
        }
    }

    // update player status as they move
    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        int health = (int) Math.ceil(player.getHealth());
        int hunger = (int) Math.ceil(player.getFoodLevel());
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
        player.sendMessage(ChatColor.GOLD + "You are facing: " + yaw);

        // send info to serial
        output.print(4000+yaw);
        output.flush();
        try {Thread.sleep(300);} catch(Exception e) {}
        output.print(3000+health);
        output.flush();
        try {Thread.sleep(100);} catch(Exception e) {}
        output.print(2000+water);
        output.flush();
        try {Thread.sleep(100);} catch(Exception e) {}
        output.print(1000+hunger);
        output.flush();
        try {Thread.sleep(100);} catch(Exception e) {}

        thread.start();
    }

    @EventHandler
    public static void onPlayerLeave (PlayerQuitEvent event){
        chosenPort.closePort();
    }
}