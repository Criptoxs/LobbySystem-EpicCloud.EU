package net.CloudEXE.lobby.methoden;

import net.CloudEXE.lobby.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Loadingdata {

    public static void Set(Player p){
        ItemStack plc = new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 14);
        ItemMeta metplc = plc.getItemMeta();
        metplc.setDisplayName("§8► §cLade Profil §8◄");
        plc.setItemMeta(metplc);

        p.getInventory().setItem(0, plc);
        p.getInventory().setItem(1, plc);
        p.getInventory().setItem(2, plc);
        p.getInventory().setItem(3, plc);
        p.getInventory().setItem(4, plc);
        p.getInventory().setItem(5, plc);
        p.getInventory().setItem(6, plc);
        p.getInventory().setItem(7, plc);
        p.getInventory().setItem(8, plc);

        Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
            @Override
            public void run() {
                p.getInventory().clear();
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 90);
                JoinInventory.Set(p);
            }
        },40);
    }
}
