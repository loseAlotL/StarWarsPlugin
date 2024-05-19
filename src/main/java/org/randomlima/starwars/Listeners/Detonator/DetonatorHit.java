package org.randomlima.starwars.Listeners.Detonator;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.randomlima.starwars.CustomItems;
import org.randomlima.starwars.Listeners.ThermalDetonator.ThermalDetonatorRightClick;
import org.randomlima.starwars.StarWars;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetonatorHit implements Listener {
    private final StarWars main;
    public DetonatorHit(StarWars main) {
        this.main = main;
    }


    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getItem() != null && event.getItem().getItemMeta().equals(CustomItems.Detonator.getItemMeta())) {
            event.getPlayer().playSound(event.getPlayer(), Sound.UI_BUTTON_CLICK,1,1);
            detonateThermalDetonators(event.getPlayer());
        }
    }

    private void detonateThermalDetonators(Player player) {
        for (Map.Entry<Item, Player> entry : ThermalDetonatorRightClick.getDetonators().entrySet()) {
            Item i = entry.getKey();
            Player p = entry.getValue();
            if (p.equals(player)){
                i.getLocation().createExplosion(3);
                ThermalDetonatorRightClick.getDetonators().remove(i);
            }
        }
    }
}
