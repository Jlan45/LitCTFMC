package litctf.ctfminecraftmaven;

import litctf.commands.*;
import litctf.listeners.AllListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CTFminecraftMaven extends JavaPlugin {

    @Override
    public void onLoad() {
        Bukkit.getConsoleSender().sendMessage("§f<§5CTFminecraft§f>§e插件加载了");
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginCommand("howtogetflag").setExecutor(new HowtogetFlag());
        Bukkit.getPluginCommand("flag").setExecutor(new Flagcmd());
        Bukkit.getPluginCommand("whohasflag").setExecutor(new WhohasFlag());
        Bukkit.getPluginCommand("login").setExecutor(new UserLoginCmd());
        Bukkit.getPluginCommand("register").setExecutor(new UserRegCmd());
        Bukkit.getPluginCommand("whataboutusers").setExecutor(new SeeUserStatus());

        Bukkit.getPluginManager().registerEvents(new AllListeners(), this);
        Bukkit.getConsoleSender().sendMessage("§f<§5CTFminecraft§f>§e插件启动了");
    }
    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§f<§5CTFminecraft§f>§e插件关闭了");
    }

}
