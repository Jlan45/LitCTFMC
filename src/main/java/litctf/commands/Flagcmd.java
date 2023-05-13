package litctf.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Flagcmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        if (sender.isOp()){
            Player player=(Player)sender;
            sender.sendMessage("OP大人，已经将您的Flag放入背包了");
            HowtogetFlag.getFlag(player);
        }else {
            sender.sendMessage("只有OP才能这样获得flag诶");
        }
        return true;
    }
}