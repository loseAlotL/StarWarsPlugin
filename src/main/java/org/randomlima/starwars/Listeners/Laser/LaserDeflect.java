package org.randomlima.starwars.Listeners.Laser;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;
import org.randomlima.starwars.Listeners.DC17.DC17LeftClick;
import org.randomlima.starwars.StarWars;

import java.util.ArrayList;

public class LaserDeflect implements Listener {
    private final StarWars plugin;
    Laser laser = new Laser(plugin);
    public LaserDeflect(StarWars plugin){
        this.plugin = plugin;
        System.out.println("my plugin ahhhhhhhhh- "+this.plugin);
    }
    DC17LeftClick dc17LeftClick = new DC17LeftClick(plugin);
    ArrayList<Villager> lasers = DC17LeftClick.getLasers();

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event){
        System.out.println(lasers);
        if(!lasers.contains(event.getEntity()))return;
        if(!(event.getDamager() instanceof Player))return;

        event.setCancelled(true);

        Player player = (Player) event.getDamager();
        Location location = player.getLocation();
        Vector direction = player.getEyeLocation().getDirection();

        Villager laser = (Villager) event.getEntity();

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            location.add(direction.clone().multiply(0.5));
            laser.teleport(location);
        }, 0L, 1L);

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            laser.remove();
        }, 200L);
    }
}
