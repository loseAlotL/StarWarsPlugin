package org.randomlima.starwars.Assets.DC17;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.randomlima.starwars.Util.Colorize;

import java.util.ArrayList;
import java.util.List;

public class ItemDC17 {
    public static ItemStack DC17;
    public static void createDC17() {
        ItemStack item = new ItemStack(Material.IRON_NUGGET,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(100);
        meta.setDisplayName(Colorize.format("&7DC-17"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: Shoot &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7Shoot a laser...yeah"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        DC17 = item;
    }

    public ItemStack get(){
        return DC17;
    }
}
