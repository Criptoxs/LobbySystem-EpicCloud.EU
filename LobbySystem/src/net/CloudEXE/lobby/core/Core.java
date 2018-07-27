package net.CloudEXE.lobby.core;

import de.slikey.effectlib.EffectManager;
import net.CloudEXE.lobby.commands.BuildCMD;
import net.CloudEXE.lobby.listeners.Events;
import net.CloudEXE.lobby.methoden.Effecte;
import net.CloudEXE.lobby.methoden.Scoreboard;
import net.CloudEXE.lobby.mysql.Client;
import org.bukkit.Effect;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Core extends JavaPlugin {
    public static EffectManager effectManager;
    public static ArrayList<String> build = new ArrayList<String>();
    public static ArrayList<String> vipsichtbar = new ArrayList<String>();
    public static ArrayList<String> allesichtbar = new ArrayList<String>();
    public static ArrayList<String> keinersichtbar = new ArrayList<String>();
    public static String Prefix = "§8► §bEpic§3Cloud §8┃§7 ";

    @Override
    public void onEnable() {
        Client.connect();
        effectManager = new EffectManager(this);
        System.out.println("Das LobbySystem ist nun aktiviert!");
        new Scoreboard().startAnimation();
        Core.getPlugin(Core.class).getServer().getMessenger().registerOutgoingPluginChannel(Core.getPlugin(Core.class), "BungeeCord");
        Effecte.Spawn();
        getServer().getPluginManager().registerEvents(new Events(),this);
        getCommand("Build").setExecutor(new BuildCMD());
    }

    @Override
    public void onDisable() {
        Client.disconnect();
        System.out.println("Das LobbySystem ist nun deaktiviert!");
    }
}
