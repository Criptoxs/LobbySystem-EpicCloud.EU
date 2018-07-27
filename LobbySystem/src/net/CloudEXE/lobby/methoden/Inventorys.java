package net.CloudEXE.lobby.methoden;

import de.dytanic.cloudnet.api.CloudAPI;
import net.CloudEXE.lobby.core.Core;
import net.CloudEXE.lobby.mysql.Manager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class Inventorys {
    public static void getLobbyswitcher(Player p) {

        Inventory menu = Bukkit.createInventory(null, InventoryType.HOPPER, "§8┃ §3LobbySwitcher §8◄");

        if(CloudAPI.getInstance().getServerInfo("Lobby-1") == null) {
            Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
                @Override
                public void run() {
                    ServerItemOffline("Lobby-1", 0, menu);
                    p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 2);
                }
            },2);
        }else {
            Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
                @Override
                public void run() {
                    ServerItemOnline("Lobby-1", 0, menu);
                    p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 2);
                }
            },2);
        }

        if(CloudAPI.getInstance().getServerInfo("Lobby-2") == null) {
            Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
                @Override
                public void run() {
                    ServerItemOffline("Lobby-2", 1, menu);
                    p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 4);
                }
            },4);
        }else {
            Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
                @Override
                public void run() {
                    ServerItemOnline("Lobby-2", 1, menu);
                    p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 4);
                }
            },4);
        }

        if(CloudAPI.getInstance().getServerInfo("Lobby-3") == null) {
            Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
                @Override
                public void run() {
                    ServerItemOffline("Lobby-3", 2, menu);
                    p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 6);
                }
            },6);
        }else {
            Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
                @Override
                public void run() {
                    ServerItemOnline("Lobby-3", 2, menu);
                    p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 6);
                }
            },6);
        }

        if(CloudAPI.getInstance().getServerInfo("Lobby-4") == null) {
            Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
                @Override
                public void run() {
                    ServerItemOffline("Lobby-4", 3, menu);
                    p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 8);
                }
            },8);
        }else {
            Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
                @Override
                public void run() {
                    ServerItemOnline("Lobby-4", 3, menu);
                    p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 8);
                }
            },8);
        }

        if(CloudAPI.getInstance().getServerInfo("Lobby-5") == null) {
            Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
                @Override
                public void run() {
                    ServerItemOffline("Lobby-5", 4, menu);
                    p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 10);
                }
            },10);
        }else {
            Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
                @Override
                public void run() {
                    ServerItemOnline("Lobby-5", 4, menu);
                    p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 10);
                }
            },10);
        }

        p.openInventory(menu);
    }

    public static void ServerItemOnline(String server,int slot, Inventory menu) {
        ArrayList<String> lore = new ArrayList<>();
        ItemStack nav = new ItemStack(351, CloudAPI.getInstance().getServerInfo(server).getOnlineCount(), (short) 10);
        ItemMeta metnav = nav.getItemMeta();
        if(CloudAPI.getInstance().getServerId().equals(server)) {
            metnav.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
            metnav.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        metnav.setDisplayName(" §8► §a" + server  + " §8(§a" + CloudAPI.getInstance().getServerInfo(server).getOnlineCount() + "§8/§c" + CloudAPI.getInstance().getServerInfo(server).getMaxPlayers() + "§8)");
        nav.setItemMeta(metnav);

        menu.setItem(slot, nav);
    }

    public static void ServerItemOffline(String server,int slot, Inventory menu) {
        ArrayList<String> lore = new ArrayList<>();
        ItemStack nav = new ItemStack(351, 1, (short) 8);
        ItemMeta metnav = nav.getItemMeta();
        metnav.setDisplayName(" §8► §a" + server  + " §8(§4Offline§8)");
        nav.setItemMeta(metnav);

        menu.setItem(slot, nav);
    }

    public static void getNavigator(Player p) {

        Inventory Nav = Bukkit.createInventory(null, 3*9, "§8┃ §bNavigator §8◄");

        ArrayList<String> lore = new ArrayList<>();

        ItemStack spawn = new ItemStack(Material.NETHER_STAR, CloudAPI.getInstance().getOnlineCount("Lobby"));
        ItemMeta metspawn = spawn.getItemMeta();
        metspawn.setDisplayName(" §8► §6Spawn" + " §8(§a" + CloudAPI.getInstance().getOnlineCount("Lobby") + "§8/§c250§8)");
        spawn.setItemMeta(metspawn);

        ItemStack buildffa = new ItemStack(Material.SANDSTONE, 0);
        ItemMeta metbuildffa = buildffa.getItemMeta();
        metbuildffa.setDisplayName(" §8► §cBuildFFA"  + " §8(§4Offline§8)");
        buildffa.setItemMeta(metbuildffa);

        int bw = CloudAPI.getInstance().getOnlineCount("BW-4x4") + CloudAPI.getInstance().getOnlineCount("BW-2x2") + CloudAPI.getInstance().getOnlineCount("BW-2x1");
        ItemStack bedwars = new ItemStack(Material.BED, bw);
        ItemMeta metbedwars = bedwars.getItemMeta();
        metbedwars.setDisplayName(" §8► §cBedWars" + " §8(§4Offline§8)");
        bedwars.setItemMeta(metbedwars);

        ItemStack knockpvp = new ItemStack(Material.STICK, 0);
        ItemMeta metknockpvp = knockpvp.getItemMeta();
        metknockpvp.setDisplayName(" §8► §cKnockPVP" + " §8(§4Offline§8)");
        metknockpvp.addEnchant(Enchantment.ARROW_FIRE, 1, true);
        metknockpvp.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        knockpvp.setItemMeta(metknockpvp);

        ItemStack skywars = new ItemStack(Material.GOLD_SWORD, CloudAPI.getInstance().getOnlineCount("FFA"));
        ItemMeta metskywars = skywars.getItemMeta();
        metskywars.setDisplayName(" §8► §cFFA" + " §8(§a" + CloudAPI.getInstance().getOnlineCount("FFA") + "§8/§c75§8)");
        metskywars.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        skywars.setItemMeta(metskywars);

        ItemStack freebuild = new ItemStack(Material.IRON_SPADE, 0);
        ItemMeta metfreebuild = freebuild.getItemMeta();
        metfreebuild.setDisplayName(" §8► §cCityBuild" + " §8(§4Offline§8)");
        metfreebuild.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        freebuild.setItemMeta(metfreebuild);

        ItemStack nothing = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta nothingm = nothing.getItemMeta();
        nothingm.setDisplayName(" ");
        nothing.setItemMeta(nothingm);

        for(int i = 0; i != Nav.getSize(); i++) {
            Nav.setItem(i, nothing);
        }

        Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
            @Override
            public void run() {
                Nav.setItem(4, spawn);
                p.playSound(p.getLocation(), Sound.FIREWORK_BLAST, 1, 1);
            }
        },12);

        Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
            @Override
            public void run() {
                Nav.setItem(11, buildffa);
                p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 1);
            }
        },2);

        Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
            @Override
            public void run() {
                Nav.setItem(12, bedwars);
                p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 1);
            }
        },4);

        Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
            @Override
            public void run() {
                Nav.setItem(13, knockpvp);
                p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 1);
            }
        },6);

        Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
            @Override
            public void run() {
                Nav.setItem(14, skywars);
                p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 1);
            }
        },8);

        Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
            @Override
            public void run() {
                Nav.setItem(15, freebuild);
                p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 1);
            }
        },10);

        p.openInventory(Nav);
    }

    public static void getExtras(Player p) {

        Inventory Nav = Bukkit.createInventory(null, 3*9, "§8┃ §eExtras §8◄");

        ArrayList<String> lore = new ArrayList<>();

        ItemStack fr = new ItemStack(397, 1, (short) 3);
        SkullMeta metfr = (SkullMeta) fr.getItemMeta();
        metfr.setDisplayName(" §8► §4Friend add");
        metfr.setOwner(p.getName());
        fr.setItemMeta(metfr);

        ItemStack sta = new ItemStack(Material.ENDER_PEARL, 1);
        ItemMeta metsta = sta.getItemMeta();
        metsta.setDisplayName(" §8► §5Party invite");
        metsta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        sta.setItemMeta(metsta);

        ItemStack nothing = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta nothingm = nothing.getItemMeta();
        nothingm.setDisplayName(" ");
        nothing.setItemMeta(nothingm);

        for(int i = 0; i != Nav.getSize(); i++) {
            Nav.setItem(i, nothing);
        }

        p.openInventory(Nav);
    }

    public static void setItem(String name, int slot, Player p, Material material) {
        ArrayList<String> lore = new ArrayList<>();
        ItemStack nav = new ItemStack(material);
        ItemMeta metnav = nav.getItemMeta();
        metnav.setDisplayName(name);
        nav.setItemMeta(metnav);

        p.getInventory().setItem(slot, nav);

    }

    public static void getProfile(Player p, Player klicker) {

        Inventory Nav = Bukkit.createInventory(null, 3*9, "§8┃ §cProfil §8- §c" + klicker.getName() + " §8◄");

        ArrayList<String> lore = new ArrayList<>();

        ItemStack ex = new ItemStack(Material.BOWL, 1);
        ItemMeta metex = ex.getItemMeta();
        metex.setDisplayName(" §8► §eStatus");
        lore.add(" §8» §a" + Manager.getStatus(klicker));
        metex.setLore(lore);
        ex.setItemMeta(metex);

        ItemStack fr = new ItemStack(397, 1, (short) 3);
        SkullMeta metfr = (SkullMeta) fr.getItemMeta();
        metfr.setDisplayName(" §8► §4Friend add");
        metfr.setOwner(p.getName());
        fr.setItemMeta(metfr);

        ItemStack sta = new ItemStack(Material.ENDER_PEARL, 1);
        ItemMeta metsta = sta.getItemMeta();
        metsta.setDisplayName(" §8► §5Party invite");
        metsta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        sta.setItemMeta(metsta);

        ItemStack nothing = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta nothingm = nothing.getItemMeta();
        nothingm.setDisplayName(" ");
        nothing.setItemMeta(nothingm);

        for(int i = 0; i != Nav.getSize(); i++) {
            Nav.setItem(i, nothing);
        }

        Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
            @Override
            public void run() {
                Nav.setItem(11, ex);
                p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 1);
            }
        },2);

        Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
            @Override
            public void run() {
                Nav.setItem(13, fr);
                p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 1);
            }
        },4);

        Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
            @Override
            public void run() {
                Nav.setItem(15, sta);
                p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 1);
            }
        },6);

        p.openInventory(Nav);
    }

    public static void getProfil(Player p) {

        Inventory Nav = Bukkit.createInventory(null, 3*9, "§8┃ §cProfil §8◄");

        ArrayList<String> lore = new ArrayList<>();

        ItemStack ex = new ItemStack(Material.CHEST, 1);
        ItemMeta metex = ex.getItemMeta();
        metex.setDisplayName(" §8► §eExtras");
        ex.setItemMeta(metex);

        ItemStack fr = new ItemStack(397, 1, (short) 3);
        SkullMeta metfr = (SkullMeta) fr.getItemMeta();
        metfr.setDisplayName(" §8► §cFriends");
        metfr.setOwner(p.getName());
        fr.setItemMeta(metfr);

        ItemStack sta = new ItemStack(Material.ENDER_PEARL, 1);
        ItemMeta metsta = sta.getItemMeta();
        metsta.setDisplayName(" §8► §6Stats");
        metsta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        sta.setItemMeta(metsta);

        ItemStack nothing = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta nothingm = nothing.getItemMeta();
        nothingm.setDisplayName(" ");
        nothing.setItemMeta(nothingm);

        for(int i = 0; i != Nav.getSize(); i++) {
            Nav.setItem(i, nothing);
        }

        Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
            @Override
            public void run() {
                Nav.setItem(11, ex);
                p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 1);
            }
        },2);

        Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
            @Override
            public void run() {
                Nav.setItem(13, fr);
                p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 1);
            }
        },4);

        Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
            @Override
            public void run() {
                Nav.setItem(15, sta);
                p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 1);
            }
        },6);

        p.openInventory(Nav);
    }

    public static void getVerstecker(Player p) {

        Inventory Nav = Bukkit.createInventory(null, InventoryType.BREWING, "§8┃ §eVerstecker §8◄");

        ArrayList<String> lore = new ArrayList<>();

        ItemStack pla = new ItemStack(351, 1, (short) 10);
        ItemMeta metpla = pla.getItemMeta();
        metpla.setDisplayName(" §8► §aAlle sichtbar");
        if(Core.allesichtbar.contains(p.getName())) {
            metpla.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
            metpla.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        pla.setItemMeta(metpla);

        ItemStack plv = new ItemStack(351, 1, (short) 5);
        ItemMeta metplv = plv.getItemMeta();
        metplv.setDisplayName(" §8► §5VIPs sichtbar");
        if(Core.vipsichtbar.contains(p.getName())) {
            metplv.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
            metplv.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        plv.setItemMeta(metplv);

        ItemStack plk = new ItemStack(351, 1, (short) 8);
        ItemMeta metplk = plk.getItemMeta();
        metplk.setDisplayName(" §8► §7Keine sichtbar");
        if(Core.keinersichtbar.contains(p.getName())) {
            metplk.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
            metplk.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        plk.setItemMeta(metplk);

        ItemStack nothing = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta nothingm = nothing.getItemMeta();
        nothingm.setDisplayName(" ");
        nothing.setItemMeta(nothingm);

        for(int i = 0; i != Nav.getSize(); i++) {
            Nav.setItem(i, nothing);
        }

        Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
            @Override
            public void run() {
                Nav.setItem(0, pla);
                p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 1);
            }
        },2);

        Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
            @Override
            public void run() {
                Nav.setItem(1, plv);
                p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 1);
            }
        },4);

        Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
            @Override
            public void run() {
                Nav.setItem(2, plk);
                p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 1);
            }
        },6);

        p.openInventory(Nav);
    }
}
