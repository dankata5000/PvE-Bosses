package me.thegoldenmine.pvebosses.pvebosses;

import me.thegoldenmine.pvebosses.pvebosses.Cmds.CmdTabComplete;
import me.thegoldenmine.pvebosses.pvebosses.Cmds.SpawnBoss;
import me.thegoldenmine.pvebosses.pvebosses.CoolDowns.PowerCoolDown;
import me.thegoldenmine.pvebosses.pvebosses.Listeners.BossListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class PvEbosses extends JavaPlugin {
    public PvEbosses pvebosses;
    public Config config;
    public PowerCoolDown powerCoolDown;

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
            getCommand("pveboss").setExecutor(new SpawnBoss(this));
            getCommand("pveboss").setTabCompleter(new CmdTabComplete());
            getServer().getPluginManager().registerEvents(new BossListener(this), this);
        } else {
            getLogger().severe("Failed to setup PvE Bosses");
            getLogger().severe("Your server version is not compatible with this plugin!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("<--{ Shutting Down }-->");
    }
}
