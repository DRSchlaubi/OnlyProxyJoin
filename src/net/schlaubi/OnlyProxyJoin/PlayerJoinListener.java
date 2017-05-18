package net.schlaubi.OnlyProxyJoin;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener
  implements Listener
{
  @EventHandler
  public void onJoin(PlayerJoinEvent e)
  {
    Player p = e.getPlayer();
    Bukkit.getConsoleSender().sendMessage(p.getAddress().toString());
    File f = Main.getConfiguration();
    FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
    String ip = cfg.getString("ProxyIP");
    String msg = cfg.getString("KickMSG").replace("&", "�");
    String per = cfg.getString("BypassPermission");
    if ((p.getAddress().toString() != ip) && 
      (!p.hasPermission(per))) {
      p.kickPlayer(msg);
    }
  }
}
