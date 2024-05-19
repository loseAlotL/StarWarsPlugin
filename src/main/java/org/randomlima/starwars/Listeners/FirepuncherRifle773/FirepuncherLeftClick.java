package org.randomlima.starwars.Listeners.FirepuncherRifle773;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.randomlima.starwars.CustomItems;
import org.randomlima.starwars.StarWars;



public class FirepuncherLeftClick implements Listener {
    private boolean zoom;
    private final StarWars main;
    public FirepuncherLeftClick(StarWars main) {
        this.main = main;
    }

    @EventHandler
    public void onLeftClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isLeftClick() && event.getItem().getItemMeta().equals(CustomItems.Firepuncher.getItemMeta())){
            if (zoom){
                player.removePotionEffect(PotionEffectType.SLOW);
                player.playSound(player, Sound.ITEM_SPYGLASS_STOP_USING,1,1);
                zoom=false;
            } else{
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 90));
                player.playSound(player, Sound.ITEM_SPYGLASS_USE,1,1);
                zoom=true;
            }
        }
    }

}
