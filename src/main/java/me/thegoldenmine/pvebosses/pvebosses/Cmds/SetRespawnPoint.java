package me.thegoldenmine.pvebosses.pvebosses.cmds;

import me.thegoldenmine.pvebosses.pvebosses.PvEbosses;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetRespawnPoint implements CommandExecutor {
    private final PvEbosses plugin;

    public SetRespawnPoint(PvEbosses main) {
        plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ChatColor darkGray = ChatColor.DARK_GRAY;
        ChatColor strikethrough = ChatColor.STRIKETHROUGH;
        ChatColor gold = ChatColor.GOLD;
        ChatColor bold = ChatColor.BOLD;
        ChatColor yellow = ChatColor.YELLOW;
        ChatColor green = ChatColor.GREEN;
        ChatColor italic = ChatColor.ITALIC;
        ChatColor aqua = ChatColor.AQUA;
        ChatColor red = ChatColor.RED;
        String s = " PvE Bosses ";
        String WARN = darkGray + "" + strikethrough + "-" + gold + "" + bold + s + yellow + "" + bold + "WARN " + darkGray + "" + strikethrough + "-" + yellow + "" + italic + " ";
        String INFO = darkGray + "" + strikethrough + "-" + gold + "" + bold + s + aqua + "" + bold + "INFO " + darkGray + "" + strikethrough + "-" + aqua + "" + italic + " ";
        String NORMAL = darkGray + "" + strikethrough + "-" + gold + "" + bold + s + darkGray + "" + strikethrough + "-" + green + "" + italic + " ";
        String ERROR = darkGray + "" + strikethrough + "-" + gold + "" + bold + s + red + "" + bold + "ERROR " + darkGray + "" + strikethrough + "-" + red + "" + italic + " ";
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("pveboss.setrespawn")) {
                Location loc = player.getLocation();
                String locStr = plugin.LocationToString(loc);
                plugin.respawnLocations.add(loc);
                int amount = plugin.config.getBossInt("number_of_respawn_points") + 1;
                plugin.config.setBossStr(String.valueOf(amount) + "_respawn_point", locStr);
                plugin.config.setBossInt("number_of_respawn_points", amount);
                player.sendMessage(NORMAL + "Your location is set as boss respawn location.");
            } else {
                player.sendMessage(ERROR+" You don't have "+gold+""+italic+"pveboss.setrespawn"+red+""+italic+" permission.");
            }
        } else {
            sender.sendMessage("- PvE Bosses - You have to be a player to set a respawn point.");
        }
        return true;
    }
}
