package me.thegoldenmine.pvebosses.pvebosses.CoolDowns;

import me.thegoldenmine.pvebosses.pvebosses.PvEbosses;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class PowerCoolDown {
    private final PvEbosses plugin;

    public PowerCoolDown(PvEbosses main) {
        plugin = main;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!Bukkit.getOnlinePlayers().isEmpty()) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        for (LivingEntity living : player.getWorld().getLivingEntities()) {
                            if (living.getCustomName() != null && living.getCustomName().equals(plugin.config.getBossStr("Zombie_Name")) && living instanceof Zombie && plugin.config.getBossBoolean("Zombie_Jump")) {
                                Zombie zombieBoss = (Zombie) living;
                                if (zombieBoss.getTarget() != null) {
                                    zombieBoss.setVelocity(zombieBoss.getTarget().getLocation().subtract(zombieBoss.getLocation()).toVector().normalize().multiply(3));
                                } else {
                                    zombieBoss.setVelocity(zombieBoss.getTargetBlock(null, 5).getLocation().subtract(zombieBoss.getLocation()).toVector().normalize().multiply(3));
                                }
                            }
                            if (living.getCustomName() != null && living.getCustomName().equals(plugin.config.getBossStr("BabyZombie_Name")) && living instanceof Zombie && plugin.config.getBossBoolean("BabyZombie_Jump")) {
                                Zombie zombieBoss = (Zombie) living;
                                if (zombieBoss.getTarget() != null) {
                                    zombieBoss.setVelocity(zombieBoss.getTarget().getLocation().subtract(zombieBoss.getLocation()).toVector().normalize().multiply(3));
                                } else {
                                    zombieBoss.setVelocity(zombieBoss.getTargetBlock(null, 5).getLocation().subtract(zombieBoss.getLocation()).toVector().normalize().multiply(3));
                                }
                            }
                            if (living.getCustomName() != null && living.getCustomName().equals(plugin.config.getBossStr("Wolf_Name")) && living instanceof Wolf && plugin.config.getBossBoolean("Wolf_Jump")) {
                                Wolf wolf = (Wolf) living;
                                if (wolf.getTarget() != null) {
                                    wolf.setVelocity(wolf.getTarget().getLocation().subtract(wolf.getLocation()).toVector().normalize().multiply(2.5));
                                } else {
                                    wolf.setVelocity(wolf.getTargetBlock(null, 5).getLocation().subtract(wolf.getLocation()).toVector().normalize().multiply(2.5));
                                }
                            }
                            if (living.getCustomName() != null && living.getCustomName().equals(plugin.config.getBossStr("BabyWolf_Name")) && living instanceof Wolf && plugin.config.getBossBoolean("BabyWolf_Jump")) {
                                Wolf wolf = (Wolf) living;
                                if (wolf.getTarget() != null) {
                                    wolf.setVelocity(wolf.getTarget().getLocation().subtract(wolf.getLocation()).toVector().normalize().multiply(3.5));
                                } else {
                                    wolf.setVelocity(wolf.getTargetBlock(null, 5).getLocation().subtract(wolf.getLocation()).toVector().normalize().multiply(3.5));
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 200);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!Bukkit.getOnlinePlayers().isEmpty()) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        for (LivingEntity living : player.getWorld().getLivingEntities()) {
                            NamespacedKey name = new NamespacedKey(plugin, "boss");
                            PersistentDataContainer data = living.getPersistentDataContainer();
                            if (living.getCustomName() != null && living instanceof Skeleton && data.has(name, PersistentDataType.STRING)) {
                                Skeleton skeleton = (Skeleton) living;
                                if (skeleton.getTarget() == null) {
                                    if (!skeleton.getNearbyEntities(10, 10, 10).isEmpty()) {
                                        for (Entity entity : skeleton.getNearbyEntities(10, 10, 10)) {
                                            if (entity instanceof Player) {
                                                Player players = (Player) entity;
                                                skeleton.setTarget(players);
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (plugin.config.getBossBoolean("Skeleton_Shoot_Fireballs")) {
                                    Fireball fireball = skeleton.launchProjectile(Fireball.class);
                                    fireball.setBounce(false);
                                    fireball.setShooter(skeleton);
                                    fireball.setYield(0);
                                }
                                if (plugin.config.getBossBoolean("Skeleton_Spawn_Thunder")) {
                                    if (!skeleton.getNearbyEntities(7, 7, 7).isEmpty()) {
                                        for (Entity entity : skeleton.getNearbyEntities(7, 7, 7)) {
                                            if (entity instanceof Player) {
                                                Player players = (Player) entity;
                                                skeleton.getWorld().strikeLightningEffect(players.getLocation());
                                                players.damage(plugin.config.getBossInt("Skeleton_Thunder_Damage"));
                                            }
                                        }
                                    }
                                }
                            }
                            if (living.getCustomName() != null && living instanceof Zombie && data.has(name, PersistentDataType.STRING)) {
                                Zombie zombie = (Zombie) living;
                                if (zombie.getTarget() == null) {
                                    if (!zombie.getNearbyEntities(10, 10, 10).isEmpty()) {
                                        for (Entity entity : zombie.getNearbyEntities(10, 10, 10)) {
                                            if (entity instanceof Player) {
                                                Player players = (Player) entity;
                                                zombie.setTarget(players);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            if (living.getCustomName() != null && living instanceof Wolf && data.has(name, PersistentDataType.STRING)) {
                                Wolf wolf = (Wolf) living;
                                if (wolf.getTarget() == null) {
                                    if (!wolf.getNearbyEntities(10, 10, 10).isEmpty()) {
                                        for (Entity entity : wolf.getNearbyEntities(10, 10, 10)) {
                                            if (entity instanceof Player) {
                                                Player players = (Player) entity;
                                                wolf.setTarget(players);
                                                wolf.setAngry(true);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            if (living.getCustomName() != null && living instanceof Wolf && data.has(name, PersistentDataType.STRING)) {
                                Wolf wolf = (Wolf) living;
                                if (wolf.getTarget() == null) {
                                    if (!wolf.getNearbyEntities(10, 10, 10).isEmpty()) {
                                        for (Entity entity : wolf.getNearbyEntities(10, 10, 10)) {
                                            if (entity instanceof Player) {
                                                Player players = (Player) entity;
                                                wolf.setTarget(players);
                                                wolf.setAngry(true);
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (plugin.config.getBossBoolean("BabyWolf_Spawn_Thunder") && wolf.getCustomName() != null && wolf.getCustomName().contains(plugin.config.getBossStr("BabyZombie_Name"))) {
                                    if (!wolf.getNearbyEntities(7, 7, 7).isEmpty()) {
                                        for (Entity entity : wolf.getNearbyEntities(7, 7, 7)) {
                                            if (entity instanceof Player) {
                                                Player players = (Player) entity;
                                                wolf.getWorld().strikeLightningEffect(players.getLocation());
                                                players.damage(plugin.config.getBossInt("BabyWolf_Thunder_Damage"));
                                            }
                                        }
                                    }
                                }
                            }
                            if (living.getCustomName() != null  && living instanceof Zombie && data.has(name, PersistentDataType.STRING)) {
                                Zombie zombie = (Zombie) living;
                                if (zombie.getTarget() == null) {
                                    if (!zombie.getNearbyEntities(10, 10, 10).isEmpty()) {
                                        for (Entity entity : zombie.getNearbyEntities(10, 10, 10)) {
                                            if (entity instanceof Player) {
                                                Player players = (Player) entity;
                                                zombie.setTarget(players);
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (plugin.config.getBossBoolean("BabyZombie_Spawn_Thunder")) {
                                    if (!zombie.getNearbyEntities(7, 7, 7).isEmpty()) {
                                        for (Entity entity : zombie.getNearbyEntities(7, 7, 7)) {
                                            if (entity instanceof Player) {
                                                Player players = (Player) entity;
                                                zombie.getWorld().strikeLightningEffect(players.getLocation());
                                                players.damage(plugin.config.getBossInt("BabyZombie_Thunder_Damage"));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 120);
    }
}
