package me.thegoldenmine.pvebosses.pvebosses;

import me.thegoldenmine.pvebosses.pvebosses.CoolDowns.PowerCoolDown;
import me.thegoldenmine.pvebosses.pvebosses.Listeners.BossListener;
import me.thegoldenmine.pvebosses.pvebosses.cmds.CmdTabComplete;
import me.thegoldenmine.pvebosses.pvebosses.cmds.SetRespawnPoint;
import me.thegoldenmine.pvebosses.pvebosses.cmds.SpawnBossCmd;

import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PvEbosses extends JavaPlugin {
    public PvEbosses pvebosses;
    public Config config;
    public PowerCoolDown powerCoolDown;

    public SpawnBosses spawnBosses;

    public List<Location> respawnLocations = new ArrayList<>();
    public List<LivingEntity> deadBosses = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        String version = "N/A";

        try {

            version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

        } catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
            getLogger().severe("Failed to setup PvE Bosses");
            getLogger().severe("Your server version is not compatible with this plugin!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        if (!version.equals("N/A")) {
            getLogger().info("Your server is running version " + version);
            /*
            boolean versionOK = "v1_18_R1".equals(version) || ..
            if (versionOK) {
                PvEbosses = this;
            }

             */
            switch (version) {
                case "v1_18_R1":
                case "v1_18_R2":
                case "v1_19_R1":
                case "v1_16_R3":
                case "v1_16_R2":
                case "v1_16_R1":
                case "v1_15_R1":
                case "v1_14_R1":
                case "v1_17_R1":
                    pvebosses = this;
                    break;
            }
        }
        // This will return true if the server version was compatible with one of our NMS classes
        // because if it is, our actionbar would not be null
        if (pvebosses != null) {
            try {
                System.out.println("<---{ Loading Config Files }--->");
                System.out.println(" ");
                System.out.println("  <-> Registering Configs <->");
                System.out.println(" ");
                config = new Config(this);
            } catch (IOException e) {
                System.out.println("<---{ Couldn't load config files }--->");
                System.out.println(" ");
                throw new RuntimeException("- PvE Bosses ERROR - Cannot prepare config files", e);
            }
            Items.init();
            powerCoolDown = new PowerCoolDown(this);
            spawnBosses = new SpawnBosses(this);
            getCommand("pveboss").setExecutor(new SpawnBossCmd(this));
            getCommand("pveboss-set-respawn-point").setExecutor(new SetRespawnPoint(this));
            getCommand("pveboss").setTabCompleter(new CmdTabComplete());
            getServer().getPluginManager().registerEvents(new BossListener(this), this);
            for (int i = 0; i <= config.getBossInt("number_of_respawn_points"); i++) {
                Location loc = StringToLocation(config.getBossStr(i+"_respawn_point"));
                if (loc != null) {
                    respawnLocations.add(loc);
                }
            }
        } else {
            getLogger().severe("Failed to setup PvE Bosses");
            getLogger().severe("Your server version is not compatible with this plugin!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        respawnLocations.clear();
        deadBosses.clear();
        System.out.println("<--{ Shutting Down }-->");
    }

    public String LocationToString(final Location loc) {
        if (loc == null) {
            return "null";
        }
        return loc.getWorld().getName() + ":" + loc.getBlockX() + ":" + loc.getBlockY() + ":" + loc.getBlockZ()+":"+loc.getYaw()+":"+loc.getPitch();
    }

    public Location StringToLocation(final String str) {
        if (str == null || str.trim().equals("")) {
            return null;
        }
        final String[] parts = str.split(":");
        if (parts.length == 6) {
            final World w = Bukkit.getServer().getWorld(parts[0]);
            final int x = Integer.parseInt(parts[1]);
            final int y = Integer.parseInt(parts[2]);
            final int z = Integer.parseInt(parts[3]);
            final float yaw = Float.parseFloat(parts[4]);
            final float pit = Float.parseFloat(parts[5]);
            return new Location(w, x, y, z, yaw, pit);
        }
        return null;
    }
}
