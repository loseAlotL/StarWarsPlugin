package org.randomlima.starwars.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.randomlima.starwars.Assets.DC17.ItemDC17;

public class CommandDC17 implements CommandExecutor {
    ItemDC17 itemDC17 = new ItemDC17();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) sender;
        player.getInventory().addItem(itemDC17.get());
        return true;
    }
}
