package org.randomlima.starwars.Listeners.LaserTurret;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.randomlima.starwars.CustomItems;
import org.randomlima.starwars.StarWars;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.*;

public class LaserTurretRightClick implements Listener {

    private List<Entity> pigList = new ArrayList<>();

    private List<Entity> pigsOnCooldown = new ArrayList<>();
    private List<Player> pigPlayers = new ArrayList<>();
    private List<Pig> missilePigs = new ArrayList<>();

    private Map<Player, Pig> playerPigMap = new HashMap<>();
    private final StarWars main;
    public LaserTurretRightClick(StarWars main) {
        this.main = main;
    }


    @EventHandler
    public void onLeftClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isLeftClick() && event.getItem().getItemMeta().equals(CustomItems.LaserTurret.getItemMeta())){
            Pig pig =  player.getWorld().spawn(player.getLocation(), Pig.class);
            pigList.add(pig);
            player.getWorld().playSound(player.getLocation(),Sound.BLOCK_LEVER_CLICK,1,1);
            player.getInventory().removeItem(CustomItems.LaserTurret);
            pig.setCustomName("Laser Turret");
            pig.setCustomNameVisible(true);
            pig.setAI(false);
        }
    }

    @EventHandler
    public void onEntityDismount(EntityDismountEvent event) {
        Entity dismountedEntity = event.getDismounted();
        Entity e = event.getEntity();
        if (e instanceof Player) {
            Player player = (Player) e;
            if (pigList.contains(dismountedEntity)) {
                pigPlayers.remove(player);
            }
        }
    }


    @EventHandler
    public void onEntityHit(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        if (event.getDamager() instanceof Player){
            Player damager = (Player) event.getDamager();
            if(pigList.contains(damaged)){
                damaged.remove();
                damager.getInventory().addItem(CustomItems.LaserTurret);
            }
        }

    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getAction().isRightClick() && pigPlayers.contains(player)) {
            if(missilePigs.contains(player.getVehicle())){
                if(!pigsOnCooldown.contains(player.getVehicle())){
                    Item item = player.getWorld().dropItem(player.getLocation(), new ItemStack(CustomItems.Grenade));
                    Vector launchDirection = player.getLocation().getDirection().normalize().multiply(3);
                    item.setVelocity(launchDirection);
                    player.getWorld().playSound(event.getPlayer(), Sound.ENTITY_ENDER_EYE_LAUNCH,1,1);
                    pigsOnCooldown.add(player.getVehicle());
                    Bukkit.getScheduler().runTaskLater(main, () -> {
                        player.getWorld().playSound(player, Sound.ENTITY_GHAST_WARN,1,1);
                        item.getLocation().createExplosion(4);
                    }, 15);
                    Bukkit.getScheduler().runTaskLater(main, () -> {
                        pigsOnCooldown.remove(player.getVehicle());
                    }, 30);
                } else{
                    player.getWorld().playSound(event.getPlayer(), Sound.ENTITY_ENDERMAN_TELEPORT,1,1);
                }

            } else{
                shootLaser(player);
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GHAST_SHOOT,1,1);
            }
        }
        if (event.getAction().isRightClick() && player.getTargetEntity(5) instanceof Pig){
            Pig pig = (Pig) player.getTargetEntity(5);
            if (pigList.contains(pig)){
                if (!player.isSneaking()){
                    pig.setPassenger(player);
                    pigPlayers.add(player);
                } else{
                    ItemStack glassPane = new ItemStack(Material.RED_STAINED_GLASS_PANE);
                    ItemMeta meta = glassPane.getItemMeta();
                    if (meta != null) {
                        meta.setDisplayName("AMMO: MISSLES");
                        glassPane.setItemMeta(meta);
                    }
                    Inventory customInventory = Bukkit.createInventory(player, 18, "Ammunition Selection Menu");
                    player.openInventory(customInventory);
                    customInventory.setItem(0, glassPane);
                    playerPigMap.put(player, pig);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null || event.getCurrentItem() == null) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        ItemStack currentItem = event.getCurrentItem();
        ItemMeta currentMeta = currentItem.getItemMeta();

        if (currentMeta != null) {
            // Check if the clicked item is a grey glass pane named "AMMO: MISSLES"
            if (currentItem.getType() == Material.RED_STAINED_GLASS_PANE &&
                    "AMMO: MISSLES".equals(currentMeta.getDisplayName())) {

                // Change the item to a blue stained glass pane
                ItemStack blueGlassPane = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
                ItemMeta blueMeta = blueGlassPane.getItemMeta();
                if (blueMeta != null) {
                    blueMeta.setDisplayName("AMMO: LASERS");
                    blueGlassPane.setItemMeta(blueMeta);
                }

                event.getInventory().setItem(event.getSlot(), blueGlassPane);
                event.setCancelled(true); // Prevent taking the item
                event.getWhoClicked().getWorld().playSound(event.getWhoClicked(), Sound.BLOCK_LEVER_CLICK,1,1);

                // Add the pig to the missilePigs list if player is associated with a pig
                Pig pig = playerPigMap.get(player);
                if (pig != null) {
                    missilePigs.remove(pig);
                    player.sendMessage("Selected: LASER");
                }

            } else if (currentItem.getType() == Material.BLUE_STAINED_GLASS_PANE && "AMMO: LASERS".equals(currentMeta.getDisplayName())) {
                // Change the item back to a grey stained glass pane
                ItemStack greyGlassPane = new ItemStack(Material.RED_STAINED_GLASS_PANE);
                ItemMeta greyMeta = greyGlassPane.getItemMeta();
                if (greyMeta != null) {
                    greyMeta.setDisplayName("AMMO: MISSLES");
                    greyGlassPane.setItemMeta(greyMeta);
                }

                event.getInventory().setItem(event.getSlot(), greyGlassPane);
                event.setCancelled(true); // Prevent taking the item
                event.getWhoClicked().getWorld().playSound(event.getWhoClicked(), Sound.BLOCK_LEVER_CLICK,1,1);

                // Add the pig to the laserPigs list if player is associated with a pig
                Pig pig = playerPigMap.get(player);
                if (pig != null) {
                    missilePigs.add(pig);
                    player.sendMessage("Selected: MISSILE");
                }
            }
        }
    }


    public void shootLaser(Player player) {
        Location pLoc = player.getEyeLocation();
        pLoc.setY(pLoc.getY()-0.5);
        Location currentLoc = pLoc.clone(); // Store the current location
        Vector direction = player.getEyeLocation().getDirection().normalize(); // Store the constant direction
        List<Item> itemList = new ArrayList<>();

        new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count >= 200) {
                    cancel(); // Stop after 50 iterations
                    return;
                }
                for (Item item : itemList) {
                    item.remove();
                }
                for (double i = 0; i < 2; i += 0.2) {
                    Location particleLoc = currentLoc.clone().add(direction.clone().multiply(i));
                    Item item = player.getWorld().dropItem(particleLoc, new ItemStack(Material.LIGHT_BLUE_CONCRETE));
                    item.setVelocity(new Vector(0, 0, 0));
                    item.setGravity(false);
                    itemList.add(item);
                    //damage stuff:
                    for (Entity entity : particleLoc.getWorld().getNearbyEntities(particleLoc, 0.5, 0.5, 0.5)) {
                        if (entity instanceof LivingEntity) {
                            if(!entity.equals(player)){
                                if(!pigList.contains(entity)){
                                    hitEntities((LivingEntity) entity, 2); // Add entity to hit list
                                    entity.getWorld().playSound(entity, Sound.ENTITY_GENERIC_HURT,1,1);}
                                }

                        }
                    }
                }
                currentLoc.add(direction.clone().multiply(2)); // Update the location
                count++;
            }
        }.runTaskTimer(main, 0L, 1L); // Run every 10 ticks
    }
    public void hitEntities(LivingEntity entity, int damage){
        entity.damage(damage);
    }

}