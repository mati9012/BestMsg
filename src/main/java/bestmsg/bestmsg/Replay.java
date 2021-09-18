package bestmsg.bestmsg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class Replay implements CommandExecutor {
    Main plugin;
    public Replay(Main m) {
        plugin = m;
        m.getCommand("r").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        UUID puuid = p.getUniqueId();

        if(args.length >= 1) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < args.length; i++) {
                sb.append(args[i]).append(" ");
            }
            String msg = sb.toString();

            Player cel = Bukkit.getPlayer(plugin.getConfig().getString(puuid + ".lastPlayer"));

            UUID celuuid = cel.getUniqueId();

            List<String> celIgnored = plugin.getConfig().getStringList(celuuid + ".ignored");

            if(!celIgnored.contains(p.getDisplayName())) {
                p.sendMessage("§8[§eyou §7-> §6" + cel.getDisplayName() + "§8] §e" + msg);
                cel.sendMessage("§8[§e" + p.getDisplayName() + "§6 -> you§8] " + msg);
            }else {
                p.sendMessage(ChatColor.GRAY + "You cannot send messages to this player!");
            }
        }else {
            p.sendMessage(ChatColor.DARK_RED + "Usage: / msg [player] [message]");
        }
        return false;
    }
}
