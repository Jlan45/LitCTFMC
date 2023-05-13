package litctf.commands;

import litctf.tools.Gamer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserRegCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        List<Gamer> gamers;
        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage("宝贝写个密码就可以了");
            return false;
        }
        String password = args[0];
        try {
            gamers = Gamer.getGamers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Gamer gamer : gamers) {
            if (player.getName().equals(gamer.getName())) {
                player.sendMessage("注册完了搁这造孽是吧？");
                return false;
            }
        }
        String hash;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            hash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Gamer gamer = new Gamer(player.getName(), hash);
        gamer.setLogin(true);
        gamers.add(gamer);
        try {
            Gamer.saveGamers(gamers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        player.sendMessage("师傅注册好啦，开心玩耍吧");
        return true;
    }
}
