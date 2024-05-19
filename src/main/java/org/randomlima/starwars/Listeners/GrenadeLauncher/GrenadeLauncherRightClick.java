package org.randomlima.starwars.Listeners.GrenadeLauncher;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.randomlima.starwars.CustomItems;
import org.randomlima.starwars.Listeners.ThermalDetonator.ThermalDetonatorRightClick;
import org.randomlima.starwars.StarWars;

import java.util.Map;

public class GrenadeLauncherRightClick implements Listener {
    private final StarWars main;
    public GrenadeLauncherRightClick(StarWars main) {
        this.main = main;
    }


    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getItem() != null && event.getAction().isRightClick() && event.getItem().getItemMeta().equals(CustomItems.GrenadeLauncher.getItemMeta())) {
            Player player =  event.getPlayer();
            if(player.getInventory().containsAtLeast(CustomItems.Grenade,1)){
                Item item = player.getWorld().dropItem(player.getLocation(), new ItemStack(CustomItems.Grenade));
                Vector launchDirection = player.getLocation().getDirection().normalize().multiply(1.5);
                item.setVelocity(launchDirection);
                player.getInventory().removeItem(CustomItems.Grenade);
                player.playSound(event.getPlayer(), Sound.ENTITY_ENDER_EYE_LAUNCH,1,1);
                Bukkit.getScheduler().runTaskLater(main, () -> {
                    item.getLocation().createExplosion(3);
                }, 15);
            } else{
                player.sendMessage("You do not have any greandes.");
            }
        }
    }
}
