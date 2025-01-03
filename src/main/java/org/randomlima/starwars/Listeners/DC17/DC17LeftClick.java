package org.randomlima.starwars.Listeners.DC17;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.checkerframework.checker.units.qual.A;
import org.randomlima.starwars.Assets.DC17.ItemDC17;
import org.randomlima.starwars.StarWars;
import org.randomlima.starwars.Util.ItemUtil;

import java.util.ArrayList;

public class DC17LeftClick implements Listener {
    ItemUtil itemUtil = new ItemUtil();
    ItemDC17 itemDC17 = new ItemDC17();
    StarWars plugin;
    static ArrayList<Villager> lasers = new ArrayList<>();
    public DC17LeftClick(StarWars plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        if(!event.getAction().isRightClick())return;
        if(!itemUtil.isSameItem(event.getItem(), itemDC17.get()));
        Player player = event.getPlayer();
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
    public static ArrayList<Villager> getLasers(){
        return lasers;
    }
}
