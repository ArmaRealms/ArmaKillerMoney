package net.diecode.killermoney.managers;

import net.diecode.killermoney.configs.LangConfig;
import net.diecode.killermoney.enums.LanguageString;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

public class LanguageManager {

    public static String cGet(LanguageString l1, Object... args) {
        String s = LangConfig.getStrings().get(l1);

        if (s != null) {
            for (int i = 0; i < args.length; i++) {
                String holder = "{" + i + "}";

                s = s.replace(holder, String.valueOf(args[i]));
            }

            return s;
        } else {
            return l1.name();
        }
    }

    public static String getEntityName(EntityType type) {
        try {
            return LangConfig.getStrings().getOrDefault(LanguageString.valueOf("ENTITIES_" + type.name()), type.name());
        } catch (IllegalArgumentException ignored) {
            return type.name();
        }
    }

    public static void send(CommandSender sender, LanguageString l1, Object... args) {
        sender.sendMessage(cGet(l1, args));
    }

}
