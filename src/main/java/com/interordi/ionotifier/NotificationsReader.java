package com.interordi.ionotifier;

import java.util.Queue;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;

import com.interordi.ionotifier.IONotifier;


public class NotificationsReader implements Runnable {
	
	private IONotifier plugin;
	private String file = "plugins/IONotifier/notifications.txt";
	
	
	public NotificationsReader(IONotifier plugin) {
		this.plugin = plugin;
	}
	
	
	//Read notifications
	public void run() {
		
		Queue<String> notifications = new LinkedList<String>();

		//Get the current notifications from the text file, if any
		FileReader input = null;
		try {
			input = new FileReader(file);
		} catch (FileNotFoundException e) {
			plugin.getLogger().info("Notifications file not found!");
			return;
		}
		
		//Read the content of the file
		try {
			BufferedReader buffer = new BufferedReader(input);
			String line = buffer.readLine();
			while (line != null) {
				notifications.add(line);
				line = buffer.readLine();
			}

			buffer.close();
			
		} catch (IOException e) {
			plugin.getLogger().info("Failed to read the notifications file");
			return;
		}
		
		//Empty the notifications file
		try {
			FileOutputStream erasor = new FileOutputStream(file);
			erasor.write(new String().getBytes());
			erasor.close();
		} catch (Exception e) {
			plugin.getLogger().info("Failed to empty the notifications file");
		}
		
		//Display to the players the list of notifications
		if (!notifications.isEmpty()) {
			NotificationsDisplay task = new NotificationsDisplay(plugin, notifications);
			task.startRun();
		}
	}
}
