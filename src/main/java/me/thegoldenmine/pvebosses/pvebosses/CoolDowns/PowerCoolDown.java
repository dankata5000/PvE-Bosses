package me.thegoldenmine.pvebosses.pvebosses.CoolDowns;

import me.thegoldenmine.pvebosses.pvebosses.PvEbosses;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class PowerCoolDown {
    private final PvEbosses plugin;

    public PowerCoolDown(PvEbosses main) {
        plugin = main;
        int cooldown = plugin.config.getBossInt("respawn_cooldown_seconds") * 20;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!plugin.respawnLocations.isEmpty() && !plugin.deadBosses.isEmpty() && plugin.config.getBossBoolean("enable_respawn")) {
                    int randomRespawnIndex = new Random().nextInt(plugin.respawnLocations.size());
                    if (plugin.respawnLocations.get(randomRespawnIndex) == null) {
                        while (plugin.respawnLocations.get(randomRespawnIndex) == null) {
                            randomRespawnIndex = new Random().nextInt(plugin.respawnLocations.size()) - 1;
                        }
                    } else {
                        Location RespawnLoc = plugin.respawnLocations.get(randomRespawnIndex);
                        if (RespawnLoc != null) {
                            for (LivingEntity entity : plugin.deadBosses) {
                                if (entity != null) {
                                    NamespacedKey nameKey = new NamespacedKey(plugin, "boss");
                                    PersistentDataContainer dataEntity = entity.getPersistentDataContainer();
                                    if (dataEntity.has(nameKey, PersistentDataType.STRING)) {
                                        if (entity instanceof Wolf && dataEntity.get(nameKey, PersistentDataType.STRING).equals("BABY")) {
                                            plugin.spawnBosses.BabyWolf(RespawnLoc);
                                        } else if (entity instanceof Wolf && dataEntity.get(nameKey, PersistentDataType.STRING).equals("BOSS")) {
                                            plugin.spawnBosses.Wolf(RespawnLoc);
                                        } else if (entity instanceof Zombie && dataEntity.get(nameKey, PersistentDataType.STRING).equals("BABY")) {
                                            plugin.spawnBosses.BabyZombie(RespawnLoc);
                                        } else if (entity instanceof Zombie && dataEntity.get(nameKey, PersistentDataType.STRING).equals("BOSS")) {
                                            plugin.spawnBosses.Zombie(RespawnLoc);
                                        } else if (entity instanceof Creeper && dataEntity.get(nameKey, PersistentDataType.STRING).equals("BOSS")) {
                                            plugin.spawnBosses.Creeper(RespawnLoc);
                                        } else if (entity instanceof Enderman && dataEntity.get(nameKey, PersistentDataType.STRING).equals("BOSS")) {
                                            plugin.spawnBosses.Enderman(RespawnLoc);
                                        } else if (entity instanceof Witch && dataEntity.get(nameKey, PersistentDataType.STRING).equals("BOSS")) {
                                            plugin.spawnBosses.Witch(RespawnLoc);
                                        } else if (entity instanceof Slime && dataEntity.get(nameKey, PersistentDataType.STRING).equals("BOSS")) {
                                            plugin.spawnBosses.Slime(RespawnLoc);
                                        } else if (entity instanceof Spider && dataEntity.get(nameKey, PersistentDataType.STRING).equals("BOSS")) {
                                            plugin.spawnBosses.Spider(RespawnLoc);
                                        }
                                    }
                                }
                            }
                            plugin.deadBosses.clear();
                        } else {
                            while (RespawnLoc == null) {
                                randomRespawnIndex = new Random().nextInt(plugin.respawnLocations.size()) - 1;
                                RespawnLoc = plugin.respawnLocations.get(randomRespawnIndex);
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0, cooldown);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!Bukkit.getOnlinePlayers().isEmpty()) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        for (LivingEntity living : player.getWorld().getLivingEntities()) {
                            NamespacedKey name = new NamespacedKey(plugin, "boss");
                            PersistentDataContainer data = living.getPersistentDataContainer();
                            if (living.getCustomName() != null && data.has(name, PersistentDataType.STRING)) {
                                if (living.getCustomName().contains(plugin.config.getBossStr("Witch_Name")) && living instanceof Witch && plugin.config.getBossBoolean("Witch_Spawn_Explosion") && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                                    Witch witch = (Witch) living;
                                    if (!witch.getNearbyEntities(10, 10, 10).isEmpty()) {
                                        for (Entity entity : witch.getNearbyEntities(10, 10, 10)) {
                                            if (entity instanceof Player) {
                                                Player playerHit = (Player) entity;
                                                witch.getWorld().spawnParticle(Particle.FLAME, playerHit.getLocation(), 30);
                                                playerHit.damage(plugin.config.getBossInt("Witch_Explosion_Damage"));
                                            }
                                        }
                                    }
                                }
                                if (living.getCustomName().contains(plugin.config.getBossStr("Witch_Name")) && living instanceof Witch && plugin.config.getBossBoolean("Witch_Minion_Spawn_Explosion") && data.get(name, PersistentDataType.STRING).equals("MINI")) {
                                    Witch witch = (Witch) living;
                                    if (!witch.getNearbyEntities(10, 10, 10).isEmpty()) {
                                        for (Entity entity : witch.getNearbyEntities(10, 10, 10)) {
                                            if (entity instanceof Player) {
                                                Player playerHit = (Player) entity;
                                                witch.getWorld().spawnParticle(Particle.FLAME, playerHit.getLocation(), 30);
                                                playerHit.damage(plugin.config.getBossInt("Witch_Minion_Explosion_Damage"));
                                            }
                                        }
                                    }
                                }
                                if (living.getCustomName().contains(plugin.config.getBossStr("Slime_Name")) && living instanceof Slime && plugin.config.getBossBoolean("Slime_Jump") && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                                    Slime slime = (Slime) living;
                                    if (slime.getTarget() != null) {
                                        slime.setVelocity(slime.getTarget().getLocation().add(0, 0.1, 0).subtract(slime.getLocation()).toVector().normalize().multiply(1.5));
                                    }
                                }
                                if (living.getCustomName().contains(plugin.config.getBossStr("Creeper_Name")) && living instanceof Creeper && plugin.config.getBossBoolean("Creeper_Spawn_Explosions") && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                                    Creeper creeper = (Creeper) living;
                                    if (!creeper.getNearbyEntities(10, 10, 10).isEmpty()) {
                                        for (Entity entity : creeper.getNearbyEntities(10, 10, 10)) {
                                            if (entity instanceof Player) {
                                                Player playerHit = (Player) entity;
                                                creeper.getWorld().spawnParticle(Particle.FLAME, playerHit.getLocation(), 30);
                                                playerHit.damage(plugin.config.getBossInt("Creeper_Explosion_Damage"));
                                            }
                                        }
                                    }
                                }
                                if (living.getCustomName().contains(plugin.config.getBossStr("Slime_Name")) && living instanceof Slime && plugin.config.getBossBoolean("Slime_Spawn_Explosion") && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                                    Slime slime = (Slime) living;
                                    if (!slime.getNearbyEntities(10, 10, 10).isEmpty()) {
                                        for (Entity entity : slime.getNearbyEntities(10, 10, 10)) {
                                            if (entity instanceof Player) {
                                                Player playerHit = (Player) entity;
                                                slime.getWorld().spawnParticle(Particle.FLAME, playerHit.getLocation(), 30);
                                                playerHit.damage(plugin.config.getBossInt("Slime_Explosion_Damage"));
                                            }
                                        }
                                    }
                                }
                                if (living.getCustomName().contains(plugin.config.getBossStr("Creeper_Name")) && living instanceof Creeper && plugin.config.getBossBoolean("Creeper_Jump") && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                                    Creeper creeper = (Creeper) living;
                                    if (creeper.getTarget() != null) {
                                        creeper.setVelocity(creeper.getTarget().getLocation().add(0, 1, 0).subtract(creeper.getLocation()).toVector().normalize().multiply(3));
                                    }
                                }
                                if (living.getCustomName().contains(plugin.config.getBossStr("Slime_Name")) && living instanceof Slime && plugin.config.getBossBoolean("Slime_Jump") && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                                    Slime slime = (Slime) living;
                                    if (slime.getTarget() != null) {
                                        slime.setVelocity(slime.getTarget().getLocation().subtract(slime.getLocation()).toVector().normalize().multiply(3));
                                    }
                                }
                                if (living.getCustomName().contains(plugin.config.getBossStr("Spider_Name")) && living instanceof Spider && plugin.config.getBossBoolean("Spider_Jump") && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                                    Spider spider = (Spider) living;
                                    if (spider.getTarget() != null) {
                                        spider.setVelocity(spider.getTarget().getLocation().add(0, 1, 0).subtract(spider.getLocation()).toVector().normalize().multiply(3));
                                    }
                                }
                                if (living.getCustomName().contains(plugin.config.getBossStr("Enderman_Name")) && living instanceof Enderman && plugin.config.getBossBoolean("Enderman_Jump") && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                                    Enderman enderman = (Enderman) living;
                                    if (enderman.getTarget() != null) {
                                        enderman.setVelocity(enderman.getTarget().getLocation().subtract(enderman.getLocation()).toVector().normalize().multiply(1.4));
                                    }
                                }
                                if (living.getCustomName().contains(plugin.config.getBossStr("Zombie_Name")) && living instanceof Zombie && plugin.config.getBossBoolean("Zombie_Jump") && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                                    Zombie zombieBoss = (Zombie) living;
                                    if (zombieBoss.getTarget() != null) {
                                        zombieBoss.setVelocity(zombieBoss.getTarget().getLocation().add(0, 1, 0).subtract(zombieBoss.getLocation()).toVector().normalize().multiply(3));
                                    }
                                }
                                if (living.getCustomName().contains(plugin.config.getBossStr("BabyZombie_Name")) && living instanceof Zombie && plugin.config.getBossBoolean("BabyZombie_Jump") && data.get(name, PersistentDataType.STRING).equals("BABY")) {
                                    Zombie zombieBoss = (Zombie) living;
                                    if (zombieBoss.getTarget() != null) {
                                        zombieBoss.setVelocity(zombieBoss.getTarget().getLocation().subtract(zombieBoss.getLocation()).toVector().normalize().multiply(1.3));
                                    }
                                }
                                if (living.getCustomName().contains(plugin.config.getBossStr("Wolf_Name")) && living instanceof Wolf && plugin.config.getBossBoolean("Wolf_Jump") && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                                    Wolf wolf = (Wolf) living;
                                    if (wolf.getTarget() != null) {
                                        wolf.setVelocity(wolf.getTarget().getLocation().add(0, 0.5, 0).subtract(wolf.getLocation()).toVector().normalize().multiply(1.5));
                                    }
                                }
                                if (living.getCustomName().contains(plugin.config.getBossStr("BabyWolf_Name")) && living instanceof Wolf && plugin.config.getBossBoolean("BabyWolf_Jump") && data.get(name, PersistentDataType.STRING).equals("BABY")) {
                                    Wolf wolf = (Wolf) living;
                                    if (wolf.getTarget() != null) {
                                        wolf.setVelocity(wolf.getTarget().getLocation().subtract(wolf.getLocation()).toVector().normalize().multiply(1.5));
                                    }
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
                            if (living.getCustomName() != null && data.has(name, PersistentDataType.STRING)) {
                                if (living instanceof Spider && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                                    Spider spider = (Spider) living;
                                    if (spider.getTarget() == null && !spider.getNearbyEntities(10, 10, 10).isEmpty()) {
                                        for (Entity entity : spider.getNearbyEntities(10, 10, 10)) {
                                            if (entity instanceof Player) {
                                                Player players = (Player) entity;
                                                spider.setTarget(players);
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (living instanceof Enderman && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                                    Enderman enderman = (Enderman) living;
                                    if (enderman.getTarget() == null && !enderman.getNearbyEntities(10, 10, 10).isEmpty()) {
                                        for (Entity entity : enderman.getNearbyEntities(10, 10, 10)) {
                                            if (entity instanceof Player) {
                                                Player players = (Player) entity;
                                                enderman.setTarget(players);
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (living instanceof Witch && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                                    Witch witch = (Witch) living;
                                    if (witch.getTarget() == null && !witch.getNearbyEntities(10, 10, 10).isEmpty()) {
                                        for (Entity entity : witch.getNearbyEntities(10, 10, 10)) {
                                            if (entity instanceof Player) {
                                                Player players = (Player) entity;
                                                witch.setTarget(players);
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (living instanceof Slime && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                                    Slime slime = (Slime) living;
                                    if (slime.getTarget() == null && !slime.getNearbyEntities(10, 10, 10).isEmpty()) {
                                        for (Entity entity : slime.getNearbyEntities(10, 10, 10)) {
                                            if (entity instanceof Player) {
                                                Player players = (Player) entity;
                                                slime.setTarget(players);
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (living instanceof Creeper && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                                    Creeper creeper = (Creeper) living;
                                    if (creeper.getTarget() == null && !creeper.getNearbyEntities(10, 10, 10).isEmpty()) {
                                        for (Entity entity : creeper.getNearbyEntities(10, 10, 10)) {
                                            if (entity instanceof Player) {
                                                Player players = (Player) entity;
                                                creeper.setTarget(players);
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (living instanceof Skeleton && data.get(name, PersistentDataType.STRING).equals("BOSS")) {
                                    Skeleton skeleton = (Skeleton) living;
                                    if (skeleton.getTarget() == null && !skeleton.getNearbyEntities(10, 10, 10).isEmpty()) {
                                        for (Entity entity : skeleton.getNearbyEntities(10, 10, 10)) {
                                            if (entity instanceof Player) {
                                                Player players = (Player) entity;
                                                skeleton.setTarget(players);
                                                break;
                                            }
                                        }
                                    }
                                    if (plugin.config.getBossBoolean("Skeleton_Shoot_Fireballs")) {
                                        Fireball fireball = skeleton.launchProjectile(Fireball.class);
                                        fireball.setBounce(false);
                                        fireball.setShooter(skeleton);
                                        fireball.setYield(0);
                                    }
                                    if (plugin.config.getBossBoolean("Skeleton_Spawn_Thunder") && !skeleton.getNearbyEntities(7, 7, 7).isEmpty()) {
                                        for (Entity entity : skeleton.getNearbyEntities(7, 7, 7)) {
                                            if (entity instanceof Player) {
                                                Player players = (Player) entity;
                                                skeleton.getWorld().strikeLightningEffect(players.getLocation());
                                                players.damage(plugin.config.getBossInt("Skeleton_Thunder_Damage"));
                                            }
                                        }
                                    }
                                }
                                boolean boss = data.get(name, PersistentDataType.STRING).equals("BOSS") || data.get(name, PersistentDataType.STRING).equals("MINI");
                                if (living instanceof Zombie) {
                                    Zombie zombie = (Zombie) living;
                                    if (zombie.getTarget() == null && !zombie.getNearbyEntities(10, 10, 10).isEmpty() && boss) {
                                        for (Entity entity : zombie.getNearbyEntities(10, 10, 10)) {
                                            if (entity instanceof Player) {
                                                Player players = (Player) entity;
                                                zombie.setTarget(players);
                                                break;
                                            }
                                        }
                                    }
                                    if (data.get(name, PersistentDataType.STRING).equals("BABY") && plugin.config.getBossBoolean("BabyZombie_Spawn_Thunder") && !zombie.getNearbyEntities(7, 7, 7).isEmpty()) {
                                        for (Entity entity : zombie.getNearbyEntities(7, 7, 7)) {
                                            if (entity instanceof Player) {
                                                Player players = (Player) entity;
                                                zombie.getWorld().strikeLightningEffect(players.getLocation());
                                                players.damage(plugin.config.getBossInt("BabyZombie_Thunder_Damage"));
                                            }
                                        }
                                    }
                                }
                                if (living instanceof Wolf) {
                                    Wolf wolf = (Wolf) living;
                                    if (boss && wolf.getTarget() == null && !wolf.getNearbyEntities(10, 10, 10).isEmpty()) {
                                        for (Entity entity : wolf.getNearbyEntities(10, 10, 10)) {
                                            if (entity instanceof Player) {
                                                Player players = (Player) entity;
                                                wolf.setTarget(players);
                                                wolf.setAngry(true);
                                                break;
                                            }
                                        }
                                    }
                                    if (data.get(name, PersistentDataType.STRING).equals("BABY") && plugin.config.getBossBoolean("BabyWolf_Spawn_Thunder") && wolf.getCustomName() != null && wolf.getCustomName().contains(plugin.config.getBossStr("BabyZombie_Name")) && !wolf.getNearbyEntities(7, 7, 7).isEmpty()) {
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
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 120);
    }
}
