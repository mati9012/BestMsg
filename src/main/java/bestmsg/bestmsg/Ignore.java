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

public class Ignore implements CommandExecutor {
    Main plugin;
    public Ignore(Main m) {
        plugin = m;
        m.getCommand("ignore").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        UUID puuid = p.getUniqueId();
        if(args.length == 1) {
            Player cel = Bukkit.getPlayerExact(args[0]);
            List<String> ignored = plugin.getConfig().getStringList(puuid + ".ignored");
            if(ignored == null) {
                ignored = new ArrayList<String>();

            }
            if(!ignored.contains(cel.getDisplayName())) {
                ignored.add(cel.getDisplayName());
                plugin.getConfig().set(puuid + ".ignored", ignored);
                plugin.saveConfig();
                p.sendMessage(ChatColor.DARK_RED + "You have added a player " + ChatColor.YELLOW + cel.getDisplayName() + ChatColor.DARK_RED + " to the ignore list!");

            }else {
                p.sendMessage(ChatColor.DARK_RED + "This player is already on the ignore list!");
            }
        }else {
            p.sendMessage(ChatColor.DARK_RED + "Usage: /ignore [Gracz]");
        }
        return false;
    }
}
