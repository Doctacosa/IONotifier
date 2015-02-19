package com.interordi.ionotifier;

import java.util.Queue;



public class NotificationsDisplay implements Runnable {
	
	private IONotifier plugin;
	private Queue<String> notifications;
	private int taskId;
	
	
	public NotificationsDisplay(IONotifier plugin, Queue<String> notifications) {
		this.plugin = plugin;
		this.notifications = notifications;
	}
	
	
	public void startRun() {
		taskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this, 0L, 3*20L);
	}
	
	
	public void endRun() {
		plugin.getServer().getScheduler().cancelTask(this.taskId);
	}
	
	
	public void run() {
		plugin.getServer().broadcastMessage(notifications.poll());
		if (notifications.isEmpty())
			endRun();
	}
}
