package org.randomlima.starwars.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.randomlima.starwars.CustomItems;

public class Firepuncher implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) sender;
        player.getInventory().addItem(CustomItems.Firepuncher);
        return true;
    }
}
