package pedrxd.survival;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;
import pedrxd.survival.commands.*;
import pedrxd.survival.listeners.OnChat;
import pedrxd.survival.listeners.OnCommandExecute;
import pedrxd.survival.listeners.OnEntityDamage;
import pedrxd.survival.listeners.OnInventory;
import pedrxd.survival.listeners.OnPlayer;


public class Manager extends JavaPlugin {

	public static PluginFile home;
	public static PluginFile config;
	public static PluginFile lang;
	public static TitleManagerAPI tapi;
	


	@Override
	public void onEnable() {
		tapi = (TitleManagerAPI) Bukkit.getServer().getPluginManager().getPlugin("TitleManager");
		registerEvent();
		configLoad();
		registerCommand();
	}


	public void registerEvent() {

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new OnChat(), this);
		pm.registerEvents(new OnInventory(), this);
		pm.registerEvents(new OnEntityDamage(), this);
		pm.registerEvents(new OnCommandExecute(), this);
		pm.registerEvents(new OnPlayer(), this);


	}

	public void registerCommand() {

		getCommand("oi").setExecutor(new CommandOi());
		getCommand("home").setExecutor(new CommandHome());
		getCommand("econfig").setExecutor(new CommandEconfig());
		getCommand("mute").setExecutor(new CommandMute());
		getCommand("creative").setExecutor(new CommandCreative());
		getCommand("survival").setExecutor(new CommandSurvival());
		getCommand("adventure").setExecutor(new CommandAdventure());
		getCommand("spec").setExecutor(new CommandSpectator());
		getCommand("shot").setExecutor(new CommandShot(this));
		getCommand("slap").setExecutor(new CommandShot(this));
		getCommand("night").setExecutor(new CommandNight());
		getCommand("day").setExecutor(new CommandDay());
		getCommand("send").setExecutor(new CommandSend(this));
		getCommand("sendaccept").setExecutor(new CommandSend(this));
		getCommand("senddeny").setExecutor(new CommandSend(this));
		getCommand("tpa").setExecutor(new CommandTpResquest(this));
		getCommand("tpaccept").setExecutor(new CommandTpResquest(this));
		getCommand("tpdeny").setExecutor(new CommandTpResquest(this));


	}

	public void configLoad() {

		config = new PluginFile(this, "config.yml", "config.yml");
		home = new PluginFile(this, "home.yml");
		lang = new PluginFile(this, "lang.yml", "lang.yml");

	}
	public static String getLang(String index){
		return ChatColor.translateAlternateColorCodes('&', lang.getString(index));
	}
	
	public static boolean isNumeric(String str)  
	{
		try  
	  {  
	    Double.parseDouble(str);  
	  }catch(NumberFormatException nfe){  
	    return false;  
	  }  
	  return true;  
	}
}
