package litctf.commands;

import com.alibaba.fastjson2.*;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import litctf.tools.Gamer;
import org.bukkit.entity.Player;

public class UserLoginCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        List<Gamer> gamers;
        Player player = (Player) sender;
        if (args.length!=1){
            player.sendMessage("宝贝写个密码就可以了");
            return false;
        }
        String password=args[0];
        try {
            gamers=Gamer.getGamers();
        } catch (IOException e) {
            try {
                FileWriter fileWriter = new FileWriter("gamers.json");
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                String jsonStr= "[]";
                bufferedWriter.write(jsonStr);
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException ee) {
                throw new RuntimeException(ee);
            }
            throw new RuntimeException(e);
        }
        for (Gamer gamer : gamers) {

            if (player.getName().equals(gamer.getName())){
                try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hashInBytes) {
                        sb.append(String.format("%02x", b));
                    }
                    String hash = sb.toString();
                    if (gamer.getPassword().equals(hash)) {
                        player.sendMessage("登录成功，欢迎来玩");
                        gamer.setLogin(true);
                        player.setGameMode(GameMode.SURVIVAL);
                        return true;
                    }
                    else {
                        player.sendMessage("密码不对诶，再试试吧");
                        return false;
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        }
        player.sendMessage("宝贝是不是考虑先注册一下再登录呢？");
        return false;
    }
}
