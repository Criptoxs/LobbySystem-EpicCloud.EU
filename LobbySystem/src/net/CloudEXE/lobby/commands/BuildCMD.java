package net.CloudEXE.lobby.commands;

import net.CloudEXE.lobby.core.Core;
import net.CloudEXE.lobby.methoden.JoinInventory;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCMD implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage("Du musst ein Spieler sein!");
			return true;
		}
		Player p = (Player) sender;
		if(!p.hasPermission("lobby.build")) {
			p.sendMessage(Core.Prefix + "§7This command was not found§8!");
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("build")) {
			if(Core.build.contains(sender.getName())){
				p.getInventory().clear();
				p.sendMessage(Core.Prefix + "§7Du bist nun §cnicht§7 mehr im Buildmodus§8!");
				Core.build.remove(p.getName());
				JoinInventory.Set(p);
				p.setGameMode(GameMode.ADVENTURE);
			}else{
				p.sendMessage(Core.Prefix + "§7Du bist §anun §7im Buildmodus§8!");
				p.setGameMode(GameMode.CREATIVE);
				p.getInventory().clear();
				Core.build.add(p.getName());
			}
		}
		
		return true;
		
	}

}
