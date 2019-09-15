package fr.htez.royalborder;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Main extends JavaPlugin{
	
	public String version;
	
	@Override
    public void onEnable() {		
		saveDefaultConfig();
		System.out.println("[RoyalBorder] The hamster is turning the wheel...");
        System.out.println("[RoyalBorder] Configuration file loaded.");
        
        PluginManager pm = getServer().getPluginManager();
        getCommand("rb").setExecutor(new CommandGlobal(this));
        
        version = Bukkit.getBukkitVersion();
        System.out.println("[RoyalBorder] Your server version is " + version);		
	}	
	public void onDisable() {        
		System.out.println("[RoyalBorder] Bye :)");		
    }	
	public String getPrefix() {
        return "§7[§6Royal§eBorder§7]§r ";
    }
	public String convertToLink(String text, String link) {
        TextComponent messageComponent = new TextComponent(text);
        messageComponent.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link));
        String returnMessage = messageComponent.toLegacyText();
        return returnMessage;
    }
	public boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	public void setWorldBorder(String world, Integer x, Integer z, Integer size) {
		WorldBorder wb = Bukkit.getWorld(world).getWorldBorder();
		wb.setCenter(x,z);
		wb.setSize(size);
		wb.setDamageAmount(1);
	}
	public void delWorldBorder(String world) {
		WorldBorder wb = Bukkit.getWorld(world).getWorldBorder();
		wb.reset();
	}

}
