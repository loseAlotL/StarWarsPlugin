package org.randomlima.starwars.Listeners.DC17;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.randomlima.starwars.CooldownManager;
import org.randomlima.starwars.CustomItems;
import org.randomlima.starwars.StarWars;

public class DC17LeftClick implements Listener {
    private CooldownManager cooldownManager;
    private final StarWars main;
    public DC17LeftClick(StarWars main) {
        this.main = main;
        this.cooldownManager = new CooldownManager(main, 5);
        cooldownManager.setCooldownMessage("&7Overheat! Use again in: %seconds% seconds."); //the %seconds% will be replaced with the remaining seconds.
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isLeftClick() && event.getItem().getItemMeta().equals(CustomItems.DC17.getItemMeta())){
            DC17RightClick.reload();
            player.sendMessage("Reloading!");
            player.getWorld().playSound(player, Sound.ENTITY_GHAST_WARN,1,1);
        }
    }
}
