package snw.compasshere;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.LocationArgument;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Optional;

public final class Main extends JavaPlugin implements Listener {
    private Location target = null;

    @Override
    public void onEnable() {
        // Plugin startup logic

        new CommandAPICommand("compasshere")
                .executesPlayer(((sender, args) -> {
                    target = sender.getLocation();
                    Bukkit.getOnlinePlayers().forEach(IT -> IT.setCompassTarget(target));
                }))
                .register();
        new CommandAPICommand("compasshere")
                .withArguments(
                        new LocationArgument("location")
                )
                .executes(((sender, args) -> {
                    target = (Location) args[0];
                    Bukkit.getOnlinePlayers().forEach(IT -> IT.setCompassTarget(target));
                }))
                .register();

        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                Optional.ofNullable(target).ifPresent(player::setCompassTarget);
            }
        }.runTaskLater(this, 3L); // delay operation.
    }
}
