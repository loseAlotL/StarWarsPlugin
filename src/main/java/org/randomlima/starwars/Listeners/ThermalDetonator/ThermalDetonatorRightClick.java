package org.randomlima.starwars.Listeners.ThermalDetonator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.randomlima.starwars.CustomItems;
import org.randomlima.starwars.StarWars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThermalDetonatorRightClick implements Listener {
    private final StarWars main;
    private static HashMap<Item, Player> detonators = new HashMap<>();
    public ThermalDetonatorRightClick(StarWars main) {
        this.main = main;

    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isRightClick() && event.getItem().getItemMeta().equals(CustomItems.ThermalDetonator.getItemMeta())){
            if (player.getInventory().getItemInOffHand().equals(CustomItems.Detonator)){
                Item item = player.getWorld().dropItem(player.getLocation(), new ItemStack(CustomItems.ThermalDetonator));
                Vector launchDirection = player.getLocation().getDirection().normalize().multiply(1);
                item.setVelocity(launchDirection);
                player.getInventory().removeItem(CustomItems.ThermalDetonator);
                player.playSound(player, Sound.ENTITY_FISHING_BOBBER_THROW,1,1);
                detonators.put(item, player);
            } else{
                player.sendMessage("You must have a detonator in your off hand!");
            }
        }
    }

    public static HashMap<Item, Player> getDetonators(){
        return detonators;
    }
}
