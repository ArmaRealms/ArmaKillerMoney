package net.diecode.killermoney.managers;

import net.diecode.killermoney.configs.DefaultConfig;
import net.diecode.killermoney.configs.EntitiesConfig;
import net.diecode.killermoney.configs.LangConfig;
import net.diecode.killermoney.configs.PlayersConfig;

public class ConfigManager {

    private static DefaultConfig defaultConfig;
    private static EntitiesConfig entitiesConfig;
    private static LangConfig langConfig;
    private static PlayersConfig playersConfig;

    public static void init() {
        defaultConfig = new DefaultConfig("config.yml");
        entitiesConfig = new EntitiesConfig("entities.yml");
        langConfig = new LangConfig("lang.yml");
        playersConfig = new PlayersConfig();

        reloadConfigs();
    }

    public static void reloadConfigs() {
        DefaultConfig.getInstance().load();
        EntitiesConfig.getInstance().load();
        LangConfig.getInstance().load();
    }

    public static DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    public static EntitiesConfig getEntitiesConfig() {
        return entitiesConfig;
    }

    public static LangConfig getLangConfig() {
        return langConfig;
    }
}
