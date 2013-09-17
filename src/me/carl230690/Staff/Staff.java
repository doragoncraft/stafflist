package me.carl230690.Staff;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Staff extends JavaPlugin
  implements Listener
{
  FileConfiguration conf;
  public String headLine;
  public String playersOnline;
  public String staffLine;
  public int maxPlayers;
  public String lastLine;


  public void onEnable()
  {
    this.conf = getConfig();
    File configFile = new File(getDataFolder(), "config.yml");

    if (configFile.exists()) {
      this.conf.options().copyDefaults(true);
      saveDefaultConfig();
    }

    this.headLine = this.conf.getString("headLine").replaceAll("&", "§");
    //this.playersOnline = this.conf.getString("playersOnline").replaceAll("&", "§").replaceAll("%online%", Bukkit.getOnlinePlayers()).replace("%max_online%", Bukkit.getMaxPlayers());
    this.lastLine = this.conf.getString("lastLine").replaceAll("&", "§");
  }

  public void onDisable()
  {
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if ((sender instanceof Player)) {
      Player player = (Player)sender;
      if (label.equalsIgnoreCase("who")) {
        if (player.hasPermission("staffonline.who")) {
          if (args.length == 0) {
        	  
        	  this.playersOnline = ChatColor.YELLOW+"Players Online"+ChatColor.GRAY+":"+ChatColor.WHITE+ChatColor.DARK_PURPLE+"("+ChatColor.LIGHT_PURPLE+ Bukkit.getOnlinePlayers().length+ChatColor.DARK_GRAY+"/"+ChatColor.LIGHT_PURPLE+Bukkit.getMaxPlayers()+ChatColor.DARK_PURPLE+")";
        	  StringBuilder staff = new StringBuilder();
        	  int i = 0;
        	  for(Player p : Bukkit.getOnlinePlayers()){
        	  if(p.hasPermission("staffmanager.online"))
        	  if(i==0){staff.append(" " + p.getName());
        		  }
        	  else{staff.append(" " + p.getName());
        	  i++;
        	  }
        	  }
        	  this.staffLine = ChatColor.RED+ "Staff Online"+ChatColor.DARK_GRAY+": "+ChatColor.RED + staff.toString();
            sendList(player);
            return true;	  
          }
        } else if (!player.hasPermission("staffonline.who"))
          player.sendMessage(ChatColor.DARK_RED + "You do not have permission for this command");
        return false;
      }
      else if (label.equalsIgnoreCase("list")) {
        if (player.hasPermission("staffonline.list")) {
          if (args.length == 0) {
        	  
        	  this.playersOnline = ChatColor.YELLOW+"Players Online"+ChatColor.GRAY+":"+ChatColor.WHITE+ChatColor.DARK_PURPLE+"("+ChatColor.LIGHT_PURPLE+ Bukkit.getOnlinePlayers().length+ChatColor.DARK_GRAY+"/"+ChatColor.LIGHT_PURPLE+Bukkit.getMaxPlayers()+ChatColor.DARK_PURPLE+")";
        	  StringBuilder staff = new StringBuilder();
        	  int i = 0;
        	  for(Player p : Bukkit.getOnlinePlayers()){
        	  if(p.hasPermission("staffmanager.online"))
        	  if(i==0){staff.append(" " + p.getName());
        		  }
        	  else{staff.append(" " + p.getName());
        	  i++;
        	  }
        	  }
        	  this.staffLine = ChatColor.RED+ "Staff Online"+ChatColor.DARK_GRAY+": "+ChatColor.RED + staff.toString();
        	   
            sendList(player);
            return true;
          }
        } else if (!player.hasPermission("staffonline.list"))
          player.sendMessage(ChatColor.DARK_RED + "You do not have permission for this command");
        return false;
      }
    else
    {
      sender.sendMessage("Player Command");
    }
    return false;
  }
    return false;
    }

  public String getStaff() {
    String staff = "";
    for (Player player : Bukkit.getOnlinePlayers()) {
      if (player.hasPermission("online.staff")) {
        staff = staff + ChatColor.DARK_AQUA + player.getName() + ", ";
      }
    }
    if (staff.length() > 4) {
      staff = staff.substring(0, staff.length() - 2);
    }
    if (staff.length() == 0) {
      staff = ChatColor.DARK_RED + "There are no staff online";
    }
    return staff;
  }

  public void sendList(Player p) {
    p.sendMessage(this.headLine);
    p.sendMessage(this.playersOnline);
    p.sendMessage(this.staffLine);
    p.sendMessage(this.lastLine);
  }
}