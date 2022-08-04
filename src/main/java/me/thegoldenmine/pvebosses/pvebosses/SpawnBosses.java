package me.thegoldenmine.pvebosses.pvebosses;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpawnBosses {
    private final PvEbosses plugin;

    private ChatColor gold;
    private ChatColor green;

    public SpawnBosses(PvEbosses main) {
        plugin = main;
        gold = ChatColor.GOLD;
        green = ChatColor.GREEN;
    }

    public void Zombie(Location location) {
        if (location.getWorld() != null) {
            Zombie zombieBoss = location.getWorld().spawn(location, Zombie.class);
            zombieBoss.getEquipment().setHelmet(Items.Helmet);
            zombieBoss.getEquipment().setChestplate(Items.Chestplate);
            zombieBoss.getEquipment().setLeggings(Items.Leggins);
            zombieBoss.setCanPickupItems(false);
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
            zombieBoss.setCustomName(plugin.config.getBossStr("Zombie_Name") + " " + ChatColor.GRAY + "<" + ChatColor.GOLD+ "" + String.valueOf((int) zombieBoss.getHealth()) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Zombie_Health")) + "" + ChatColor.GRAY + ">");
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
                    zombieMinion.setCanPickupItems(false);
                    attributeDAMAGEm.setBaseValue(plugin.config.getBossInt("Zombie_Minion_Damage"));
                    attributeInstanceM.setBaseValue(plugin.config.getBossInt("Zombie_Minion_Health"));
                    zombieMinion.setHealth(plugin.config.getBossInt("Zombie_Minion_Health"));
                    zombieMinion.setCustomName(plugin.config.getBossStr("Zombie_Name") + "" + ChatColor.BLUE + "" + ChatColor.ITALIC + "'s Minion " + ChatColor.GRAY + "<" + ChatColor.GOLD + "" + String.valueOf((int) zombieMinion.getHealth()) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Zombie_Minion_Health")) + "" + ChatColor.GRAY + ">");
                    zombieMinion.setBaby(false);
                    PersistentDataContainer dataMini = zombieMinion.getPersistentDataContainer();
                    dataMini.set(name, PersistentDataType.STRING, "MINI");
                    zombieMinion.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 2));
                }
            }
        }
    } // Ready

    public void BabyZombie(Location location) {
        if (location.getWorld() != null) {
            Zombie babyZombie = location.getWorld().spawn(location, Zombie.class);
            babyZombie.setBaby(true);
            babyZombie.getEquipment().setHelmet(Items.Helmet);
            babyZombie.getEquipment().setChestplate(Items.Chestplate);
            babyZombie.getEquipment().setLeggings(Items.Leggins);
            babyZombie.getEquipment().setBoots(Items.Boots);
            babyZombie.setCustomNameVisible(true);
            babyZombie.setCanPickupItems(false);
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
        }
    } // Ready

    public void BabyWolf(Location location) {
        if (location.getWorld() != null) {
            Wolf babyWolf = location.getWorld().spawn(location, Wolf.class);
            babyWolf.setBaby();
            babyWolf.setCustomNameVisible(true);
            babyWolf.setOwner(null);
            babyWolf.setTamed(false);
            AttributeInstance attributeInstance = babyWolf.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            AttributeInstance attributeDAMAGE = babyWolf.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
            assert attributeInstance != null;
            assert attributeDAMAGE != null;
            babyWolf.setCanPickupItems(false);
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
    } // Ready

    public void Skeleton(Location location) {
        if (location.getWorld() != null) {
            Skeleton skeleton = location.getWorld().spawn(location, Skeleton.class);
            skeleton.getEquipment().setHelmet(Items.Helmet);
            skeleton.getEquipment().setChestplate(Items.Chestplate);
            skeleton.getEquipment().setLeggings(Items.Leggins);
            skeleton.getEquipment().setBoots(Items.Boots);
            AttributeInstance attributeInstance = skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            AttributeInstance attributeDAMAGE = skeleton.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
            assert attributeInstance != null;
            assert attributeDAMAGE != null;
            skeleton.setCanPickupItems(false);
            attributeDAMAGE.setBaseValue(plugin.config.getBossInt("Skeleton_Damage"));
            attributeInstance.setBaseValue(plugin.config.getBossInt("Skeleton_Health"));
            skeleton.setHealth(plugin.config.getBossInt("Skeleton_Health"));
            skeleton.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 2));
            skeleton.setCustomName(plugin.config.getBossStr("Skeleton_Name")+" "+ChatColor.GRAY+"<"+ChatColor.GOLD+""+String.valueOf((int) skeleton.getHealth())+""+ChatColor.GRAY+"/"+ChatColor.GREEN+""+String.valueOf(plugin.config.getBossInt("Skeleton_Health"))+""+ChatColor.GRAY+">");
            skeleton.setCustomNameVisible(true);
            NamespacedKey name = new NamespacedKey(plugin, "boss");
            PersistentDataContainer data = skeleton.getPersistentDataContainer();
            data.set(name, PersistentDataType.STRING, "BOSS");
            skeleton.getWorld().strikeLightningEffect(skeleton.getLocation());
        }
    }  // Ready

    public void Wolf(Location location) {
        if (location.getWorld() != null) {
            Wolf wolf = location.getWorld().spawn(location, Wolf.class);
            wolf.setCustomNameVisible(true);
            wolf.setOwner(null);
            wolf.setTamed(false);
            wolf.setCollarColor(DyeColor.LIGHT_BLUE);
            wolf.setAngry(true);
            wolf.setCanPickupItems(false);
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
                    wolfMinion.setCanPickupItems(false);
                    PersistentDataContainer dataMini = wolfMinion.getPersistentDataContainer();
                    dataMini.set(name, PersistentDataType.STRING, "MINI");
                    wolfMinion.setTamed(false);
                    wolfMinion.setCollarColor(DyeColor.LIGHT_BLUE);
                    wolfMinion.setAngry(true);
                }
            }
        }
    }  // Ready

    public void Creeper(Location location) {
        if (location.getWorld() != null) {
            Creeper creeper = location.getWorld().spawn(location, Creeper.class);
            NamespacedKey name = new NamespacedKey(plugin, "boss");
            PersistentDataContainer data = creeper.getPersistentDataContainer();
            data.set(name, PersistentDataType.STRING, "BOSS");
            AttributeInstance attributeInstance = creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            assert attributeInstance != null;
            attributeInstance.setBaseValue(plugin.config.getBossInt("Creeper_Health"));
            creeper.setHealth(plugin.config.getBossInt("Creeper_Health"));
            creeper.setCustomName(plugin.config.getBossStr("Creeper_Name") + " " + ChatColor.GRAY + "<" + ChatColor.GOLD+ "" + String.valueOf((int) creeper.getHealth()) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Creeper_Health")) + "" + ChatColor.GRAY + ">");
            creeper.setCustomNameVisible(true);
            creeper.setExplosionRadius(0);
            creeper.setCanPickupItems(false);
        }
    } // Ready

    public void Spider(Location location) {
        if (location.getWorld() != null) {
            Spider spider = location.getWorld().spawn(location, Spider.class);
            spider.setCustomNameVisible(true);
            AttributeInstance attributeInstance = spider.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            AttributeInstance attributeDAMAGE = spider.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
            assert attributeInstance != null;
            assert attributeDAMAGE != null;
            attributeDAMAGE.setBaseValue(plugin.config.getBossInt("Spider_Damage"));
            attributeInstance.setBaseValue(plugin.config.getBossInt("Spider_Health"));
            spider.setHealth(plugin.config.getBossInt("Spider_Health"));
            spider.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 2));
            spider.getWorld().strikeLightningEffect(spider.getLocation());
            spider.setCanPickupItems(false);
            NamespacedKey name = new NamespacedKey(plugin, "boss");
            PersistentDataContainer data = spider.getPersistentDataContainer();
            data.set(name, PersistentDataType.STRING, "BOSS");
            spider.setCustomName(plugin.config.getBossStr("Spider_Name") + " " + ChatColor.GRAY + "<" + ChatColor.GOLD+ "" + String.valueOf((int) spider.getHealth()) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Spider_Health")) + "" + ChatColor.GRAY + ">");
            if (plugin.config.getBossBoolean("Spider_Spawn_Minions")) {
                for (int i = 0; i < plugin.config.getBossInt("Spider_Minions"); i++) {
                    Spider spiderMinion = spider.getWorld().spawn(spider.getLocation(), Spider.class);
                    spiderMinion.setCustomNameVisible(true);
                    AttributeInstance attributeInstanceM = spiderMinion.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                    AttributeInstance attributeDAMAGEm = spiderMinion.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                    assert attributeInstanceM != null;
                    assert attributeDAMAGEm != null;
                    spiderMinion.setCanPickupItems(false);
                    attributeDAMAGEm.setBaseValue(plugin.config.getBossInt("Spider_Minion_Damage"));
                    attributeInstanceM.setBaseValue(plugin.config.getBossInt("Spider_Minion_Health"));
                    spiderMinion.setHealth(plugin.config.getBossInt("Spider_Minion_Health"));
                    spiderMinion.setCustomName(plugin.config.getBossStr("Spider_Name") + "" + ChatColor.BLUE + "" + ChatColor.ITALIC + "'s Minion " + ChatColor.GRAY + "<" + ChatColor.GOLD + "" + String.valueOf((int) spiderMinion.getHealth()) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Spider_Minion_Health")) + "" + ChatColor.GRAY + ">");
                    PersistentDataContainer dataMini = spiderMinion.getPersistentDataContainer();
                    dataMini.set(name, PersistentDataType.STRING, "MINI");
                    spiderMinion.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 2));
                }
            }
        }
    } // Ready

    public void Witch(Location location) {
        if (location.getWorld() != null) {
            Witch witch = location.getWorld().spawn(location, Witch.class);
            witch.setCustomNameVisible(true);
            AttributeInstance attributeInstance = witch.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            assert attributeInstance != null;
            attributeInstance.setBaseValue(plugin.config.getBossInt("Witch_Health"));
            witch.setHealth(plugin.config.getBossInt("Witch_Health"));
            //witch.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 2));
            witch.getWorld().strikeLightningEffect(witch.getLocation());
            NamespacedKey name = new NamespacedKey(plugin, "boss");
            witch.setCanPickupItems(false);
            PersistentDataContainer data = witch.getPersistentDataContainer();
            data.set(name, PersistentDataType.STRING, "BOSS");
            witch.setCustomName(plugin.config.getBossStr("Witch_Name") + " " + ChatColor.GRAY + "<" + ChatColor.GOLD+ "" + String.valueOf((int) witch.getHealth()) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Witch_Health")) + "" + ChatColor.GRAY + ">");
            if (plugin.config.getBossBoolean("Witch_Spawn_Minions")) {
                for (int i = 0; i < plugin.config.getBossInt("Witch_Minions"); i++) {
                    Witch witchMinion = witch.getWorld().spawn(witch.getLocation(), Witch.class);
                    witchMinion.setCustomNameVisible(true);
                    AttributeInstance attributeInstanceM = witchMinion.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                    AttributeInstance attributeDAMAGEm = witchMinion.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                    assert attributeInstanceM != null;
                    assert attributeDAMAGEm != null;
                    witchMinion.setCanPickupItems(false);
                    attributeDAMAGEm.setBaseValue(plugin.config.getBossInt("Witch_Minion_Damage"));
                    attributeInstanceM.setBaseValue(plugin.config.getBossInt("Witch_Minion_Health"));
                    witchMinion.setHealth(plugin.config.getBossInt("Witch_Minion_Health"));
                    witchMinion.setCustomName(plugin.config.getBossStr("Witch_Name") + "" + ChatColor.BLUE + "" + ChatColor.ITALIC + "'s Minion " + ChatColor.GRAY + "<" + ChatColor.GOLD + "" + String.valueOf((int) witchMinion.getHealth()) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Witch_Minion_Health")) + "" + ChatColor.GRAY + ">");
                    PersistentDataContainer dataMini = witchMinion.getPersistentDataContainer();
                    dataMini.set(name, PersistentDataType.STRING, "MINI");
                    //witchMinion.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 2));
                }
            }
        }
    }  // Ready

    public void Enderman(Location location) {
        if (location.getWorld() != null) {
            Enderman enderman = location.getWorld().spawn(location, Enderman.class);
            enderman.setCustomNameVisible(true);
            AttributeInstance attributeInstance = enderman.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            assert attributeInstance != null;
            attributeInstance.setBaseValue(plugin.config.getBossInt("Enderman_Health"));
            enderman.setHealth(plugin.config.getBossInt("Enderman_Health"));
            enderman.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 2));
            enderman.getWorld().strikeLightningEffect(enderman.getLocation());
            NamespacedKey name = new NamespacedKey(plugin, "boss");
            enderman.setCanPickupItems(false);
            PersistentDataContainer data = enderman.getPersistentDataContainer();
            data.set(name, PersistentDataType.STRING, "BOSS");
            enderman.setCustomName(plugin.config.getBossStr("Enderman_Name") + " " + ChatColor.GRAY + "<" + ChatColor.GOLD+ "" + String.valueOf((int) enderman.getHealth()) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Enderman_Health")) + "" + ChatColor.GRAY + ">");
            if (plugin.config.getBossBoolean("Enderman_Spawn_Minions")) {
                for (int i = 0; i < plugin.config.getBossInt("Enderman_Minions"); i++) {
                    Enderman endermanMinion = enderman.getWorld().spawn(enderman.getLocation(), Enderman.class);
                    endermanMinion.setCustomNameVisible(true);
                    AttributeInstance attributeInstanceM = endermanMinion.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                    AttributeInstance attributeDAMAGEm = endermanMinion.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                    assert attributeInstanceM != null;
                    assert attributeDAMAGEm != null;
                    endermanMinion.setCanPickupItems(false);
                    attributeDAMAGEm.setBaseValue(plugin.config.getBossInt("Enderman_Minion_Damage"));
                    attributeInstanceM.setBaseValue(plugin.config.getBossInt("Enderman_Minion_Health"));
                    endermanMinion.setHealth(plugin.config.getBossInt("Enderman_Minion_Health"));
                    endermanMinion.setCustomName(plugin.config.getBossStr("Enderman_Name") + "" + ChatColor.BLUE + "" + ChatColor.ITALIC + "'s Minion " + ChatColor.GRAY + "<" + ChatColor.GOLD + "" + String.valueOf((int) endermanMinion.getHealth()) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Enderman_Minion_Health")) + "" + ChatColor.GRAY + ">");
                    PersistentDataContainer dataMini = endermanMinion.getPersistentDataContainer();
                    dataMini.set(name, PersistentDataType.STRING, "MINI");
                    endermanMinion.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 2));
                }
            }
        }
    } // Ready

    public void Slime(Location location) {
        if (location.getWorld() != null) {
            Slime slime = location.getWorld().spawn(location, Slime.class);
            NamespacedKey name = new NamespacedKey(plugin, "boss");
            PersistentDataContainer data = slime.getPersistentDataContainer();
            data.set(name, PersistentDataType.STRING, "BOSS");
            AttributeInstance attributeInstance = slime.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            AttributeInstance attributeDAMAGE = slime.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
            assert attributeDAMAGE != null;
            assert attributeInstance != null;
            attributeDAMAGE.setBaseValue(plugin.config.getBossInt("Slime_Damage"));
            attributeInstance.setBaseValue(plugin.config.getBossInt("Slime_Health"));
            slime.setHealth(plugin.config.getBossInt("Slime_Health"));
            slime.setCustomName(plugin.config.getBossStr("Slime_Name") + " " + ChatColor.GRAY + "<" + ChatColor.GOLD+ "" + String.valueOf((int) slime.getHealth()) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Slime_Health")) + "" + ChatColor.GRAY + ">");
            slime.setCustomNameVisible(true);
            slime.setSilent(true);
            slime.setSize(5);
            slime.setCanPickupItems(false);
            slime.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 2));
        }
    } // Ready
}
