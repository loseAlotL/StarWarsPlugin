package org.randomlima.starwars.Util;

import org.bukkit.inventory.ItemStack;

import static org.randomlima.starwars.Assets.DC17.ItemDC17.createDC17;

public class ItemUtil {
    public static void createItems(){
        createDC17();
    }
    public boolean isSameItem(ItemStack i, ItemStack j){
        return i.getItemMeta().equals(j.getItemMeta());
    }
}
