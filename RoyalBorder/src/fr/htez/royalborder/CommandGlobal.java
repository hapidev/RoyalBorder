package fr.htez.royalborder;

import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGlobal implements CommandExecutor {

	Main main;
	
	public CommandGlobal(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			
			Player player = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("rb") && args.length == 0) {
				if(player.hasPermission("rb.admin")) {
					player.chat("/rb help");
                    return true;
				}
				player.sendMessage(main.getPrefix() + main.getConfig().getString("messageNoPermission"));
				return false;
			}
			if(cmd.getName().equalsIgnoreCase("rb") && args != null && args.length == 1 && args[0].toString().equalsIgnoreCase("help")) {
				if(player.hasPermission("rb.admin")) {
					
					player.sendMessage("§7§m-----------------------------------------------");
                    player.sendMessage("§6                       §b* §6RoyalBorder §b*");
                    player.sendMessage("");
                    player.sendMessage("§b/rb help §8- §7 Plugin documentation");                   
                    player.sendMessage("§b/rb setworldborder <size> §8- §7 Set worldborder");
                    player.sendMessage("§b/rb delworldborder §8- §7 Remove worldborder");
                    player.sendMessage("§7§m-----------------------------------------------");
                    player.sendMessage("§7Besoin d'un site web sur mesure pour votre projet?");
                    player.sendMessage("§7Cliquez ici: " + main.convertToLink("§bwww.hapidev.fr", "https://www.hapidev.fr"));
					
                    return true;
				}
				player.sendMessage(main.getPrefix() + main.getConfig().getString("messageNoPermission"));
				return false;
			}
			if(cmd.getName().equalsIgnoreCase("rb") && args != null && args.length > 0 && args[0].toString().equalsIgnoreCase("setworldborder")) {
				if(player.hasPermission("rb.admin")) {
					
					if(args.length >= 2 && args[1] != null && args[1].length() > 0 && Integer.valueOf(args[1]) > 0 && main.isInteger(args[1])) {
						
						player.sendMessage(main.getPrefix() + main.getConfig().getString("messageWorldBorderCreated"));
						main.setWorldBorder(player.getLocation().getWorld().getName().toString(), player.getLocation().getBlockX(), player.getLocation().getBlockZ(), Integer.valueOf(args[1]));
						return true;
						
					}else {
						
						player.sendMessage(main.getPrefix() + main.getConfig().getString("messageUsage"));
						return false;
						
					}
					
					
				}
				player.sendMessage(main.getPrefix() + main.getConfig().getString("messageNoPermission"));
				return false;
			}
			if(cmd.getName().equalsIgnoreCase("rb") && args != null && args.length > 0 && args[0].toString().equalsIgnoreCase("delworldborder")) {
				if(player.hasPermission("rb.admin")) {

						player.sendMessage(main.getPrefix() + main.getConfig().getString("messageWorldBorderRemoved"));
						main.delWorldBorder(player.getLocation().getWorld().getName().toString());
						return true;

				}
				player.sendMessage(main.getPrefix() + main.getConfig().getString("messageNoPermission"));
				return false;
			}

			
			
			
			return false;
			
		}
		return false;
	}

}
