# IONotifier

This Bukkit plugin reads any messages present in the file `notifications.txt` and displays them as-is in the server's chat. If multiple lines are present, they will be displayed one after the other, at a preset interval.

The file is checked every minute for new content. Once text is prepped to be ready, the file is emptied to prevent repeated outputs.
