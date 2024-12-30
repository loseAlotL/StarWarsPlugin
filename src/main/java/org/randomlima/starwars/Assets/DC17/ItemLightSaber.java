package org.randomlima.starwars.Assets.DC17;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.randomlima.starwars.Util.Colorize;

import java.util.ArrayList;
import java.util.List;

public class ItemLightSaber {
    public static ItemStack LightSaber;
    public static void createLightSaber() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(100);
        meta.setDisplayName(Colorize.format("&7Light Saber"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Block laser and deflect them!"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        LightSaber = item;
    }

    public ItemStack get(){
        return LightSaber;
    }
}
