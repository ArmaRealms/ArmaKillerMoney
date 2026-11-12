package net.diecode.killermoney.functions;

import net.diecode.killermoney.BukkitMain;
import net.diecode.killermoney.configs.DefaultConfig;
import net.diecode.killermoney.enums.LanguageString;
import net.diecode.killermoney.events.KMSendActionBarMessageEvent;
import net.diecode.killermoney.events.KMSendMessageEvent;
import net.diecode.killermoney.managers.KMPlayerManager;
import net.diecode.killermoney.managers.LanguageManager;
import net.diecode.killermoney.objects.KMPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public class MessageHandler implements Listener {

    private final HashMap<UUID, BukkitTask> timers = new HashMap<>();

    @EventHandler(ignoreCancelled = true)
    public void onSendMessage(KMSendMessageEvent e) {
        String message = LanguageManager.cGet(LanguageString.GENERAL_PREFIX) + e.getMessage();
        e.getPlayer().sendMessage(LegacyComponentSerializer.legacySection().deserialize(message));
    }

    @EventHandler(ignoreCancelled = true)
    public void onActionBarMessage(KMSendActionBarMessageEvent e) {
        Player player = e.getPlayer();
        UUID playerId = player.getUniqueId();
        BukkitTask previousTask = timers.remove(playerId);
        if (previousTask != null) {
            previousTask.cancel();
        }

        Component message = LegacyComponentSerializer.legacySection().deserialize(e.getMessage());
        player.sendActionBar(message);
        if (e.getDuration() <= 1) {
            return;
        }

        BukkitTask task = new BukkitRunnable() {
            private int remaining = e.getDuration() - 1;

            @Override
            public void run() {
                player.sendActionBar(message);
                if (--remaining == 0) {
                    cancel();
                    timers.remove(playerId);
                }
            }
        }.runTaskTimer(BukkitMain.getInstance(), 20L, 20L);
        timers.put(playerId, task);
    }

    public static void process(Player player, String message) {
        KMPlayer kmp = KMPlayerManager.getKMPlayer(player);
        PluginManager pm = Bukkit.getPluginManager();

        if (kmp == null || !kmp.isEnableMessages()) {
            return;
        }

        switch (DefaultConfig.getMessageMethod()) {
            case DISABLED:              return;

            case ACTION_BAR:            pm.callEvent(new KMSendActionBarMessageEvent(player, message, 3));
                                        break;

            case CHAT:                  pm.callEvent(new KMSendMessageEvent(player, message));
                                        break;
        }
    }

}
