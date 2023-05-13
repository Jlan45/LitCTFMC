package litctf.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class WhohasFlag implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        sender.sendMessage("目前下列玩家背包中拥有Flag：\n");
        for (Player player : getPlayersWithItem(Material.WRITTEN_BOOK)) {
            sender.sendMessage("§e"+player.getName()+"\n");
        }
        sender.sendMessage("§r我可没让你抢别的师傅的Flag啊");
        return true;
    }
    public List<Player> getPlayersWithItem(Material item) {
        List<Player> players = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getInventory().contains(item)) {
                players.add(player);
                spawnExampleEffect(player);
            }
        }
        return players;
    }
    public void spawnExampleEffect(Player player) {
        Particle[] particles = {
                Particle.FIREWORKS_SPARK,
                Particle.HEART,
                Particle.CLOUD,
                Particle.SMOKE_NORMAL,
                Particle.CRIT_MAGIC
        };
        Particle particle = particles[(int) (Math.random() * particles.length)];
        player.spawnParticle(particle, player.getLocation(), 10, 0.5, 0.5, 0.5, 0.1);
    }

}
