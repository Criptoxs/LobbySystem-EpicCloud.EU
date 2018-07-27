package net.CloudEXE.lobby.methoden;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.player.permission.PermissionGroup;
import net.CloudEXE.lobby.core.Core;
import net.CloudEXE.lobby.mysql.Manager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

public class Scoreboard {

	private static Integer animationCount;
	private String[] animation = new String[]{
			" ","§8► ","§8► §bE","§8► §bEp","§8► §bEpi","§8► §bEpic","§8► §bEpic§3C","§8► §bEpic§3Cl","§8► §bEpic§3Clo","§8► §bEpic§3Clou","§8► §bEpic§3Cloud","§8► §bEpic§3Cloud§8.","§8► §bEpic§3Cloud§8.§3E","§8► §bEpic§3Cloud§8.§3EU","§8► §bEpic§3Cloud§8.§3EU ","§8► §bEpic§3Cloud§8.§3EU §8◄","§8► §bEpic§3Cloud§8.§3EU §8◄","§8► §bEpic§3Cloud§8.§3EU §8◄","§8► §bEpic§3Cloud§8.§3EU §8◄"
	};

	public void setScoreboard(Player p) {
		org.bukkit.scoreboard.Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Team punkte = board.registerNewTeam("coins");
		Team ran = board.registerNewTeam("ran");
		Objective obj = board.registerNewObjective("aaa", "bbb");

		CloudPlayer playerWhereAmI = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());
		PermissionGroup permissionGroup = playerWhereAmI.getPermissionEntity().getHighestPermissionGroup(CloudAPI.getInstance().getPermissionPool());

		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(animation[animationCount]);
		obj.getScore("§e§r§l").setScore(13);
		obj.getScore("§4●► §7Rang").setScore(12);
		obj.getScore("§8").setScore(11);
		obj.getScore("§a§b").setScore(10);
		obj.getScore("§b●► §7Coins").setScore(9);
		obj.getScore("§e").setScore(8);
		obj.getScore("§e§2").setScore(7);
		obj.getScore("§a●► §7Server").setScore(6);
		obj.getScore(" §8» §a" + CloudAPI.getInstance().getServerId()).setScore(5);
		obj.getScore("§e§r").setScore(4);
		obj.getScore("§3●► §7Teamspeak").setScore(3);
		obj.getScore(" §8» §3EpicCloud§8.§3EU").setScore(2);
		obj.getScore("").setScore(1);

		punkte.addEntry("§e");
		punkte.setPrefix(" §8» §b" + Manager.getCoins(p.getName()));

		ran.addEntry("§8");
		ran.setPrefix(" §8» " + permissionGroup.getDisplay() + permissionGroup.getName());

		p.setScoreboard(board);
	}

	public static void updateScoreboard(Player p) {
		if (p.getScoreboard() == null)
			new Scoreboard().setScoreboard(p);

		CloudPlayer playerWhereAmI = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());
		PermissionGroup permissionGroup = playerWhereAmI.getPermissionEntity().getHighestPermissionGroup(CloudAPI.getInstance().getPermissionPool());
		org.bukkit.scoreboard.Scoreboard board= p.getScoreboard();
		Team coins = board.getTeam("coins");
		Team ran = board.getTeam("ran");
		ran.setPrefix(" §8» " + permissionGroup.getDisplay() + permissionGroup.getName());
		coins.setPrefix(" §8» §b" + Manager.getCoins(p.getName()));
	}

	public void startAnimation(){
		animationCount = 0;
		Bukkit.getScheduler().runTaskTimer(Core.getPlugin(Core.class), new Runnable() {
			@Override
			public void run() {
				for(Player all : Bukkit.getOnlinePlayers()){
					if(all.getScoreboard() == null)
						setScoreboard(all);
					updateScoreboard(all);
					all.setLevel(CloudAPI.getInstance().getOnlineCount());
					all.setExp((float) CloudAPI.getInstance().getOnlineCount() / (float) 150);
					Bukkit.getWorld("world").setTime(1000);
					all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(animation[animationCount]);
				}

				animationCount++;

				if(animationCount == animation.length)
					animationCount = 0;


			}
		}, 0, 15);
	}
}
