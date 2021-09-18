package bestmsg.bestmsg;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main main;
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();

        main = this;
        new Ignore(this);
        new Msg(this);
        new Replay(this);
        new Unlock(this);

    }
    public static Main getMain() {
        return main;
    }


}
