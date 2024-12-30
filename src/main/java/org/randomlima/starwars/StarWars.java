package org.randomlima.starwars;

import org.bukkit.plugin.java.JavaPlugin;
import org.randomlima.starwars.Commands.*;
import org.randomlima.starwars.Listeners.DC17.DC17LeftClick;
import org.randomlima.starwars.Listeners.DC17.DC17RightClick;
import org.randomlima.starwars.Listeners.Laser.Laser;
import org.randomlima.starwars.Listeners.Laser.LaserDeflect;
import org.randomlima.starwars.Util.ItemUtil;

public final class StarWars extends JavaPlugin {

    @Override
    public void onEnable() {
        ItemUtil.createItems();

        this.getCommand("sw.DC17").setExecutor(new CommandDC17());
        this.getCommand("sw.LightSaber").setExecutor(new CommandLightSaber());


        LaserDeflect laserDeflect = new LaserDeflect(this);

        getServer().getPluginManager().registerEvents(new LaserDeflect(this), this);
        getServer().getPluginManager().registerEvents(new DC17RightClick(), this);
        getServer().getPluginManager().registerEvents(new DC17LeftClick(this), this);
        Laser laser = new Laser(this);


        //createCustomRecipes();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

//    public void createCustomRecipes(){
//        ShapedRecipe thermalDetonatorRecipe = new ShapedRecipe(CustomItems.ThermalDetonator);
//        thermalDetonatorRecipe.shape("***","*B*","***");
//        thermalDetonatorRecipe.setIngredient('*', Material.IRON_NUGGET);
//        thermalDetonatorRecipe.setIngredient('B', Material.TNT);
//        getServer().addRecipe(thermalDetonatorRecipe);
//
//        ShapedRecipe laserTurretRecipe = new ShapedRecipe(CustomItems.LaserTurret);
//        laserTurretRecipe.shape("***","*B*","*%*");
//        laserTurretRecipe.setIngredient('*', Material.IRON_INGOT);
//        laserTurretRecipe.setIngredient('B', Material.REDSTONE_BLOCK);
//        laserTurretRecipe.setIngredient('%', Material.NETHERITE_BLOCK);
//        getServer().addRecipe(laserTurretRecipe);
//    }
}
