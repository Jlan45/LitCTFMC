package litctf.listeners;

import litctf.commands.HowtogetFlag;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class AllListeners implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //获取玩家ID并发送欢迎消息
        Player player = event.getPlayer();
        String playerName = player.getName().toString();
        event.setJoinMessage(null);
        player.sendMessage("欢迎师傅参加LitCTF\n如果师傅没注册的话记得用/register命令注册哦\n注册完也不要忘记登录一下哦\n玩的开心哟～～～");
        Bukkit.broadcastMessage("可爱的 §e§l"+playerName+"§r 师傅来啦♪（＾∀＾●）ﾉｼ （●´∀｀）♪");
        int fadeIn = 10; // 设置淡入时间（ticks）
        int stay = 70; // 设置停留时间（ticks）
        int fadeOut = 20; // 设置淡出时间（ticks）
        player.sendTitle("欢迎师傅参加LitCTF", "PS：听说J1an在找男朋友，快点帮帮他", fadeIn, stay, fadeOut); // 发送 Title
        player.setGameMode(GameMode.SPECTATOR);

    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Bukkit.broadcastMessage("wwwww §e§l"+event.getPlayer().getName()+"§r 师傅跑路了 <(｀^′)>");
    }
    @EventHandler
    public void onPlayerLevelChange(PlayerLevelChangeEvent event){
        Player player = event.getPlayer();
        if(player.getLevel()==30){
            HowtogetFlag.getFlag(player);
        }
    }
    @EventHandler
    public void onIronmanDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof IronGolem) { // 判断实体是否为铁傀儡
            IronGolem ironGolem = (IronGolem) entity;
            EntityDamageEvent lastDamageCause = ironGolem.getLastDamageCause();
            if (lastDamageCause instanceof EntityDamageByEntityEvent) {
                EntityDamageByEntityEvent damageEvent = (EntityDamageByEntityEvent) lastDamageCause;
                Entity damager = damageEvent.getDamager();
                if (damager instanceof Player) { // 判断攻击者是否为玩家
                    Player player = (Player) damager;
                    HowtogetFlag.getFlag(player);
                }
            }
        }
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        // 检查玩家是否需要禁止移动，如果需要，则取消玩家移动
        if (needStopPlayerMove(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    // 自定义方法，根据需要禁止移动的条件判断玩家是否需要禁止移动
    private boolean needStopPlayerMove(Player player) {
        if(player.getGameMode()==GameMode.SPECTATOR){
            return true;
        }
        // 在这里添加你的禁止移动逻辑
        return false; // 模拟需要禁止移动的情况
    }

}