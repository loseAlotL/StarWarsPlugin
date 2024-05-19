package org.randomlima.starwars;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.randomlima.starwars.Commands.*;
import org.randomlima.starwars.Listeners.DC17.DC17LeftClick;
import org.randomlima.starwars.Listeners.DC17.DC17RightClick;
import org.randomlima.starwars.Listeners.Detonator.DetonatorHit;
import org.randomlima.starwars.Listeners.FirepuncherRifle773.FirepuncherLeftClick;
import org.randomlima.starwars.Listeners.FirepuncherRifle773.FirepuncherRightClick;
import org.randomlima.starwars.Listeners.FlameThrower.FlameThrowerRightClick;
import org.randomlima.starwars.Listeners.GrenadeLauncher.GrenadeLauncherRightClick;
import org.randomlima.starwars.Listeners.LaserTurret.LaserTurretRightClick;
import org.randomlima.starwars.Listeners.ThermalDetonator.ThermalDetonatorRightClick;

public final class StarWars extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        CustomItems.createItems();

        this.getCommand("sw.DC17").setExecutor(new DC17());
        this.getCommand("sw.firepuncher").setExecutor(new Firepuncher());
        this.getCommand("sw.thermaldetonator").setExecutor(new ThermalDetonator());
        this.getCommand("sw.detonator").setExecutor(new Detonator());
        this.getCommand("sw.grenade").setExecutor(new Grenade());
        this.getCommand("sw.grenadelauncher").setExecutor(new GrenadeLauncher());
        this.getCommand("sw.flamethrower").setExecutor(new FlameThrower());
        this.getCommand("sw.laserturret").setExecutor(new LaserTurret());

        getServer().getPluginManager().registerEvents(new DC17RightClick(this), this);
        getServer().getPluginManager().registerEvents(new DC17LeftClick(this), this);
        getServer().getPluginManager().registerEvents(new FirepuncherLeftClick(this), this);
        getServer().getPluginManager().registerEvents(new FirepuncherRightClick(this), this);
        getServer().getPluginManager().registerEvents(new ThermalDetonatorRightClick(this), this);
        getServer().getPluginManager().registerEvents(new DetonatorHit(this), this);
        getServer().getPluginManager().registerEvents(new GrenadeLauncherRightClick(this), this);
        getServer().getPluginManager().registerEvents(new LaserTurretRightClick(this), this);

        createCustomRecipes();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void createCustomRecipes(){
        ShapedRecipe thermalDetonatorRecipe = new ShapedRecipe(CustomItems.ThermalDetonator);
        thermalDetonatorRecipe.shape("***","*B*","***");
        thermalDetonatorRecipe.setIngredient('*', Material.IRON_NUGGET);
        thermalDetonatorRecipe.setIngredient('B', Material.TNT);
        getServer().addRecipe(thermalDetonatorRecipe);

        ShapedRecipe laserTurretRecipe = new ShapedRecipe(CustomItems.LaserTurret);
        laserTurretRecipe.shape("***","*B*","*%*");
        laserTurretRecipe.setIngredient('*', Material.IRON_INGOT);
        laserTurretRecipe.setIngredient('B', Material.REDSTONE_BLOCK);
        laserTurretRecipe.setIngredient('%', Material.NETHERITE_BLOCK);
        getServer().addRecipe(laserTurretRecipe);
    }
}
