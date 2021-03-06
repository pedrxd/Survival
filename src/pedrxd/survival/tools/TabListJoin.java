package pedrxd.survival.tools;

import java.util.HashMap;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import pedrxd.survival.Manager;


public class TabListJoin {
	public static HashMap<Player, Integer> editors = new HashMap<Player, Integer>();
	public static void setTabList(Player p){
		String header = ChatColor.translateAlternateColorCodes('&', Manager.config.getString("TabList.Header").replaceAll("%player", p.getName()));
		String footer = ChatColor.translateAlternateColorCodes('&', Manager.config.getString("TabList.Footer").replaceAll("%player", p.getName()));

		Manager.tapi.setHeaderAndFooter(p, header, footer);
	}
	public static void editTabMenu(Player p, int i){
		editors.put(p, i);
		p.closeInventory();
		if(i == 1){
			p.sendMessage(Manager.getLang("f1"));
		}if(i == 2){
			p.sendMessage(Manager.getLang("f2"));
		}
	}
	public static void onChat(AsyncPlayerChatEvent e){
		if(editors.containsKey(e.getPlayer())){
			if(editors.get(e.getPlayer()) == 1){
				Manager.config.set("TabList.Header", e.getMessage());
			}if(editors.get(e.getPlayer()) == 2){
				Manager.config.set("TabList.Footer", e.getMessage());
			}
			finishEdit(e.getPlayer());
			e.setCancelled(true);
		}
	}
	public static void finishEdit(Player p){
		p.openInventory(ConfigGui.tabMenuGui());
		for(Player pp : Bukkit.getOnlinePlayers()){
			setTabList(pp);
		}
		Manager.config.save();
		Manager.config.reload();
		editors.remove(p);
	}
}
