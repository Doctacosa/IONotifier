package com.interordi.ionotifier;

import org.bukkit.plugin.java.JavaPlugin;


public class IONotifier extends JavaPlugin {

	public void onEnable() {
		getLogger().info("IONotifier enabled");
		
		//Once the server is running, check for new notifications every minute
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new NotificationsReader(this), 30*20L, 60*20L);
	}
	
	
	public void onDisable() {
		getLogger().info("IONotifier disabled");
	}
}
