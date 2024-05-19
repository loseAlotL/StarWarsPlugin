package org.randomlima.starwars.Listeners.FirepuncherRifle773;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.randomlima.starwars.CooldownManager;
import org.randomlima.starwars.CustomItems;
import org.randomlima.starwars.StarWars;

import java.util.ArrayList;
import java.util.List;

public class FirepuncherRightClick implements Listener {
    private CooldownManager cooldownManager;
    private final StarWars main;
    public FirepuncherRightClick(StarWars main) {
        this.main = main;
        this.cooldownManager = new CooldownManager(main, 10);
        cooldownManager.setCooldownMessage("&7Reloading! Use again in: %seconds% seconds."); //the %seconds% will be replaced with the remaining seconds.
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isRightClick() && event.getItem().getItemMeta().equals(CustomItems.Firepuncher.getItemMeta())){
            if (cooldownManager.isOnCooldown(player)) {
                event.setCancelled(true);
                cooldownManager.displayTimeLeftInteger(player);
                return;
            }
            shootLaser(player);
            player.playSound(player, Sound.ENTITY_GHAST_SHOOT,1,1);
            cooldownManager.setCooldown(player);
            Bukkit.getScheduler().runTaskLater(main, () -> {
                player.playSound(player, Sound.ENTITY_GHAST_WARN,1,1);
            }, 7);
        }
    }
    public void shootLaser(Player player) {
        Location pLoc = player.getEyeLocation();
        pLoc.setY(pLoc.getY()-0.5);
        Location currentLoc = pLoc.clone(); // Store the current location
        Vector direction = player.getEyeLocation().getDirection().normalize(); // Store the constant direction
        List<Item> itemList = new ArrayList<>();

        new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count >= 200) {
                    cancel(); // Stop after 50 iterations
                    return;
                }
                for (Item item : itemList) {
                    item.remove();
                }
                for (double i = 0; i < 3; i += 0.2) {
                    Location particleLoc = currentLoc.clone().add(direction.clone().multiply(i));
                    Item item = player.getWorld().dropItem(particleLoc, new ItemStack(Material.LIGHT_BLUE_CONCRETE));
                    item.setVelocity(new Vector(0, 0, 0));
                    item.setGravity(false);
                    itemList.add(item);
                    //damage stuff:
                    for (Entity entity : particleLoc.getWorld().getNearbyEntities(particleLoc, 0.5, 0.5, 0.5)) {
                        if (entity instanceof LivingEntity) {
                            if(!entity.equals(player)){hitEntities((LivingEntity) entity, 10); // Add entity to hit list
                                entity.getWorld().playSound(entity, Sound.ENTITY_GENERIC_HURT,1,1);}
                        }
                    }
                }
                currentLoc.add(direction.clone().multiply(2)); // Update the location
                count++;
            }
        }.runTaskTimer(main, 0L, 1L); // Run every 10 ticks
    }
    public void hitEntities(LivingEntity entity, int damage){
        entity.damage(damage);
    }
}
