package net.diecode.killermoney.configs;

import net.diecode.killermoney.Logger;
import net.diecode.killermoney.enums.MessageMethod;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

public class DefaultConfig extends SuperConfig {

    private static DefaultConfig instance;

    private static int decimalPlaces;
    private static MessageMethod messageMethod;
    private static boolean hookMobArena;
    private static boolean disableRewardsInArena;
    private static boolean antiFarmingSpawner;
    private static boolean antiFarmingSpawnerEgg;
    private static int limitResetTime;
    private static boolean reachedLimitMessage;
    private static ArrayList<GameMode> allowedGameModes = new ArrayList<>();

    private static boolean moneyItemDropEnabled;
    private static String moneyItemName;
    private static Material moneyItemMaterial = Material.GOLD_INGOT;
    private static boolean moneyItemAnyonePickUp;

    private static HashMap<String, Double> permBasedMoneyMultipliers = new HashMap<>();
    private static HashMap<String, Double> permBasedLimitMultipliers = new HashMap<>();

    public DefaultConfig(String fileName) {
        super(fileName);

        instance = this;
    }

    @Override
    public void load() {
        reload();

        // Money decimal places
        decimalPlaces = getConfig().getInt("Global-settings.Money.Decimal-places");

        // Message method value
        try {
            messageMethod = MessageMethod.valueOf(getConfig().getString("Global-settings.General.Message-method", "CHAT")
                    .toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            messageMethod = MessageMethod.CHAT;

            Logger.warning("Invalid message method value. Using default \"CHAT\" value.");
        }

        // Mobarena support
        hookMobArena = getConfig().getBoolean("Hook.MobArena.Enabled");

        // Disable reward in mobarena
        disableRewardsInArena = getConfig().getBoolean("Hook.MobArena.Disable-rewards-in-arena");

        // Farming settings
        antiFarmingSpawner = getConfig().getBoolean("Global-settings.Farming.Disable-spawner-farming");
        antiFarmingSpawnerEgg = getConfig().getBoolean("Global-settings.Farming.Disable-spawner-egg-farming");

        // Limit reset time
        limitResetTime = getConfig().getInt("Global-settings.Limit.Reset-time");

        if (limitResetTime < 1) {
            limitResetTime = 24;
        }

        // Limit reached settings
        reachedLimitMessage = getConfig().getBoolean("Global-settings.Limit.Reached-limit-message");

        // Allowed gamemodes
        allowedGameModes.clear();
        String[] splitted = getConfig().getString("Global-settings.General.Allowed-gamemodes").replace(" ", "").split(",");

        for (String s : splitted) {
            try {
                GameMode gamemode = GameMode.valueOf(s.toUpperCase(Locale.ROOT));

                if (!allowedGameModes.contains(gamemode)) {
                    allowedGameModes.add(gamemode);
                }
            } catch (IllegalArgumentException e) {
                Logger.warning("Invalid gamemode type: " + s);
            }
        }

        // Money Item drop settings
        moneyItemDropEnabled = getConfig().getBoolean("Global-settings.Money.Item-drop.Enabled");
        moneyItemName = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Global-settings.Money.Item-drop.Item-name"));
        moneyItemAnyonePickUp = getConfig().getBoolean("Global-settings.Money.Item-drop.Anyone-can-pick-it-up", true);

        Material material = Material.matchMaterial(getConfig().getString("Global-settings.Money.Item-drop.Item-material", "GOLD_INGOT"));
        if (material != null && material.isItem() && !material.isAir()) {
            moneyItemMaterial = material;
        } else {
            moneyItemMaterial = Material.GOLD_INGOT;
            Logger.warning("Invalid money item material. Using default: GOLD_INGOT");
        }

        permBasedMoneyMultipliers.clear();
        Set<String> perms = getConfig().getConfigurationSection("Configurable-permissions.Money-multipliers")
                .getKeys(false);

        for (String s : perms) {
            try {
                double value = getConfig().getDouble("Configurable-permissions.Money-multipliers." + s);

                permBasedMoneyMultipliers.put(s, value);
            } catch (Exception e) {
                Logger.warning("Invalid money multiplier value at: " + s);
            }
        }

        permBasedLimitMultipliers.clear();
        perms = getConfig().getConfigurationSection("Configurable-permissions.Limit-multipliers").getKeys(false);

        for (String s : perms) {
            try {
                double value = getConfig().getDouble("Configurable-permissions.Limit-multipliers." + s);

                permBasedLimitMultipliers.put(s, value);
            } catch (Exception e) {
                Logger.warning("Invalid limit multiplier value at: " + s);
            }
        }
    }

    public static DefaultConfig getInstance() {
        return instance;
    }

    public static int getDecimalPlaces() {
        return decimalPlaces;
    }

    public static MessageMethod getMessageMethod() {
        return messageMethod;
    }

    public static boolean isHookMobArena() {
        return hookMobArena;
    }

    public static boolean isDisableRewardsInArena() {
        return disableRewardsInArena;
    }

    public static boolean isAntiFarmingSpawner() {
        return antiFarmingSpawner;
    }

    public static boolean isAntiFarmingSpawnerEgg() {
        return antiFarmingSpawnerEgg;
    }

    public static int getLimitResetTime() {
        return limitResetTime;
    }

    public static boolean isReachedLimitMessage() {
        return reachedLimitMessage;
    }

    public static ArrayList<GameMode> getAllowedGameModes() {
        return allowedGameModes;
    }

    public static boolean isMoneyItemDropEnabled() {
        return moneyItemDropEnabled;
    }

    public static String getMoneyItemName() {
        return moneyItemName;
    }

    public static Material getMoneyItemMaterial() {
        return moneyItemMaterial;
    }

    public static boolean isMoneyItemAnyonePickUp() {
        return moneyItemAnyonePickUp;
    }

    public static HashMap<String, Double> getPermBasedMoneyMultipliers() {
        return permBasedMoneyMultipliers;
    }

    public static HashMap<String, Double> getPermBasedLimitMultipliers() {
        return permBasedLimitMultipliers;
    }
}
