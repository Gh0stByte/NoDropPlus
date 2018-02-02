package com.gh0stbyte.NoDrop;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ME on 6/22/2016.
 */
public class MyListener implements Listener
{

    NoDrop plugin;
    public MyListener (NoDrop instance) {
        plugin = instance;
    }

    HashMap<String, ArrayList<ItemStack>> returnQueue = new HashMap<String, ArrayList<ItemStack>>();


    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent e) {
        //If Enabled
        if(plugin.getConfig().getBoolean("NoDropPlus.Enabled") && plugin.getConfig().getBoolean("NoDropPlus.KeepOnDeath"))
        {
            //Copy of items to drop on death
            final List<ItemStack> list = e.getDrops();

            //Items to return to player (temp)
            final ArrayList<ItemStack> items = new ArrayList<ItemStack>();

            //Array for the items no to drop (load from config)
            ArrayList<Material> noDrop = new ArrayList<Material>();

            //for each item in the list of items in the config
            for(String itemLine : plugin.getConfig().getStringList("NoDropPlus.Items")){
                //set that item to be a material
                Material item = Material.getMaterial(itemLine);
                //add it to the list to not drop
                noDrop.add(item);
            }

            //For each item in the items to drop
            for (ItemStack i : list) {
                //If the item type is a Diamond_pick
                if (noDrop.contains(i.getType())) {
                    //ADd it to the items var
                    items.add(i);
                }
            }
            //For each item that is getting returned
            for (ItemStack i2 : items) {
                //remove it from the drops
                e.getDrops().remove(i2);
            }
            //add the returned items to the returned queue
            this.returnQueue.put(e.getEntity().getPlayer().getName(), items);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        //If keep on respawn is enabled
        if(plugin.getConfig().getBoolean("NoDropPlus.Enabled") && plugin.getConfig().getBoolean("NoDropPlus.KeepOnDeath")) {
            //If the player has an entry in the return queue
            if (returnQueue.containsKey(event.getPlayer().getName())) {
                //for each of the items in their return queue
                for (ItemStack i : returnQueue.get(event.getPlayer().getName())) {
                    //give them the items
                    event.getPlayer().getInventory().addItem(i);
                }
                //remove items from queue once they have been given.
                returnQueue.remove(event.getPlayer().getName());
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        //if nodrop is enabled
        if(plugin.getConfig().getBoolean("NoDropPlus.Enabled")) {
            Player p = event.getPlayer();
            //if the player doesn't have bypass perm
            if (!p.hasPermission("nodropplus.bypass")) {
                //array for configured not allowed to drop blocks
                ArrayList<Material> noAllow = new ArrayList<Material>();
                //for each line in the config for items
                for(String itemLine : plugin.getConfig().getStringList("NoDropPlus.Items")){
                    //convert it to a material
                    Material item = Material.getMaterial(itemLine);
                    //add it to the not allowed array
                    noAllow.add(item);
                }
                //if the not allowed items has the item they are trying to drop
                if (noAllow.contains(event.getItemDrop().getItemStack().getType())) {
                    //cancel it
                    event.setCancelled(true);
                    //alert them that they are not allowed to drop it.
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("NoDropPlus.Message")));
                }
            }
        }
    }
}
