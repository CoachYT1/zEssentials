package it.coachyt.zessentials.commands;

import it.coachyt.zessentials.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class fly implements CommandExecutor {
    private final Main plugin = Main.getPlugin(Main.class);
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {

            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(player.hasPermission("essentials.fly")){
                    if(args.length==0) {
                        if(player.getAllowFlight()){
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.config.getString("messages.fly.disabled")).replace("%prefix%", Objects.requireNonNull(plugin.config.getString("messages.prefix")))));
                        }else{
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.config.getString("messages.fly.enabled")).replace("%prefix%", Objects.requireNonNull(plugin.config.getString("messages.prefix")))));
                        }
                        player.setAllowFlight(!player.getAllowFlight());
                    }else if(args.length==1){
                        if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("1")|| args[0].equalsIgnoreCase("yes")){
                            player.setAllowFlight(true);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.config.getString("messages.fly.enabled")).replace("%prefix%", Objects.requireNonNull(plugin.config.getString("messages.prefix")))));
                        }else if(args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("0")|| args[0].equalsIgnoreCase("no")){
                            player.setAllowFlight(false);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.config.getString("messages.fly.disabled")).replace("%prefix%", Objects.requireNonNull(plugin.config.getString("messages.prefix")))));
                        }else{
                            if(player.hasPermission("essentials.fly.other")) {
                                Player target = Bukkit.getPlayer(args[0]);
                                if (target != null) {
                                    if (target.getAllowFlight()) {
                                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.config.getString("messages.fly.disabled-other")).replace("%prefix%", Objects.requireNonNull(plugin.config.getString("messages.prefix"))).replace("%player%", target.getDisplayName())));
                                        target.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.config.getString("messages.fly.disabled-by-other")).replace("%prefix%", Objects.requireNonNull(plugin.config.getString("messages.prefix"))).replace("%player%", player.getDisplayName())));
                                    } else {
                                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.config.getString("messages.fly.enabled-other")).replace("%prefix%", Objects.requireNonNull(plugin.config.getString("messages.prefix"))).replace("%player%", target.getDisplayName())));
                                        target.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.config.getString("messages.fly.enabled-by-other")).replace("%prefix%", Objects.requireNonNull(plugin.config.getString("messages.prefix"))).replace("%player%", player.getDisplayName())));
                                    }
                                    target.setAllowFlight(!player.getAllowFlight());
                                } else {
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.config.getString("messages.player-not-found")).replace("%prefix%", Objects.requireNonNull(plugin.config.getString("messages.prefix"))).replace("%player%", args[0])));
                                }
                            }else{
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.config.getString("messages.no-perms")).replace("%prefix%", Objects.requireNonNull(plugin.config.getString("messages.prefix")))));
                            }
                        }
                    }
                }else{
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.config.getString("messages.no-perms")).replace("%prefix%", Objects.requireNonNull(plugin.config.getString("messages.prefix")))));
                }
            }else{
                sender.sendMessage("Only players can execute this command.");
            }
        });
        return false;
    }
}
