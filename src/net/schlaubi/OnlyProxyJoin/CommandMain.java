package net.schlaubi.OnlyProxyJoin;

import java.io.File;
import java.io.IOException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandMain
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command name, String lable, String[] args)
  {
    if ((sender instanceof Player))
    {
      Player p = (Player)sender;
      if (p.hasPermission("opj.reload")) {
        if (args.length == 0)
        {
          p.sendMessage("§7[§3OnlyProxyJoin§7]§aCommands: \n §e/onlyproxyjoin reload");
        }
        else if ((args.length == 1) && (
          (args[0].equalsIgnoreCase("reload")) || (args[0].equalsIgnoreCase("rl"))))
        {
          File f = Main.getConfiguration();
          FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
          String rmsg = cfg.getString("ReloadMSG").replace("&", "§");
          try
          {
            cfg.save(f);
          }
          catch (IOException e)
          {
            e.printStackTrace();
          }
          p.sendMessage(rmsg);
        }
      }
    }
    return false;
  }
}
