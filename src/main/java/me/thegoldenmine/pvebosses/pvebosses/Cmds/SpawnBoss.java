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
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpawnBoss implements CommandExecutor {
    private final PvEbosses plugin;

    public SpawnBoss(PvEbosses main) {
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
        String ERROR = darkGray + "" + strikethrough + "-" + gold + "" + bold + s + red + "" + bold + "ERROR" + darkGray + "" + strikethrough + "-" + red + "" + italic + " ";
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("pveboss.spawn")) {
                if (args.length >= 1) {
                    String boss = args[0];
                    if (plugin.config.getBossInt("Zombie_Health") > 2048) {
                        player.sendMessage(ERROR+"Zombie: The max health is 2048");
                        return false;
                    }
                    if (plugin.config.getBossInt("Skeleton_Health") > 2048) {
                        player.sendMessage(ERROR+"Skeleton: The max health is 2048");
                        return false;
                    }
                    if (plugin.config.getBossInt("Wolf_Health") > 2048) {
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
                    if (boss.equalsIgnoreCase("Zombie")) {
                        Zombie zombieBoss = player.getWorld().spawn(player.getLocation(), Zombie.class);
                        zombieBoss.getEquipment().setHelmet(Items.Helmet);
                        zombieBoss.getEquipment().setChestplate(Items.Chestplate);
                        zombieBoss.getEquipment().setLeggings(Items.Leggins);
                        zombieBoss.getEquipment().setBoots(Items.Boots);
                        zombieBoss.setCustomNameVisible(true);
                        AttributeInstance attributeInstance = zombieBoss.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                        AttributeInstance attributeDAMAGE = zombieBoss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                        assert attributeInstance != null;
                        assert attributeDAMAGE != null;
                        attributeDAMAGE.setBaseValue(plugin.config.getBossInt("Zombie_Damage"));
                        attributeInstance.setBaseValue(plugin.config.getBossInt("Zombie_Health"));
                        zombieBoss.setHealth(plugin.config.getBossInt("Zombie_Health"));
                        zombieBoss.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 2));
                        zombieBoss.getWorld().strikeLightningEffect(zombieBoss.getLocation());
                        NamespacedKey name = new NamespacedKey(plugin, "boss");
                        PersistentDataContainer data = zombieBoss.getPersistentDataContainer();
                        data.set(name, PersistentDataType.STRING, "BOSS");
                        zombieBoss.setCustomName(plugin.config.getBossStr("Zombie_Name")+" "+ChatColor.GRAY+"<"+gold+""+String.valueOf((int) zombieBoss.getHealth())+""+ChatColor.GRAY+"/"+green+""+String.valueOf(plugin.config.getBossInt("Zombie_Health"))+""+ChatColor.GRAY+">");
                        if (plugin.config.getBossBoolean("Zombie_Spawn_Minions")) {
                            for (int i = 0; i < plugin.config.getBossInt("Zombie_Minions"); i++) {
                                Zombie zombieMinion = zombieBoss.getWorld().spawn(zombieBoss.getLocation(), Zombie.class);
                                zombieMinion.getEquipment().setHelmet(Items.Helmet);
                                zombieMinion.getEquipment().setChestplate(Items.Chestplate);
                                zombieMinion.getEquipment().setLeggings(Items.Leggins);
                                zombieMinion.getEquipment().setBoots(Items.Boots);
                                zombieMinion.setCustomNameVisible(true);
                                AttributeInstance attributeInstanceM = zombieMinion.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                                AttributeInstance attributeDAMAGEm = zombieMinion.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                                assert attributeInstanceM != null;
                                assert attributeDAMAGEm != null;
                                attributeDAMAGEm.setBaseValue(plugin.config.getBossInt("Zombie_Minion_Damage"));
                                attributeInstanceM.setBaseValue(plugin.config.getBossInt("Zombie_Minion_Health"));
                                zombieMinion.setHealth(plugin.config.getBossInt("Zombie_Minion_Health"));
                                zombieMinion.setCustomName(plugin.config.getBossStr("Zombie_Name")+""+ChatColor.BLUE+""+ChatColor.ITALIC+"'s Minion "+ChatColor.GRAY+"<"+gold+""+String.valueOf((int) zombieMinion.getHealth())+""+ChatColor.GRAY+"/"+green+""+String.valueOf(plugin.config.getBossInt("Zombie_Minion_Health"))+""+ChatColor.GRAY+">");
                                zombieMinion.setBaby(false);
                                PersistentDataContainer dataMini = zombieMinion.getPersistentDataContainer();
                                dataMini.set(name, PersistentDataType.STRING, "MINI");
                                zombieMinion.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 2));
                            }
                        }
                    } else if (boss.equalsIgnoreCase("Skeleton")) {
                        Skeleton skeleton = player.getWorld().spawn(player.getLocation(), Skeleton.class);
                        skeleton.getEquipment().setHelmet(Items.Helmet);
                        skeleton.getEquipment().setChestplate(Items.Chestplate);
                        skeleton.getEquipment().setLeggings(Items.Leggins);
                        skeleton.getEquipment().setBoots(Items.Boots);
                        AttributeInstance attributeInstance = skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                        AttributeInstance attributeDAMAGE = skeleton.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                        assert attributeInstance != null;
                        assert attributeDAMAGE != null;
                        attributeDAMAGE.setBaseValue(plugin.config.getBossInt("Skeleton_Damage"));
                        attributeInstance.setBaseValue(plugin.config.getBossInt("Skeleton_Health"));
                        skeleton.setHealth(plugin.config.getBossInt("Skeleton_Health"));
                        skeleton.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 2));
                        skeleton.setCustomName(plugin.config.getBossStr("Skeleton_Name")+" "+ChatColor.GRAY+"<"+gold+""+String.valueOf((int) skeleton.getHealth())+""+ChatColor.GRAY+"/"+green+""+String.valueOf(plugin.config.getBossInt("Skeleton_Health"))+""+ChatColor.GRAY+">");
                        skeleton.setCustomNameVisible(true);
                        NamespacedKey name = new NamespacedKey(plugin, "boss");
                        PersistentDataContainer data = skeleton.getPersistentDataContainer();
                        data.set(name, PersistentDataType.STRING, "BOSS");
                        skeleton.getWorld().strikeLightningEffect(skeleton.getLocation());
                    } else if (boss.equalsIgnoreCase("Wolf")) {
                        Wolf wolf = player.getWorld().spawn(player.getLocation(), Wolf.class);
                        wolf.setCustomNameVisible(true);
                        wolf.setOwner(null);
                        wolf.setTamed(false);
                        wolf.setCollarColor(DyeColor.LIGHT_BLUE);
                        wolf.setAngry(true);
                        AttributeInstance attributeInstance = wolf.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                        AttributeInstance attributeDAMAGE = wolf.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                        assert attributeInstance != null;
                        assert attributeDAMAGE != null;
                        attributeDAMAGE.setBaseValue(plugin.config.getBossInt("Wolf_Damage"));
                        attributeInstance.setBaseValue(plugin.config.getBossInt("Wolf_Health"));
                        wolf.setHealth(plugin.config.getBossInt("Wolf_Health"));
                        wolf.getWorld().strikeLightningEffect(wolf.getLocation());
                        NamespacedKey name = new NamespacedKey(plugin, "boss");
                        PersistentDataContainer data = wolf.getPersistentDataContainer();
                        data.set(name, PersistentDataType.STRING, "BOSS");
                        wolf.setCustomName(plugin.config.getBossStr("Wolf_Name")+" "+ChatColor.GRAY+"<"+gold+""+String.valueOf((int) wolf.getHealth())+""+ChatColor.GRAY+"/"+green+""+String.valueOf(plugin.config.getBossInt("Wolf_Health"))+""+ChatColor.GRAY+">");
                        if (plugin.config.getBossBoolean("Wolf_Spawn_Minions")) {
                            for (int i = 0; i < plugin.config.getBossInt("Wolf_Minions"); i++) {
                                Wolf wolfMinion = wolf.getWorld().spawn(wolf.getLocation(), Wolf.class);
                                AttributeInstance attributeInstanceM = wolfMinion.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                                AttributeInstance attributeDAMAGEm = wolfMinion.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                                assert attributeInstanceM != null;
                                assert attributeDAMAGEm != null;
                                attributeDAMAGEm.setBaseValue(plugin.config.getBossInt("Wolf_Minion_Damage"));
                                attributeInstanceM.setBaseValue(plugin.config.getBossInt("Wolf_Minion_Health"));
                                wolfMinion.setHealth(plugin.config.getBossInt("Wolf_Minion_Health"));
                                wolfMinion.setCustomName(plugin.config.getBossStr("Wolf_Name")+""+ChatColor.BLUE+""+ChatColor.ITALIC+"'s Minion "+ChatColor.GRAY+"<"+gold+""+String.valueOf((int) wolfMinion.getHealth())+""+ChatColor.GRAY+"/"+green+""+String.valueOf(plugin.config.getBossInt("Wolf_Minion_Health"))+""+ChatColor.GRAY+">");
                                wolfMinion.setCustomNameVisible(true);
                                wolfMinion.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 2));
                                wolfMinion.setOwner(null);
                                PersistentDataContainer dataMini = wolfMinion.getPersistentDataContainer();
                                dataMini.set(name, PersistentDataType.STRING, "MINI");
                                wolfMinion.setTamed(false);
                                wolfMinion.setCollarColor(DyeColor.LIGHT_BLUE);
                                wolfMinion.setAngry(true);
                            }
                        }
                    } else if (boss.equalsIgnoreCase("Baby-Zombie")) {
                        Zombie babyZombie = player.getWorld().spawn(player.getLocation(), Zombie.class);
                        babyZombie.setBaby(true);
                        babyZombie.getEquipment().setHelmet(Items.Helmet);
                        babyZombie.getEquipment().setChestplate(Items.Chestplate);
                        babyZombie.getEquipment().setLeggings(Items.Leggins);
                        babyZombie.getEquipment().setBoots(Items.Boots);
                        babyZombie.setCustomNameVisible(true);
                        AttributeInstance attributeInstance = babyZombie.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                        AttributeInstance attributeDAMAGE = babyZombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                        assert attributeInstance != null;
                        assert attributeDAMAGE != null;
                        attributeDAMAGE.setBaseValue(plugin.config.getBossInt("BabyZombie_Damage"));
                        attributeInstance.setBaseValue(plugin.config.getBossInt("BabyZombie_Health"));
                        babyZombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 1));
                        babyZombie.setHealth(plugin.config.getBossInt("BabyZombie_Health"));
                        babyZombie.getWorld().strikeLightningEffect(babyZombie.getLocation());
                        babyZombie.setCustomName(plugin.config.getBossStr("BabyZombie_Name")+" "+ChatColor.GRAY+"<"+gold+""+String.valueOf((int) babyZombie.getHealth())+""+ChatColor.GRAY+"/"+green+""+String.valueOf(plugin.config.getBossInt("BabyZombie_Health"))+""+ChatColor.GRAY+">");
                        NamespacedKey name = new NamespacedKey(plugin, "boss");
                        PersistentDataContainer data = babyZombie.getPersistentDataContainer();
                        data.set(name, PersistentDataType.STRING, "BABY");
                    } else if (boss.equalsIgnoreCase("Baby-Wolf")) {
                        Wolf babyWolf = player.getWorld().spawn(player.getLocation(), Wolf.class);
                        babyWolf.setBaby();
                        babyWolf.setCustomNameVisible(true);
                        babyWolf.setOwner(null);
                        babyWolf.setTamed(false);
                        AttributeInstance attributeInstance = babyWolf.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                        AttributeInstance attributeDAMAGE = babyWolf.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                        assert attributeInstance != null;
                        assert attributeDAMAGE != null;
                        attributeDAMAGE.setBaseValue(plugin.config.getBossInt("BabyWolf_Damage"));
                        attributeInstance.setBaseValue(plugin.config.getBossInt("BabyWolf_Health"));
                        babyWolf.setHealth(plugin.config.getBossInt("BabyWolf_Health"));
                        babyWolf.setCollarColor(DyeColor.PURPLE);
                        babyWolf.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 1));
                        babyWolf.setAngry(true);
                        babyWolf.setCustomName(plugin.config.getBossStr("BabyWolf_Name")+" "+ChatColor.GRAY+"<"+gold+""+String.valueOf((int) babyWolf.getHealth())+""+ChatColor.GRAY+"/"+green+""+String.valueOf(plugin.config.getBossInt("BabyWolf_Health"))+""+ChatColor.GRAY+">");
                        babyWolf.getWorld().strikeLightningEffect(babyWolf.getLocation());
                        NamespacedKey name = new NamespacedKey(plugin, "boss");
                        PersistentDataContainer data = babyWolf.getPersistentDataContainer();
                        data.set(name, PersistentDataType.STRING, "BABY");
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
