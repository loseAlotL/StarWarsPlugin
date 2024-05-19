package org.randomlima.starwars;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CustomItems {
    public static ItemStack DC17;
    public static ItemStack Firepuncher;
    public static ItemStack ThermalDetonator;
    public static ItemStack Detonator;
    public static ItemStack Grenade;
    public static ItemStack GrenadeLauncher;
    public static ItemStack FlameThrower;
    public static ItemStack LaserTurret;

    public static void createItems(){
        createDC17();
        createFirepuncher773();
        createThermalDetonator();
        createDetonator();
        createGrenadeLauncher();
        createGrenade();
        createLaserTurret();
    }
    private static void createDC17() {
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
    private static void createFirepuncher773() {
        ItemStack item = new ItemStack(Material.IRON_NUGGET,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(101);
        meta.setDisplayName(Colorize.format("&7773 Firepuncher"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: Shoot &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7Shoot a laser...far"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        Firepuncher = item;
    }

    private static void createThermalDetonator() {
        ItemStack item = new ItemStack(Material.IRON_NUGGET,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(103);
        meta.setDisplayName(Colorize.format("&7Thermal Detonator"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: Throw &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7Yeet the thermal detonator,"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        ThermalDetonator = item;
    }
    private static void createDetonator() {
        ItemStack item = new ItemStack(Material.IRON_NUGGET,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(104);
        meta.setDisplayName(Colorize.format("&7Detonator"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: Detonate &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7Explode all your thermal detonators"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        Detonator = item;
    }

    private static void createGrenadeLauncher() {
        ItemStack item = new ItemStack(Material.IRON_NUGGET,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(105);
        meta.setDisplayName(Colorize.format("&7Grenade Launcher"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: Launch &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7Yeet a grenade."));
        meta.setLore(lore);
        item.setItemMeta(meta);
        GrenadeLauncher = item;
    }

    private static void createGrenade() {
        ItemStack item = new ItemStack(Material.IRON_NUGGET,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(106);
        meta.setDisplayName(Colorize.format("&7Grenade"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&7Yeet this with a grenade launcher."));
        meta.setLore(lore);
        item.setItemMeta(meta);
        Grenade = item;
    }

    private static void createLaserTurret() {
        ItemStack item = new ItemStack(Material.IRON_NUGGET,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(107);
        meta.setDisplayName(Colorize.format("&7Laser Turret"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: Place Down &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7Place it down!"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        LaserTurret = item;
    }

}
