package org.randomlima.starwars.Listeners.FlameThrower;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.randomlima.starwars.CustomItems;
import org.randomlima.starwars.StarWars;

public class FlameThrowerRightClick implements Listener {
    private Player player;
    private int durationTicks;
    private double range;
    private double spread;
    private final StarWars main;

    public void Flamethrower(Player player, int durationTicks, double range, double spread) {
        this.player = player;
        this.durationTicks = durationTicks;
        this.range = range;
        this.spread = spread;
    }

    public FlameThrowerRightClick(StarWars main, Player player, int durationTicks, double range, double spread) {
        this.main = main;
        this.player = player;
        this.durationTicks = durationTicks;
        this.range = range;
        this.spread = spread;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getAction().isRightClick() && event.getItem().getItemMeta().equals(CustomItems.Firepuncher.getItemMeta())) {
            Player player =  event.getPlayer();
            shoot();
        }
    }

    public void shoot() {
        new BukkitRunnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (ticks >= durationTicks) {
                    cancel();
                    return;
                }

                Location origin = player.getEyeLocation();
                Vector direction = origin.getDirection();

                for (double i = -spread / 2; i <= spread / 2; i += 0.2) {
                    Vector offset = direction.clone().rotateAroundY(i);
                    Location target = origin.clone().add(offset.multiply(range));

                    spawnFireParticles(target);
                    applyFireDamage(target);
                }

                ticks++;
            }
        }.runTaskTimer(main, 0L, 1L); // Replace 'plugin' with your plugin instance
    }

    private void spawnFireParticles(Location location) {
        location.getWorld().spawnParticle(Particle.FLAME, location, 1);
    }

    private void applyFireDamage(Location location) {
        World world = location.getWorld();
        for (Entity entity : world.getNearbyEntities(location, 1, 1, 1)) {
            if (entity instanceof Player && !entity.equals(player)) {
                ((Player) entity).setFireTicks(20); // Adjust fire duration as needed
                // You can also apply other effects or damage here
            }
        }

        int radius = 1; // Adjust the burn radius as needed
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block block = location.clone().add(x, y, z).getBlock();
                    if (block.getType() == Material.AIR) {
                        block.setType(Material.FIRE);
                    }
                }
            }
        }
    }
}
