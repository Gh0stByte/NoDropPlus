package com.gh0stbyte.NoDrop;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ME on 6/21/2016.
 */
public class NoDrop extends JavaPlugin {
    @Override
    public void onEnable() {
        //Load up config and save it to the variable.
        getConfig().options().copyDefaults(true);
        saveConfig();
        getServer().getPluginManager().registerEvents(new MyListener(this), this);
        getLogger().info("Plugin Enabled! NoDropPlus by Gh0stByte of Gh0stByte.ga");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Disabled! NoDropPlus by Gh0stByte of Gh0stByte.ga");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("nodropplus")) {
            if (args.length == 0) {
                sender.sendMessage("§7[§3NoDropPlus§7] §3NoDropPlus - Created by Gh0stByte of Gh0stByte.ga");
                if(sender.hasPermission("nodropplus.reload")) {
                    sender.sendMessage("§7[§3NoDropPlus§7] §3Commands - /nodropplus reload §6(Reloads Config)");
                }
                return true;
            }
            if (args.length == 1)  {
                if(args[0].equalsIgnoreCase("reload")) {
                    if(sender.hasPermission("nodropplus.reload")) {
                        reloadConfig();
                        sender.sendMessage("§7[§3NoDropPlus§7] §3Reloaded NoDropPlus config!");
                    } else {
                        sender.sendMessage("§7[§3NoDropPlus§7] §3NoDropPlus - Created by Gh0stByte of Gh0stByte.ga");
                    }
                } else {
                    if (sender.hasPermission("nodropplus.reload")) {
                        sender.sendMessage("§7[§3NoDropPlus§7] §3Correct usage: /nodropplus reload");
                    } else {
                        sender.sendMessage("§7[§3NoDropPlus§7] §3NoDropPlus - Created by Gh0stByte of Gh0stByte.ga");
                    }
                }
            }
        }
        return true;
    }
}