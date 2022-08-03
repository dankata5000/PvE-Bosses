package me.thegoldenmine.pvebosses.pvebosses;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    public PvEbosses plugin;
    private final File BossFile;
    private FileConfiguration BossData;

    public Config(PvEbosses main) throws IOException {
        plugin = main;
        File dataFolder = plugin.getDataFolder();
        BossFile = new File(dataFolder, "bosses.yml");
        BossData = YamlConfiguration.loadConfiguration(BossFile);
        if (!dataFolder.exists() && !BossFile.exists()) {
            System.out.println("<---{ Creating New Config Files }--->");
            System.out.println(" ");
            if (dataFolder.mkdir()) {
                System.out.println("  <- Folder Created ->");
            } else {
                System.out.println("  <- Couldn't Create The Folder ->");
            }
            System.out.println(" ");
            if (BossFile.createNewFile()) {
                System.out.println("  <- Boss File Created ->");
            } else {
                System.out.println("  <- Couldn't Create Boss File ->");
            }
            // Zombie
            setBossInt("Zombie_Health", 1000);
            setBossInt("Zombie_Damage", 4);
            setBossInt("Zombie_Minion_Damage", 6);
            setBossInt("Zombie_Minions", 10);
            setBossInt("Zombie_Minion_Health", 100);
            setBossBoolean("Zombie_Jump", true);
            setBossBoolean("Zombie_Spawn_Minions", true);
            setBossStr("Zombie_Name", "§6§lBob");

            // Skeleton
            setBossStr("Skeleton_Name", "§6§lJohn");
            setBossInt("Skeleton_Health", 1000);
            setBossInt("Skeleton_Damage", 4);
            setBossInt("Skeleton_Thunder_Damage", 3);
            setBossBoolean("Skeleton_Shoot_Fireballs", true);
            setBossBoolean("Skeleton_Spawn_Thunder", true);

            // Wolf
            setBossInt("Wolf_Damage", 4);
            setBossInt("Wolf_Health", 1000);
            setBossInt("Wolf_Minion_Damage", 3);
            setBossInt("Wolf_Minion_Health", 100);
            setBossBoolean("Wolf_Spawn_Minions", true);
            setBossInt("Wolf_Minions", 10);
            setBossBoolean("Wolf_Jump", true);
            setBossStr("Wolf_Name", "§6§lBruno");

            // Baby Zombie
            setBossStr("BabyZombie_Name", "§6§lBigBro");
            setBossBoolean("BabyZombie_Jump", true);
            setBossInt("BabyZombie_Damage", 6);
            setBossInt("BabyZombie_Health", 2048);
            setBossBoolean("BabyZombie_Spawn_Thunder", true);
            setBossInt("BabyZombie_Thunder_Damage", 3);

            // Baby Wolf
            setBossBoolean("BabyWolf_Jump", true);
            setBossStr("BabyWolf_Name", "§6§lRichi");
            setBossInt("BabyWolf_Damage", 6);
            setBossBoolean("BabyWolf_Spawn_Thunder", true);
            setBossInt("BabyWolf_Thunder_Damage", 3);
            setBossInt("BabyWolf_Health", 2048);

            // Custom Deaths
            // baby wolf
            setBossInt("BabyWolf_XP" ,30);
            setBossBoolean("BabyWolf_Drop_XP", true);
            setBossBoolean("BabyWolf_Drop_Items", false);
            // baby zombie
            setBossInt("BabyZombie_XP" ,30);
            setBossBoolean("BabyZombie_Drop_XP", true);
            setBossBoolean("BabyZombie_Drop_Items", false);
            // wolf
            setBossInt("Wolf_XP" ,30);
            setBossBoolean("Wolf_Drop_XP", true);
            setBossBoolean("Wolf_Drop_Items", false);
            // skeleton
            setBossInt("Skeleton_XP" ,30);
            setBossBoolean("Skeleton_Drop_XP", true);
            setBossBoolean("Skeleton_Drop_Items", false);
            // zombie
            setBossInt("Zombie_XP" ,30);
            setBossBoolean("Zombie_Drop_XP", true);
            setBossBoolean("Zombie_Drop_Items", false);
        }
        if (!dataFolder.exists()) {
            if (dataFolder.mkdir()) {
                System.out.println("  <- Created A Folder ->");
                System.out.println(" ");
            } else {
                System.out.println("  <- Couldn't Create The Folder ->");
            }
        }

        if (!BossFile.exists()) {
            if (BossFile.createNewFile()) {
                System.out.println("  <- Created bosses.yml ->");
            } else {
                System.out.println("  <- Couldn't Create Boss File ->");
            }
            System.out.println(" ");
            try {
                BossData.load(BossFile);
                System.out.println("  <- Loaded kills.yml ->");
                System.out.println(" ");
            } catch (Exception e) {
                System.out.println("  <- Couldn't Load bosses.yml ->");
                System.out.println(" ");
                System.out.println("- PvE Bosses ERROR - trying to load bosses.yml");
            }
        }
    }

    // String
    public void setBossStr(String path, String i) {
        BossData.set(path, i);
        saveBoss();
        reloadBoss();
    }

    public String getBossStr(String path) {
        return BossData.getString(path);
    }


    // Int
    public void setBossInt(String path, int i) {
        BossData.set(path, i);
        saveBoss();
        reloadBoss();
    }

    public int getBossInt(String path) {
        return BossData.getInt(path);
    }

    // Boolean
    public void setBossBoolean(String path, boolean i) {
        BossData.set(path, i);
        saveBoss();
        reloadBoss();
    }

    public boolean getBossBoolean(String path) {
        return BossData.getBoolean(path);
    }

    // Save
    public synchronized void saveBoss() {
        try {
            BossData.save(BossFile);
        } catch (IOException e) {
            throw new RuntimeException("- PvE Bosses ERROR - Cannot save bosses.yml", e);
        }
    }

    // Reload
    public synchronized void reloadBoss() {
        BossData = YamlConfiguration.loadConfiguration(BossFile);
    }
}
