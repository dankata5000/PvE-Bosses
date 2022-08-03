package me.thegoldenmine.pvebosses.pvebosses.Listeners;

import me.thegoldenmine.pvebosses.pvebosses.PvEbosses;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class BossListener implements Listener {
    private final PvEbosses plugin;

    public BossListener(PvEbosses main) {
        plugin = main;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) entity;
            if (!living.isDead()) {
                double healthNewDo = living.getHealth() - event.getDamage();
                int healthNew = (int) healthNewDo;
                if (healthNew <= 0) {
                    living.setHealth(0);
                    return;
                }
                NamespacedKey name = new NamespacedKey(plugin, "boss");
                PersistentDataContainer data = living.getPersistentDataContainer();
                if (data.has(name, PersistentDataType.STRING)) {
                    if (living instanceof Zombie) {
                        if (data.get(name, PersistentDataType.STRING).equals("MINI")) {
                            living.setCustomName(plugin.config.getBossStr("Zombie_Name") + "" + ChatColor.BLUE + "" + ChatColor.ITALIC + "'s Minion " + ChatColor.GRAY + "<" + ChatColor.GOLD + "" + String.valueOf(healthNew) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Zombie_Minion_Health")) + "" + ChatColor.GRAY + ">");
                        }
                        if (data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                            living.setCustomName(plugin.config.getBossStr("Zombie_Name") + " " + ChatColor.GRAY + "<" + ChatColor.GOLD + "" + String.valueOf(healthNew) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Zombie_Health")) + "" + ChatColor.GRAY + ">");
                        }
                        if (data.get(name, PersistentDataType.STRING).equals("BABY")) {
                            living.setCustomName(plugin.config.getBossStr("BabyZombie_Name")+" "+ChatColor.GRAY+"<"+ChatColor.GOLD+""+String.valueOf(healthNew)+""+ChatColor.GRAY+"/"+ChatColor.GREEN+""+String.valueOf(plugin.config.getBossInt("BabyZombie_Health"))+""+ChatColor.GRAY+">");
                        }
                    }
                    if (living instanceof Wolf) {
                        if (data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                            living.setCustomName(plugin.config.getBossStr("Wolf_Name")+" "+ChatColor.GRAY+"<"+ChatColor.GOLD+""+String.valueOf(healthNew)+""+ChatColor.GRAY+"/"+ChatColor.GREEN+""+String.valueOf(plugin.config.getBossInt("Wolf_Health"))+""+ChatColor.GRAY+">");
                        }
                        if (data.get(name, PersistentDataType.STRING).equals("MINI")) {
                            living.setCustomName(plugin.config.getBossStr("Wolf_Name")+""+ChatColor.BLUE+""+ChatColor.ITALIC+"'s Minion "+ChatColor.GRAY+"<"+ChatColor.GOLD+""+String.valueOf(healthNew)+""+ChatColor.GRAY+"/"+ChatColor.GREEN+""+String.valueOf(plugin.config.getBossInt("Wolf_Minion_Health"))+""+ChatColor.GRAY+">");
                        }
                        if (data.get(name, PersistentDataType.STRING).equals("BABY")) {
                            living.setCustomName(plugin.config.getBossStr("BabyWolf_Name")+" "+ChatColor.GRAY+"<"+ChatColor.GOLD+""+String.valueOf((healthNew)+""+ChatColor.GRAY+"/"+ChatColor.GREEN+""+String.valueOf(plugin.config.getBossInt("BabyWolf_Health"))+""+ChatColor.GRAY+">"));
                        }
                    }
                    if (living instanceof Skeleton && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                        living.setCustomName(plugin.config.getBossStr("Skeleton_Name")+" "+ChatColor.GRAY+"<"+ChatColor.GOLD+""+String.valueOf(healthNew)+""+ChatColor.GRAY+"/"+ChatColor.GREEN+""+String.valueOf(plugin.config.getBossInt("Skeleton_Health"))+""+ChatColor.GRAY+">");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.getCustomName() != null && entity.getCustomName().equals(plugin.config.getBossStr("Zombie_Name")) && entity instanceof Zombie) {
            if (plugin.config.getBossBoolean("Zombie_Drop_XP")) {
                event.setDroppedExp(plugin.config.getBossInt("Zombie_XP"));
            }
            if (!plugin.config.getBossBoolean("Zombie_Drop_Items")) {
                event.getDrops().clear();
            }
        }
        if (entity.getCustomName() != null && entity.getCustomName().equals(plugin.config.getBossStr("Skeleton_Name")) && entity instanceof Skeleton) {
            if (plugin.config.getBossBoolean("Skeleton_Drop_XP")) {
                event.setDroppedExp(plugin.config.getBossInt("Skeleton_XP"));
            }
            if (!plugin.config.getBossBoolean("Skeleton_Drop_Items")) {
                event.getDrops().clear();
            }
        }
        if (entity.getCustomName() != null && entity.getCustomName().equals(plugin.config.getBossStr("Wolf_Name")) && entity instanceof Wolf) {
            if (plugin.config.getBossBoolean("Wolf_Drop_XP")) {
                event.setDroppedExp(plugin.config.getBossInt("Wolf_XP"));
            }
            if (!plugin.config.getBossBoolean("Wolf_Drop_Items")) {
                event.getDrops().clear();
            }
        }
        if (entity.getCustomName() != null && entity.getCustomName().equals(plugin.config.getBossStr("BabyZombie_Name")) && entity instanceof Zombie) {
            if (plugin.config.getBossBoolean("BabyZombie_Drop_XP")) {
                event.setDroppedExp(plugin.config.getBossInt("BabyZombie_XP"));
            }
            if (!plugin.config.getBossBoolean("BabyZombie_Drop_Items")) {
                event.getDrops().clear();
            }
        }
        if (entity.getCustomName() != null && entity.getCustomName().equals(plugin.config.getBossStr("BabyWolf_Name")) && entity instanceof Wolf) {
            if (plugin.config.getBossBoolean("BabyWolf_Drop_XP")) {
                event.setDroppedExp(plugin.config.getBossInt("BabyWolf_XP"));
            }
            if (!plugin.config.getBossBoolean("BabyWolf_Drop_Items")) {
                event.getDrops().clear();
            }
        }
    }

    @EventHandler
    public void onTarget(EntityTargetEvent event) {
        Entity target = event.getTarget();
        Entity  attacker = event.getEntity();
        if (target instanceof Skeleton && attacker instanceof Wolf && target.getCustomName() != null && target.getCustomName().equals(plugin.config.getBossStr("Skeleton_Name")) && attacker.getCustomName() != null && attacker.getCustomName().equals(plugin.config.getBossStr("Wolf_Name"))) {
            event.setTarget(null);
            event.setCancelled(true);
        }
        if (target instanceof Skeleton && attacker instanceof Wolf && target.getCustomName() != null && target.getCustomName().equals(plugin.config.getBossStr("Skeleton_Name")) && attacker.getCustomName() != null && attacker.getCustomName().equals(plugin.config.getBossStr("BabyWolf_Name"))) {
            event.setTarget(null);
            event.setCancelled(true);
        }
        if (target instanceof Skeleton && attacker instanceof Wolf && target.getCustomName() != null && target.getCustomName().equals(plugin.config.getBossStr("Skeleton_Name")) && attacker.getCustomName() != null && attacker.getCustomName().equals(plugin.config.getBossStr("Wolf_Name")+""+ ChatColor.BLUE+""+ChatColor.ITALIC+"'s Minion")) {
            event.setTarget(null);
            event.setCancelled(true);
        }
    }
}
