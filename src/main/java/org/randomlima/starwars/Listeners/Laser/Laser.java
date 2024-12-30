package org.randomlima.starwars.Listeners.Laser;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.randomlima.starwars.StarWars;

import java.util.ArrayList;

public class Laser {
    static ArrayList<Villager> lasers = new ArrayList<>();
    private final StarWars plugin;
    public Laser(StarWars plugin){
        this.plugin = plugin;
    }
    public void shoot(Player player){
        Location location = player.getLocation();
        Vector direction = player.getEyeLocation().getDirection();

        Villager villager = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
        villager.setSilent(true);
        villager.setInvulnerable(true);
        villager.setAI(false);
        villager.setInvisible(true);
        villager.getEquipment().setHelmet(new ItemStack(Material.CARVED_PUMPKIN));

        lasers.add(villager);
        System.out.println("e: "+lasers);

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            location.add(direction.clone().multiply(0.5));
            villager.teleport(location);
        }, 0L, 1L);

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            villager.remove();
        }, 200L);
    }
}
