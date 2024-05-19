package org.randomlima.starwars;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class CooldownManager extends BukkitRunnable {
    private HashMap<UUID, Double> cooldownList = new HashMap<>();
    private double coolDownSeconds;
    private String secondsRemainingIdentifier = "%seconds%";
    private String cooldownMessage;

    public CooldownManager(StarWars  plugin, double seconds){ //we need to pass through the plugin to run the Bukkit task.
        this.coolDownSeconds = seconds; //cooldown in seconds.
        runTaskTimer(plugin, 0L, 5L); //timer runs 4 times a second.
    }
    public void setCooldownMessage(String message){
        this.cooldownMessage = message;
    }

    public void setCooldown(Player player){
        cooldownList.put(player.getUniqueId(), coolDownSeconds);
    }
    public void changeCooldownTimer(double seconds){ //changes the current cooldown timer if desired.
        this.coolDownSeconds = seconds;
        for(UUID uuid : cooldownList.keySet()){
            if(cooldownList.get(uuid) > seconds) cooldownList.put(uuid, seconds);
        }
    }
    public boolean isOnCooldown(Player player){
        if(!cooldownList.containsKey(player.getUniqueId()))return false; // if the player is not in the list, they cannot be on cooldown.
        return cooldownList.get(player.getUniqueId()) > 0;
    }
    public void removePlayer(Player player){
        cooldownList.remove(player.getUniqueId());
    }
    public int getTimeLeftInteger(Player player){
        return cooldownList.get(player.getUniqueId()).intValue(); // rounds the time to the nearest second.
    }
    public double getTimeLeftExact(Player player){
        return cooldownList.get(player.getUniqueId()); //returns the exact time left to the 0.25 second.
    }
    public void displayTimeLeftInteger(Player player) {
        player.sendMessage(Colorize.format(cooldownMessage.replace(secondsRemainingIdentifier, (int)getTimeLeftExact(player) + ""))); //displays second left.
        player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT,1, 1);
    }
    public void displayTimeLeftExact(Player player) {
        player.sendMessage(Colorize.format(cooldownMessage.replace(secondsRemainingIdentifier, getTimeLeftExact(player) + ""))); //displays exact time left to the nearest 0.25 second.
        player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT,1, 1);
    }
    public void stopTask(){
        cancel(); //stops the cooldown task from running.
    }
    @Override
    public void run() {
        for(UUID uuid : cooldownList.keySet()){
            //only 0.25 seconds is subtracted because this class runs 4 times a second.
            if(cooldownList.get(uuid) > 0) cooldownList.put(uuid, cooldownList.get(uuid) - 0.25);
        }
    }
}
