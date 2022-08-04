package me.thegoldenmine.pvebosses.pvebosses.Cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> SubCommands = new ArrayList<>();
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("pveboss.spawn")) {
                    SubCommands.add("Zombie");
                    SubCommands.add("Skeleton");
                    SubCommands.add("Wolf");
                    SubCommands.add("Baby-Zombie");
                    SubCommands.add("Baby-Wolf");
                    SubCommands.add("Creeper");
                    SubCommands.add("Spider");
                    SubCommands.add("Witch");
                    SubCommands.add("Enderman");
                    SubCommands.add("Slime");
                }
            }
            return SubCommands;
        }
        return null;
    }
}
