package net.CloudEXE.lobby.methoden;

import de.dytanic.cloudnet.api.CloudAPI;
import javafx.print.PageLayout;
import net.minecraft.server.v1_8_R3.ItemSkull;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class JoinInventory {

    public static void Set(Player p){
        setItem("§8► §bNavigator §8┃ §7Rechtsklick §8◄",1, p,Material.COMPASS);
        setItem("§8► §3LobbySwitcher §8┃ §7Rechtsklick §8◄",7, p,Material.NETHER_STAR);
        setItem("§8► §cProfil §8┃ §7Rechtsklick §8◄",6, p,Material.ENDER_CHEST);

        ItemStack plc = new ItemStack(351, 1, (short) 10);
        ItemMeta metplc = plc.getItemMeta();
        metplc.setDisplayName("§8► §aAlle sichtbar §8┃ §7Rechtsklick §8◄");
        plc.setItemMeta(metplc);
        p.getInventory().setItem(2, plc);
    }

    public static void setItem(String name, int slot, Player p, Material material) {
        ArrayList<String> lore = new ArrayList<>();
        ItemStack nav = new ItemStack(material);
        ItemMeta metnav = nav.getItemMeta();
        metnav.setDisplayName(name);
        nav.setItemMeta(metnav);

        p.getInventory().setItem(slot, nav);

    }
}
