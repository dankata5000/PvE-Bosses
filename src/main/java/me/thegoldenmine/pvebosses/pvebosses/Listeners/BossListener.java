package me.thegoldenmine.pvebosses.pvebosses.Listeners;

import me.thegoldenmine.pvebosses.pvebosses.PvEbosses;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class BossListener implements Listener {
    private final PvEbosses plugin;

    public BossListener(PvEbosses main) {
        plugin = main;
    }

    @EventHandler
    public void onSlimeSplit(SlimeSplitEvent event) {
        NamespacedKey name = new NamespacedKey(plugin, "boss");
        Slime entity = event.getEntity();
        PersistentDataContainer data = entity.getPersistentDataContainer();
        if (entity.getCustomName() != null && data.has(name, PersistentDataType.STRING) && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        // EntityDamageEvent
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
                    if (living instanceof Creeper && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                        living.setCustomName(plugin.config.getBossStr("Creeper_Name") + " " + ChatColor.GRAY + "<" + ChatColor.GOLD + "" + String.valueOf(healthNew) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Creeper_Health")) + "" + ChatColor.GRAY + ">");
                    } else if (living instanceof Spider) {
                        if (data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                            living.setCustomName(plugin.config.getBossStr("Spider_Name") + " " + ChatColor.GRAY + "<" + ChatColor.GOLD + "" + String.valueOf(healthNew) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Spider_Health")) + "" + ChatColor.GRAY + ">");
                        }
                        if (data.get(name, PersistentDataType.STRING).equals("MINI")) {
                            living.setCustomName(plugin.config.getBossStr("Spider_Name") + "" + ChatColor.BLUE + "" + ChatColor.ITALIC + "'s Minion " + ChatColor.GRAY + "<" + ChatColor.GOLD + "" + String.valueOf(healthNew) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Spider_Minion_Health")) + "" + ChatColor.GRAY + ">");
                        }
                    } else if (living instanceof Enderman) {
                        if (data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                            living.setCustomName(plugin.config.getBossStr("Enderman_Name") + " " + ChatColor.GRAY + "<" + ChatColor.GOLD + "" + String.valueOf(healthNew) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Enderman_Health")) + "" + ChatColor.GRAY + ">");
                        }
                        if (data.get(name, PersistentDataType.STRING).equals("MINI")) {
                            living.setCustomName(plugin.config.getBossStr("Enderman_Name") + "" + ChatColor.BLUE + "" + ChatColor.ITALIC + "'s Minion " + ChatColor.GRAY + "<" + ChatColor.GOLD + "" + String.valueOf(healthNew) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Enderman_Minion_Health")) + "" + ChatColor.GRAY + ">");
                        }
                    } else if (living instanceof Witch) {
                        if (data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                            living.setCustomName(plugin.config.getBossStr("Witch_Name") + " " + ChatColor.GRAY + "<" + ChatColor.GOLD + "" + String.valueOf(healthNew) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Witch_Health")) + "" + ChatColor.GRAY + ">");
                        }
                        if (data.get(name, PersistentDataType.STRING).equals("MINI")) {
                            living.setCustomName(plugin.config.getBossStr("Witch_Name") + "" + ChatColor.BLUE + "" + ChatColor.ITALIC + "'s Minion " + ChatColor.GRAY + "<" + ChatColor.GOLD + "" + String.valueOf(healthNew) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Witch_Minion_Health")) + "" + ChatColor.GRAY + ">");
                        }
                    } else if (living instanceof Zombie) {
                        if (data.get(name, PersistentDataType.STRING).equals("MINI")) {
                            living.setCustomName(plugin.config.getBossStr("Zombie_Name") + "" + ChatColor.BLUE + "" + ChatColor.ITALIC + "'s Minion " + ChatColor.GRAY + "<" + ChatColor.GOLD + "" + String.valueOf(healthNew) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Zombie_Minion_Health")) + "" + ChatColor.GRAY + ">");
                        }
                        if (data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                            living.setCustomName(plugin.config.getBossStr("Zombie_Name") + " " + ChatColor.GRAY + "<" + ChatColor.GOLD + "" + String.valueOf(healthNew) + "" + ChatColor.GRAY + "/" + ChatColor.GREEN + "" + String.valueOf(plugin.config.getBossInt("Zombie_Health")) + "" + ChatColor.GRAY + ">");
                        }
                        if (data.get(name, PersistentDataType.STRING).equals("BABY")) {
                            living.setCustomName(plugin.config.getBossStr("BabyZombie_Name")+" "+ChatColor.GRAY+"<"+ChatColor.GOLD+""+String.valueOf(healthNew)+""+ChatColor.GRAY+"/"+ChatColor.GREEN+""+String.valueOf(plugin.config.getBossInt("BabyZombie_Health"))+""+ChatColor.GRAY+">");
                        }
                    } else if (living instanceof Wolf) {
                        if (data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                            living.setCustomName(plugin.config.getBossStr("Wolf_Name")+" "+ChatColor.GRAY+"<"+ChatColor.GOLD+""+String.valueOf(healthNew)+""+ChatColor.GRAY+"/"+ChatColor.GREEN+""+String.valueOf(plugin.config.getBossInt("Wolf_Health"))+""+ChatColor.GRAY+">");
                        }
                        if (data.get(name, PersistentDataType.STRING).equals("MINI")) {
                            living.setCustomName(plugin.config.getBossStr("Wolf_Name")+""+ChatColor.BLUE+""+ChatColor.ITALIC+"'s Minion "+ChatColor.GRAY+"<"+ChatColor.GOLD+""+String.valueOf(healthNew)+""+ChatColor.GRAY+"/"+ChatColor.GREEN+""+String.valueOf(plugin.config.getBossInt("Wolf_Minion_Health"))+""+ChatColor.GRAY+">");
                        }
                        if (data.get(name, PersistentDataType.STRING).equals("BABY")) {
                            living.setCustomName(plugin.config.getBossStr("BabyWolf_Name")+" "+ChatColor.GRAY+"<"+ChatColor.GOLD+""+String.valueOf((healthNew)+""+ChatColor.GRAY+"/"+ChatColor.GREEN+""+String.valueOf(plugin.config.getBossInt("BabyWolf_Health"))+""+ChatColor.GRAY+">"));
                        }
                    } else if (living instanceof Skeleton && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                        living.setCustomName(plugin.config.getBossStr("Skeleton_Name")+" "+ChatColor.GRAY+"<"+ChatColor.GOLD+""+String.valueOf(healthNew)+""+ChatColor.GRAY+"/"+ChatColor.GREEN+""+String.valueOf(plugin.config.getBossInt("Skeleton_Health"))+""+ChatColor.GRAY+">");
                    } else if (living instanceof Slime && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                        living.setCustomName(plugin.config.getBossStr("Slime_Name")+" "+ChatColor.GRAY+"<"+ChatColor.GOLD+""+String.valueOf(healthNew)+""+ChatColor.GRAY+"/"+ChatColor.GREEN+""+String.valueOf(plugin.config.getBossInt("Slime_Health"))+""+ChatColor.GRAY+">");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        NamespacedKey name = new NamespacedKey(plugin, "boss");
        PersistentDataContainer data = entity.getPersistentDataContainer();
        if (entity.getCustomName() != null && data.has(name, PersistentDataType.STRING)) {
            boolean BossMini = data.get(name, PersistentDataType.STRING).equals("BOSS") || data.get(name, PersistentDataType.STRING).equals("MINI");
            if (entity.getCustomName().contains(plugin.config.getBossStr("Creeper_Name")) && entity instanceof Creeper && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                if (plugin.config.getBossBoolean("Creeper_Drop_XP")) {
                    event.setDroppedExp(plugin.config.getBossInt("Creeper_XP"));
                }
                if (!plugin.config.getBossBoolean("Creeper_Drop_Items")) {
                    event.getDrops().clear();
                }
                plugin.deadBosses.add(entity);
            } else if (entity.getCustomName().contains(plugin.config.getBossStr("Slime_Name")) && entity instanceof Slime && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                if (plugin.config.getBossBoolean("Slime_Drop_XP")) {
                    event.setDroppedExp(plugin.config.getBossInt("Slime_XP"));
                }
                if (!plugin.config.getBossBoolean("Slime_Drop_Items")) {
                    event.getDrops().clear();
                }
                plugin.deadBosses.add(entity);
            } else if (entity.getCustomName().contains(plugin.config.getBossStr("Spider_Name")) && entity instanceof Spider && BossMini) {
                if (plugin.config.getBossBoolean("Spider_Drop_XP")) {
                    event.setDroppedExp(plugin.config.getBossInt("Spider_XP"));
                }
                if (!plugin.config.getBossBoolean("Spider_Drop_Items")) {
                    event.getDrops().clear();
                }
                plugin.deadBosses.add(entity);
            } else if (entity.getCustomName().contains(plugin.config.getBossStr("Witch_Name")) && entity instanceof Witch && BossMini) {
                if (plugin.config.getBossBoolean("Witch_Drop_XP")) {
                    event.setDroppedExp(plugin.config.getBossInt("Witch_XP"));
                }
                if (!plugin.config.getBossBoolean("Witch_Drop_Items")) {
                    event.getDrops().clear();
                }
                plugin.deadBosses.add(entity);
            } else if (entity.getCustomName().contains(plugin.config.getBossStr("Enderman_Name")) && entity instanceof Enderman && BossMini) {
                if (plugin.config.getBossBoolean("Enderman_Drop_XP")) {
                    event.setDroppedExp(plugin.config.getBossInt("Enderman_XP"));
                }
                if (!plugin.config.getBossBoolean("Enderman_Drop_Items")) {
                    event.getDrops().clear();
                }
                plugin.deadBosses.add(entity);
            } else if (entity.getCustomName().contains(plugin.config.getBossStr("Zombie_Name")) && entity instanceof Zombie && BossMini) {
                if (plugin.config.getBossBoolean("Zombie_Drop_XP")) {
                    event.setDroppedExp(plugin.config.getBossInt("Zombie_XP"));
                }
                if (!plugin.config.getBossBoolean("Zombie_Drop_Items")) {
                    event.getDrops().clear();
                }
                plugin.deadBosses.add(entity);
            } else if (entity.getCustomName().contains(plugin.config.getBossStr("Skeleton_Name")) && entity instanceof Skeleton) {
                if (plugin.config.getBossBoolean("Skeleton_Drop_XP")) {
                    event.setDroppedExp(plugin.config.getBossInt("Skeleton_XP"));
                }
                if (!plugin.config.getBossBoolean("Skeleton_Drop_Items")) {
                    event.getDrops().clear();
                }
                plugin.deadBosses.add(entity);
            } else if (entity.getCustomName().contains(plugin.config.getBossStr("Wolf_Name")) && entity instanceof Wolf && BossMini) {
                if (plugin.config.getBossBoolean("Wolf_Drop_XP")) {
                    event.setDroppedExp(plugin.config.getBossInt("Wolf_XP"));
                }
                if (!plugin.config.getBossBoolean("Wolf_Drop_Items")) {
                    event.getDrops().clear();
                }
                plugin.deadBosses.add(entity);
            } else if (entity.getCustomName().contains(plugin.config.getBossStr("BabyZombie_Name")) && entity instanceof Zombie) {
                if (plugin.config.getBossBoolean("BabyZombie_Drop_XP")) {
                    event.setDroppedExp(plugin.config.getBossInt("BabyZombie_XP"));
                }
                if (!plugin.config.getBossBoolean("BabyZombie_Drop_Items")) {
                    event.getDrops().clear();
                }
                plugin.deadBosses.add(entity);
            } else if (entity.getCustomName().contains(plugin.config.getBossStr("BabyWolf_Name")) && entity instanceof Wolf) {
                if (plugin.config.getBossBoolean("BabyWolf_Drop_XP")) {
                    event.setDroppedExp(plugin.config.getBossInt("BabyWolf_XP"));
                }
                if (!plugin.config.getBossBoolean("BabyWolf_Drop_Items")) {
                    event.getDrops().clear();
                }
                plugin.deadBosses.add(entity);
            }
        }
    }

    @EventHandler
    public void onTarget(EntityTargetEvent event) {
        Entity target = event.getTarget();
        Entity  attacker = event.getEntity();
        if (target instanceof Skeleton && attacker instanceof Wolf && target.getCustomName() != null && target.getCustomName().equals(plugin.config.getBossStr("Skeleton_Name")) && attacker.getCustomName() != null) {
            if (attacker.getCustomName().equals(plugin.config.getBossStr("Wolf_Name"))) {
                event.setTarget(null);
                event.setCancelled(true);
            }
            if (attacker.getCustomName().equals(plugin.config.getBossStr("BabyWolf_Name"))) {
                event.setTarget(null);
                event.setCancelled(true);
            }
            if (attacker.getCustomName().equals(plugin.config.getBossStr("Wolf_Name") + "" + ChatColor.BLUE + "" + ChatColor.ITALIC + "'s Minion")) {
                event.setTarget(null);
                event.setCancelled(true);
            }
        }
    }
}
