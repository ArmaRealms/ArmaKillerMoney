package net.diecode.killermoney;

import com.garbagemule.MobArena.MobArenaHandler;
import net.diecode.killermoney.commands.KMAdminCommand;
import net.diecode.killermoney.commands.KMCommand;
import net.diecode.killermoney.functions.*;
import net.diecode.killermoney.managers.ConfigManager;
import net.diecode.killermoney.managers.EntityManager;
import net.diecode.killermoney.managers.KMPlayerManager;
import net.diecode.killermoney.objects.KMPlayer;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class BukkitMain extends JavaPlugin {

    private static BukkitMain instance;

    private static Economy economy;
    private static MobArenaHandler mobArenaHandler;

    private boolean initEconomy() {
        RegisteredServiceProvider<Economy> economyProvider =
                Bukkit.getServicesManager().getRegistration(Economy.class);

        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    @Override
    public void onEnable() {
        instance = this;

        ConfigManager.init();

        if (!Bukkit.getPluginManager().isPluginEnabled("Vault") || !initEconomy()) {
            Logger.warning("Vault or economy plugin not found! Money reward disabled.");
        }

        if (Bukkit.getPluginManager().isPluginEnabled("MobArena")) {
            mobArenaHandler = new MobArenaHandler();
        }

        getCommand("km").setExecutor(new KMCommand());
        getCommand("kmadmin").setExecutor(new KMAdminCommand());

        Bukkit.getPluginManager().registerEvents(new EntityManager(), this);
        Bukkit.getPluginManager().registerEvents(new MoneyHandler(), this);
        Bukkit.getPluginManager().registerEvents(new CItemHandler(), this);
        Bukkit.getPluginManager().registerEvents(new CCommandHandler(), this);
        Bukkit.getPluginManager().registerEvents(new CExpHandler(), this);
        Bukkit.getPluginManager().registerEvents(new MessageHandler(), this);
        Bukkit.getPluginManager().registerEvents(new CashTransferHandler(), this);
        Bukkit.getPluginManager().registerEvents(new AntiFarmingHandler(), this);
        Bukkit.getPluginManager().registerEvents(new LimitHandler(), this);
        Bukkit.getPluginManager().registerEvents(new MultiplierHandler(), this);
        Bukkit.getPluginManager().registerEvents(new KMPlayerManager(), this);

        for (Player p : Bukkit.getOnlinePlayers()) {
            UUID pUUID = p.getUniqueId();

            if (!KMPlayerManager.getKMPlayers().containsKey(pUUID)) {
                KMPlayerManager.getKMPlayers().put(pUUID, new KMPlayer(p));
            }
        }
    }

    @Override
    public void onDisable() {
        instance = null;
        economy = null;
        mobArenaHandler = null;
    }

    public static BukkitMain getInstance() {
        return instance;
    }

    public static Economy getEconomy() {
        return economy;
    }

    public static MobArenaHandler getMobArenaHandler() {
        return mobArenaHandler;
    }

    public static boolean isPlayerInArena(Player player) {
        return mobArenaHandler != null && mobArenaHandler.isPlayerInArena(player);
    }
}
