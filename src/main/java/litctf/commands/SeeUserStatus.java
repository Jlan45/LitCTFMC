package litctf.commands;

import com.alibaba.fastjson2.JSON;
import litctf.tools.Gamer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SeeUserStatus implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String str, String[] args) {
        if (sender.isOp()) {
            List<Gamer> gamers;
            try {
                FileReader reader = new FileReader("gamers.json");
                BufferedReader bufferedReader = new BufferedReader(reader);
                String json = bufferedReader.readLine();
                bufferedReader.close();
                reader.close();
                gamers = JSON.parseArray(json, Gamer.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (Gamer gamer : gamers) {
                sender.sendMessage(gamer.getName() + " " + gamer.getPassword() + " " + gamer.getLogin().toString());
            }
        }
        return true;
    }
}
