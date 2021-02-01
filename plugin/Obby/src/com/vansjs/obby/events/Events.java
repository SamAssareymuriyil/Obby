package com.vansjs.obby.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.FileWriter;
import java.io.IOException;

public class Events implements Listener {

    // provide starter data for python
    @EventHandler
    public static void onPlayerJoin (PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.LIGHT_PURPLE + "Welcome to the Server!! :)");
        try {
            FileWriter myWriter = new FileWriter("C:/Users/Sam Abraham/Documents/Grade12/Computer Engineering/Obby/plugin/Obby/src/data.txt"); //TODO: Change to local file path
            myWriter.write(0+"\n"+0+"\n"+0+"\n"+0+"\nT");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
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
            water = 1;
        }else{
            water = 0;
        }

        // set values
        yaw+=4000; health+=3000; water+=2000; hunger+=1000;

        // write to txt
        try {
            FileWriter myWriter = new FileWriter("C:/Users/Sam Abraham/Documents/Grade12/Computer Engineering/Obby/plugin/Obby/src/data.txt"); //TODO: Change to local file path
            myWriter.write(yaw+"\n"+health+"\n"+water+"\n"+hunger+"\nT");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    @EventHandler
    public static void onPlayerLeave (PlayerQuitEvent event){
        // close python loop
        try {
            FileWriter myWriter = new FileWriter("C:/Users/Sam Abraham/Documents/Grade12/Computer Engineering/Obby/plugin/Obby/src/data.txt"); //TODO: Change to local file path
            myWriter.write(0+"\n"+0+"\n"+0+"\n"+0+"\nF");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}