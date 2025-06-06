package lhost.manager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class PlayerManager
{
    public void acceptItemToHead(Player player, ItemStack item)
    {
        PlayerInventory inv = player.getInventory();
        ItemStack helmet = inv.getHelmet();
        if (helmet != null) return;
        if (inv.getItemInMainHand().equals(item))
        {
            inv.setHelmet(item);
            inv.remove(inv.getItemInMainHand());
            maskStatusUpdate(player, true);
            return;
        }
        else if (inv.getItemInOffHand().equals(item))
        {
            inv.setHelmet(item);
            inv.remove(inv.getItemInOffHand());
            maskStatusUpdate(player, true);
            return;
        }
    }

    public void maskUpdateMain(Player p)
    {
        PlayerInventory inv = p.getInventory();

        if (Objects.equals(inv.getHelmet(), new RobberMask().getItem()))
        {
            maskStatusUpdate(p, true);
        }
        else
        {
            maskStatusUpdate(p, false);
        }
    }

    private void maskStatusUpdate(Player player, boolean status)
    {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getTeam("hiddenName");
        if (team == null) initTeams();
        if (status)
        {
            if (team != null && !team.hasEntry(player.getName()))
                team.addEntry(player.getName());
        }
        else
        {
            if (team != null && team.hasEntry(player.getName()))
                team.removeEntry(player.getName());
        }
    }

    public void initTeams()
    {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getTeam("hiddenName");
        if (team == null)
        {
            team = scoreboard.registerNewTeam("hiddenName");
            team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        }
    }

}
