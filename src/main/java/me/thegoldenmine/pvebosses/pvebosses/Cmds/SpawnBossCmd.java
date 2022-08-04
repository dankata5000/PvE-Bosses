package me.thegoldenmine.pvebosses.pvebosses.Cmds;

import me.thegoldenmine.pvebosses.pvebosses.Items;
import me.thegoldenmine.pvebosses.pvebosses.PvEbosses;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerUnleashEntityEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpawnBossCmd implements CommandExecutor {
    private final PvEbosses plugin;

    public SpawnBossCmd(PvEbosses main) {
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
        String ERROR = darkGray + "" + strikethrough + "-" + gold + "" + bold + s + red + "" + bold + "ERROR" + darkGray + "" + strikethrough + " -" + red + "" + italic + " ";
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("pveboss.spawn")) {
                if (args.length >= 1) {
                    String boss = args[0];
                    if (plugin.config.getBossInt("Creeper_Health") > 2048) {
                        player.sendMessage(ERROR+"Creeper: The max health is 2048");
                        return false;
                    }
                    if (plugin.config.getBossInt("Spider_Health") > 2048 || plugin.config.getBossInt("Spider_Minion_Health") > 2048) {
                        player.sendMessage(ERROR+"Spider: The max health is 2048");
                        return false;
                    }
                    if (plugin.config.getBossInt("Enderman_Health") > 2048 || plugin.config.getBossInt("Enderman_Minion_Health") > 2048) {
                        player.sendMessage(ERROR+"Enderman: The max health is 2048");
                        return false;
                    }
                    if (plugin.config.getBossInt("Witch_Health") > 2048 || plugin.config.getBossInt("Witch_Minion_Health") > 2048) {
                        player.sendMessage(ERROR+"Witch: The max health is 2048");
                        return false;
                    }
                    if (plugin.config.getBossInt("Zombie_Health") > 2048 || plugin.config.getBossInt("Zombie_Minion_Health") > 2048) {
                        player.sendMessage(ERROR+"Zombie: The max health is 2048");
                        return false;
                    }
                    if (plugin.config.getBossInt("Skeleton_Health") > 2048) {
                        player.sendMessage(ERROR+"Skeleton: The max health is 2048");
                        return false;
                    }
                    if (plugin.config.getBossInt("Wolf_Health") > 2048 || plugin.config.getBossInt("Wolf_Minion_Health") > 2048) {
                        player.sendMessage(ERROR+"Wolf: The max health is 2048");
                        return false;
                    }
                    if (plugin.config.getBossInt("BabyZombie_Health") > 2048) {
                        player.sendMessage(ERROR+"Baby Zombie: The max health is 2048");
                        return false;
                    }
                    if (plugin.config.getBossInt("BabyWolf_Health") > 2048) {
                        player.sendMessage(ERROR+"Baby Wolf: The max health is 2048");
                        return false;
                    }
                    if (plugin.config.getBossInt("Slime_Health") > 2048) {
                        player.sendMessage(ERROR+"Slime: The max health is 2048");
                        return false;
                    }
                    if (boss.equalsIgnoreCase("Zombie")) {
                        plugin.spawnBosses.Zombie(player.getLocation());
                    } else if (boss.equalsIgnoreCase("Skeleton")) {
                        plugin.spawnBosses.Skeleton(player.getLocation());
                    } else if (boss.equalsIgnoreCase("Wolf")) {
                        plugin.spawnBosses.Wolf(player.getLocation());
                    } else if (boss.equalsIgnoreCase("Baby-Zombie")) {
                        plugin.spawnBosses.BabyZombie(player.getLocation());
                    } else if (boss.equalsIgnoreCase("Baby-Wolf")) {
                        plugin.spawnBosses.BabyWolf(player.getLocation());
                    } else if (boss.equalsIgnoreCase("Creeper")) {
                        plugin.spawnBosses.Creeper(player.getLocation());
                    } else if (boss.equalsIgnoreCase("Spider")) {
                        plugin.spawnBosses.Spider(player.getLocation());
                    } else if (boss.equalsIgnoreCase("Witch")) {
                        plugin.spawnBosses.Witch(player.getLocation());
                    } else if (boss.equalsIgnoreCase("Enderman")) {
                        plugin.spawnBosses.Enderman(player.getLocation());
                    } else if (boss.equalsIgnoreCase("Slime")) {
                        plugin.spawnBosses.Slime(player.getLocation());
                    } else {
                        player.sendMessage(ERROR+"You have to enter a valid boss.");
                    }
                } else {
                    player.sendMessage(ERROR+"You have to enter a boss.");
                }
            } else {
                player.sendMessage(ERROR+"You need pveboss.spawn permission.");
            }
        } else {
            sender.sendMessage("- PvE Bosses ERROR - Only players can spawn bosses.");
        }
        return true;
    }
}
