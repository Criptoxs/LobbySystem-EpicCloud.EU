package net.CloudEXE.lobby.listeners;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.bridge.event.bukkit.BukkitPlayerUpdateEvent;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.player.permission.PermissionGroup;
import net.CloudEXE.lobby.core.Core;
import net.CloudEXE.lobby.methoden.Inventorys;
import net.CloudEXE.lobby.methoden.JoinInventory;
import net.CloudEXE.lobby.methoden.Loadingdata;
import net.CloudEXE.lobby.methoden.Scoreboard;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Score;
import org.bukkit.util.Vector;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Events implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        try {

            Player p = event.getPlayer();

            if (event.getClickedBlock().getType() == Material.WALL_SIGN || event.getClickedBlock().getType() == Material.SIGN_POST && (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                Sign sign = (Sign) event.getClickedBlock().getState();

                if (sign.getLine(1).contains("lade Server...")) {
                    p.sendTitle("§clade Server...", "");
                }else if(sign.getLine(0).contains(sign.getLine(0))){
                    p.sendTitle("§8» §b" + sign.getLine(0) + " §a" + " §8┃ §b" + CloudAPI.getInstance().getServerInfo(sign.getLine(0)).getServerState() + "§8«","§a"+CloudAPI.getInstance().getServerInfo(sign.getLine(0)).getOnlineCount() + "§8/§c" + CloudAPI.getInstance().getServerInfo(sign.getLine(0)).getMaxPlayers());
                }
            }
        } catch (NullPointerException ex) {
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.getInventory().clear();
        p.setMaxHealth(6);
        p.setHealth(6);
        p.setGameMode(GameMode.ADVENTURE);
        e.setJoinMessage(null);
        new Scoreboard().setScoreboard(p);
        p.teleport(new Location(Bukkit.getWorld("world"), -1188.500, 168, 44.500));
        Loadingdata.Set(p);
        if(Core.vipsichtbar.contains(p.getName())) {
            return;
        }else if(Core.keinersichtbar.contains(p.getName())) {
            return;
        }else if(Core.allesichtbar.contains(p.getName())){
            return;
        }else {
            Core.allesichtbar.add(p.getName());
        }
        for(Player all : Bukkit.getOnlinePlayers()) {
            if(Core.keinersichtbar.contains(all.getName())) {
                all.hidePlayer(p);
            }else if(Core.vipsichtbar.contains(all.getName())) {
                if(p.hasPermission("lobby.vip")) {
                    all.showPlayer(p);
                }else{
                    all.hidePlayer(p);
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(Core.build.contains(p.getName())){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(p.getLocation().getY() <= 5){
            p.teleport(new Location(Bukkit.getWorld("world"), 22.500, 16, -33.500));
        }
        double x = p.getLocation().getX();
        double y = p.getLocation().getY();
        double z = p.getLocation().getZ();
        Location loc = new Location(p.getLocation().getWorld(), x, y, z);
        if (loc.getBlock().getType().equals(Material.GOLD_PLATE)) {
            Vector v = p.getLocation().getDirection().multiply(2.0D).setY(1.0D);
            p.setVelocity(v);
            p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 1, 90);
            p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 2);
        }
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(null);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        CloudPlayer playerWhereAmI = CloudAPI.getInstance().getOnlinePlayer(e.getPlayer().getUniqueId());
        PermissionGroup permissionGroup = playerWhereAmI.getPermissionEntity().getHighestPermissionGroup(CloudAPI.getInstance().getPermissionPool());
        e.setFormat(permissionGroup.getPrefix() + e.getPlayer().getName() + "§8: §7" + e.getMessage());
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();

        try {
            if(e.getCurrentItem().getItemMeta().getDisplayName().equals(" §8► §6Spawn" + " §8(§a" + CloudAPI.getInstance().getOnlineCount("Lobby") + "§8/§c250§8)")) {
                p.sendTitle("§8» §6Spawn §8«", "");
                p.setVelocity(p.getVelocity().add(new Vector(0,0.6,0)));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10,5));
                p.playSound(new Location(Bukkit.getWorld("world"), -1188.500, 168, 44.500), Sound.ENDERMAN_TELEPORT, 1, 1);
                p.teleport(new Location(Bukkit.getWorld("world"), -1188.500, 168, 44.500));
                Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
                    @Override
                    public void run() {
                        p.resetTitle();
                    }
                },15);
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(" §8► §cFFA" + " §8(§a" + CloudAPI.getInstance().getOnlineCount("FFA") + "§8/§c75§8)")) {
                p.sendTitle("§8» §6Spawn §8«", "");
                p.setVelocity(p.getVelocity().add(new Vector(0,0.6,0)));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10,5));
                p.playSound(new Location(Bukkit.getWorld("world"), -1188.500, 166, 133.500), Sound.ENDERMAN_TELEPORT, 1, 1);
                p.teleport(new Location(Bukkit.getWorld("world"), -1188.500, 166, 133.500));
                Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
                    @Override
                    public void run() {
                        p.resetTitle();
                    }
                },15);
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(" §8► §aAlle sichtbar")) {
                if(Core.allesichtbar.contains(p.getName())) {
                    p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
                }else {
                    Core.allesichtbar.add(p.getName());
                    if(Core.vipsichtbar.contains(p.getName())) {
                        Core.vipsichtbar.remove(p.getName());
                    }else if(Core.keinersichtbar.contains(p.getName())) {
                        Core.keinersichtbar.remove(p.getName());
                    }
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 80, 1);
                    p.setVelocity(p.getVelocity().add(new Vector(0,0.6,0)));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10,5));
                    p.sendTitle("§8» §aAlle sichtbar§8 «", "");

                    ItemStack pla = new ItemStack(351, 1, (short) 10);
                    ItemMeta metpla = pla.getItemMeta();
                    metpla.setDisplayName("§8► §aAlle sichtbar §8┃ §7Rechtsklick §8◄");
                    pla.setItemMeta(metpla);
                    for(Player all : Bukkit.getOnlinePlayers()){
                        p.showPlayer(all);
                    }
                    p.getInventory().setItem(2, pla);
                    Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
                        @Override
                        public void run() {
                            p.resetTitle();
                        }
                    },15);
                }
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(" §8► §5VIPs sichtbar")) {
                if(Core.vipsichtbar.contains(p.getName())) {
                    p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
                }else {
                    Core.vipsichtbar.add(p.getName());

                    if(Core.allesichtbar.contains(p.getName())) {
                        Core.allesichtbar.remove(p.getName());
                    }else if(Core.keinersichtbar.contains(p.getName())) {
                        Core.keinersichtbar.remove(p.getName());
                    }

                    for(Player all : Bukkit.getOnlinePlayers()) {
                        if(all.hasPermission("lobby.vip")) {
                            p.showPlayer(all);
                        }else{
                            p.hidePlayer(all);
                        }
                    }
                    p.closeInventory();
                    ItemStack plv = new ItemStack(351, 1, (short) 5);
                    ItemMeta metplv = plv.getItemMeta();
                    metplv.setDisplayName("§8► §5VIPs sichtbar §8┃ §7Rechtsklick §8◄");
                    plv.setItemMeta(metplv);
                    p.getInventory().setItem(2, plv);
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 80, 1);
                    p.setVelocity(p.getVelocity().add(new Vector(0,0.6,0)));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10,5));
                    p.sendTitle("§8» §5VIPs sichtbar §8«", "");
                    Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
                        @Override
                        public void run() {
                            p.resetTitle();
                        }
                    },15);
                }
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(" §8► §7Keine sichtbar")) {
                if(Core.keinersichtbar.contains(p.getName())) {
                    p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
                }else {
                    if(Core.vipsichtbar.contains(p.getName())) {
                        Core.vipsichtbar.remove(p.getName());
                    }else if(Core.allesichtbar.contains(p.getName())) {
                        Core.allesichtbar.remove(p.getName());
                    }

                    Core.keinersichtbar.add(p.getName());
                    p.closeInventory();
                    ItemStack plv = new ItemStack(351, 1, (short) 8);
                    ItemMeta metplv = plv.getItemMeta();
                    metplv.setDisplayName("§8► §7Keine sichtbar §8┃ §7Rechtsklick §8◄");
                    plv.setItemMeta(metplv);
                    p.getInventory().setItem(2, plv);
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 80, 1);
                    p.setVelocity(p.getVelocity().add(new Vector(0,0.6,0)));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10,5));
                    p.sendTitle("» §7Keine sichtbar «", "");
                    for(Player all : Bukkit.getOnlinePlayers()){
                        p.hidePlayer(all);
                    }
                    Bukkit.getScheduler().runTaskLater(Core.getPlugin(Core.class), new Runnable() {
                        @Override
                        public void run() {
                            p.resetTitle();
                        }
                    },15);
                }
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals( " §8► §a" + "Lobby-1"  + " §8(§a" + CloudAPI.getInstance().getServerInfo("Lobby-1").getOnlineCount() + "§8/§c" + CloudAPI.getInstance().getServerInfo("Lobby-1").getMaxPlayers() + "§8)")) {
                if(CloudAPI.getInstance().getServerId().equals("Lobby-1")) {
                    p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
                }else{
                    tpServer(p, "Lobby-1");
                }
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals( " §8► §a" + "Lobby-2"  + " §8(§a" + CloudAPI.getInstance().getServerInfo("Lobby-2").getOnlineCount() + "§8/§c" + CloudAPI.getInstance().getServerInfo("Lobby-2").getMaxPlayers() + "§8)")) {
                if(CloudAPI.getInstance().getServerId().equals("Lobby-2")) {
                    p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
                }else{
                    tpServer(p, "Lobby-2");
                }
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals( " §8► §a" + "Lobby-3"  + " §8(§a" + CloudAPI.getInstance().getServerInfo("Lobby-3").getOnlineCount() + "§8/§c" + CloudAPI.getInstance().getServerInfo("Lobby-3").getMaxPlayers() + "§8)")) {
                if(CloudAPI.getInstance().getServerId().equals("Lobby-3")) {
                    p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
                }else{
                    tpServer(p, "Lobby-3");
                }
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals( " §8► §a" + "Lobby-4"  + " §8(§a" + CloudAPI.getInstance().getServerInfo("Lobby-4").getOnlineCount() + "§8/§c" + CloudAPI.getInstance().getServerInfo("Lobby-4").getMaxPlayers() + "§8)")) {
                if(CloudAPI.getInstance().getServerId().equals("Lobby-4")) {
                    p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
                }else{
                    tpServer(p, "Lobby-4");
                }
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals( " §8► §a" + "Lobby-5"  + " §8(§a" + CloudAPI.getInstance().getServerInfo("Lobby-5").getOnlineCount() + "§8/§c" + CloudAPI.getInstance().getServerInfo("Lobby-5").getMaxPlayers() + "§8)")) {
                if(CloudAPI.getInstance().getServerId().equals("Lobby-5")) {
                    p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
                }else{
                    tpServer(p, "Lobby-5");
                }
            }
        }catch (Exception e1) {
            return;
        }
    }

    @EventHandler
    public void onSwitchSlot(PlayerItemHeldEvent e){
        e.getPlayer().playSound(e.getPlayer().getLocation(),Sound.NOTE_STICKS,2,1);
    }

    @EventHandler
    public void onInteract2(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        try {
            if(e.getAction().equals(Action.PHYSICAL) && e.getClickedBlock().getType().equals(Material.SOIL)){

                e.setCancelled(true);

            }
            if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
                if(e.getItem().getItemMeta().getDisplayName().equals("§8► §bNavigator §8┃ §7Rechtsklick §8◄")) {
                    Inventorys.getNavigator(p);

                }else if(e.getItem().getItemMeta().getDisplayName().equals("§8► §3LobbySwitcher §8┃ §7Rechtsklick §8◄")) {
                    Inventorys.getLobbyswitcher(p);

                }else if(e.getItem().getItemMeta().getDisplayName().equals("§8► §cProfil §8┃ §7Rechtsklick §8◄")){
                    Inventorys.getProfil(p);

                }else if(e.getItem().getItemMeta().getDisplayName().equals("§8► §aAlle sichtbar §8┃ §7Rechtsklick §8◄")){
                    Inventorys.getVerstecker(p);

                }else if(e.getItem().getItemMeta().getDisplayName().equals("§8► §5VIPs sichtbar §8┃ §7Rechtsklick §8◄")){
                    Inventorys.getVerstecker(p);

                }else if(e.getItem().getItemMeta().getDisplayName().equals("§8► §7Keine sichtbar §8┃ §7Rechtsklick §8◄")) {
                    Inventorys.getVerstecker(p);

                }
            }
        }catch (Exception e1) {
            return;
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        if(Core.build.contains(p.getName())){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventory(PlayerInteractEvent e){
        try{
            if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){

            }
        }catch (Exception b){

        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        Player p2 = (Player) e.getRightClicked();
        if(e.getPlayer().getType().equals(EntityType.PLAYER)) {
            e.setCancelled(true);
            Inventorys.getProfile(p, p2);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if(Core.build.contains(p.getName())){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(Core.build.contains(p.getName())){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }

    public static void tpServer(Player p, String Server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("Connect");
            out.writeUTF(Server);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        p.sendPluginMessage(Core.getPlugin(Core.class), "BungeeCord", b.toByteArray());
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e){
        Player p = e.getPlayer();
        if(Core.build.contains(p.getName())){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }
}
