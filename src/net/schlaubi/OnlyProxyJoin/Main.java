package net.schlaubi.OnlyProxyJoin;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main
  extends JavaPlugin
{
  public void onEnable()
  {
    Bukkit.getConsoleSender().sendMessage("§7[§OnlyProxyJoin§7]§f Enabled OnlyProxyJoin by Schlaubi");
    registerEvents();
    registerCommands();
    loadConfig();
  }
  
  public void onDisable()
  {
    Bukkit.getConsoleSender().sendMessage("§7[§3OnlyProxyJoin§7]§f Disabled OnlyProxyJoin by Schlaubi");
  }
  
  public void loadConfig()
  {
    File f = new File("plugins/OnlyProxyJoin", "Config.yml");
    FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
    String s = cfg.getString("ProxyIP");
    if (s == null) {
      cfg.set("ProxyIP", "127.0.0.1");
    }
    String s1 = cfg.getString("KickMSG");
    if (s1 == null) {
      cfg.set("KickMSG", "&cBitte Joine §ber den PROXY");
    }
    String s2 = cfg.getString("BypassPermission");
    if (s2 == null) {
      cfg.set("BypassPermission", "opj.bypass");
    }
    String s3 = cfg.getString("ReloadMSG");
    if (s3 == null) {
      cfg.set("ReloadMSG", "&7[&3OnlyProxyJoin&7] Reloaded");
    }
    try
    {
      cfg.save(f);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  private void registerEvents()
  {
    Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
  }
  
  private void registerCommands()
  {
    getCommand("onlyproxyjoin").setExecutor(new CommandMain());
  }
  
  public static File getConfiguration()
  {
    File f = new File("plugins/OnlyProxyJoin", "Config.yml");
    return f;
  }
}
