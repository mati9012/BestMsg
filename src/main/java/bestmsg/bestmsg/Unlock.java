package bestmsg.bestmsg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Unlock implements CommandExecutor {
    Main plugin;

    public Unlock(Main m) {
        plugin = m;
        m.getCommand("unlock").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        UUID puuid = p.getUniqueId();
        if (args.length == 1) {
            Player cel = Bukkit.getPlayerExact(args[0]);
            List<String> ignored = plugin.getConfig().getStringList(puuid + ".ignored");
            if (ignored == null) {
                ignored = new ArrayList<String>();

            }
            if (ignored.contains(cel.getDisplayName())) {
                ignored.remove(cel.getDisplayName());
                plugin.getConfig().set(puuid + ".ignored", ignored);
                plugin.saveConfig();
                p.sendMessage(ChatColor.DARK_RED + "You removed a player " + ChatColor.YELLOW + cel.getDisplayName() + ChatColor.DARK_RED + " from the ignored list!");

            } else {
                p.sendMessage(ChatColor.DARK_RED + "This player is not on the ignore list!");
            }

        }else {
            p.sendMessage(ChatColor.DARK_RED + "Usage: / unlock [Player]");
        }
        return false;
    }
}