package litctf.commands;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import java.util.Random;

public class HowtogetFlag implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String str, String[] args) {
        sender.sendMessage("1、杀死铁傀儡\n" +
                "2、钻石似乎可以换来flag\n" +
                "3、服务器似乎开放了PVP，而且死亡掉落\n" +
                "4、可以当作OSINT来玩哦（暗示）\n" +
                "5、寻宝谁不喜欢呢\n" +
                "6、似乎乖乖升到30级也是个不错的选择");
        return true;
    }
    public static void getFlag(Player player){
        int fadeIn = 10; // 设置淡入时间（ticks）
        int stay = 70; // 设置停留时间（ticks）
        int fadeOut = 20; // 设置淡出时间（ticks）
        player.sendTitle("恭喜师傅获得§9§l§oFlag§r", "", fadeIn, stay, fadeOut); // 发送 Title
        Bukkit.broadcastMessage("恭喜 §6§l"+player.getName()+"§r 师傅获得§9§l§oFlag§r！！！");
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();
        meta.addPage("师傅好～师傅的flag是：LitCTF{TEST}");
        meta.addPage("希望师傅玩的开心\n————From J1an");
        meta.setAuthor("J§11§6a§5n§4");
        meta.setTitle("FlagBook");
        book.setItemMeta(meta);
        player.getInventory().addItem(book);
        spawnFirework(player);
    }
    public static void spawnFirework(Player player) {
        // 创建烟花效果
        Random random = new Random();

        Color color = Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        Color colorFade = Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));

        FireworkEffect effect = FireworkEffect.builder()
                .withColor(color)
                .withFade(colorFade)
                .with(FireworkEffect.Type.BALL_LARGE)
                .trail(true)
                .build();
        // 创建烟花实体
        Firework firework = (Firework) player.getWorld().spawn(player.getLocation(), Firework.class);
        FireworkMeta meta = firework.getFireworkMeta();
        meta.addEffect(effect);
        meta.setPower(1);
        firework.setFireworkMeta(meta);
    }

}