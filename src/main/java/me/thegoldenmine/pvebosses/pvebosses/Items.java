package me.thegoldenmine.pvebosses.pvebosses;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {
    public static ItemStack Helmet;
    public static ItemStack Chestplate;
    public static ItemStack Leggins;
    public static ItemStack Boots;

    public static ItemStack BadyZombieHelmet;
    public static ItemStack BadyZombieChestplate;
    public static ItemStack BadyZombieLeggings;
    public static ItemStack BadyZombieBoots;

    public static void init() {
        // Zombie/Skeleton set
        setHelmet();
        setChestplate();
        setLeggins();
        setBoots();

        // BabyZombie set
        setBadyZombieHelmet();
        setBadyZombieChestplate();
        setBabyZombieLeggings();
        setBadyZombieBoots();
    }

    // Zombie/Skeleton

    private static void setHelmet() {
        ItemStack item = new ItemStack(Material.IRON_HELMET, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        //meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "|" + ChatColor.GOLD + "" + ChatColor.BOLD + "Zombie's Helmet" + ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "|");
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, false);
        meta.addEnchant(Enchantment.PROTECTION_FALL, 4, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        Helmet = item;
    }

    private static void setChestplate() {
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        //meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "|" + ChatColor.GOLD + "" + ChatColor.BOLD + "Zombie's Chestplate" + ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "|");
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, false);
        meta.addEnchant(Enchantment.PROTECTION_FALL, 4, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        Chestplate = item;
    }

    private static void setLeggins() {
        ItemStack item = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        //meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "|" + ChatColor.GOLD + "" + ChatColor.BOLD + "Zombie's Leggings" + ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "|");
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, false);
        meta.addEnchant(Enchantment.PROTECTION_FALL, 4, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        Leggins = item;
    }

    private static void setBoots() {
        ItemStack item = new ItemStack(Material.IRON_BOOTS, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        //meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "|" + ChatColor.GOLD + "" + ChatColor.BOLD + "Zombie's Boots" + ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "|");
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, false);
        meta.addEnchant(Enchantment.PROTECTION_FALL, 4, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        Boots = item;
    }

    // BadyZombie

    private static void setBadyZombieHelmet() {
        ItemStack item = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        //meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "|" + ChatColor.GOLD + "" + ChatColor.BOLD + "Zombie's Helmet" + ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "|");
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, false);
        meta.addEnchant(Enchantment.PROTECTION_FALL, 4, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        BadyZombieHelmet = item;
    }

    private static void setBadyZombieChestplate() {
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        //meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "|" + ChatColor.GOLD + "" + ChatColor.BOLD + "Zombie's Helmet" + ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "|");
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, false);
        meta.addEnchant(Enchantment.PROTECTION_FALL, 4, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        BadyZombieChestplate = item;
    }

    private static void setBabyZombieLeggings() {
        ItemStack item = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        //meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "|" + ChatColor.GOLD + "" + ChatColor.BOLD + "Zombie's Helmet" + ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "|");
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, false);
        meta.addEnchant(Enchantment.PROTECTION_FALL, 4, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        BadyZombieLeggings = item;
    }

    private static void setBadyZombieBoots() {
        ItemStack item = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        //meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "|" + ChatColor.GOLD + "" + ChatColor.BOLD + "Zombie's Helmet" + ChatColor.DARK_PURPLE + "" + ChatColor.MAGIC + "|");
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, false);
        meta.addEnchant(Enchantment.PROTECTION_FALL, 4, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        BadyZombieBoots = item;
    }
}
