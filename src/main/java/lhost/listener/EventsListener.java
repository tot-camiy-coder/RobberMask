package lhost.listener;

import lhost.manager.PlayerManager;
import lhost.manager.RobberMask;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventsListener implements Listener
{

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) // click left click in air
    {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR))
        {
            new PlayerManager().acceptItemToHead(e.getPlayer(), new RobberMask().getItem());
            return;
        }
    }

    // inventory handlers
    @EventHandler
    public void onPlayerOpenInv(InventoryInteractEvent e)
    {
        if (!(e.getWhoClicked() instanceof Player player)) return;

        new PlayerManager().maskUpdateMain(player);
        return;
    }
    @EventHandler
    public void onPlayerOpenInventory(InventoryOpenEvent e)
    {
        if (!(e.getPlayer() instanceof Player player)) return;

        new PlayerManager().maskUpdateMain(player);
        return;
    }
    @EventHandler
    public void onPlayerCloseInventory(InventoryCloseEvent e)
    {
        if (!(e.getPlayer() instanceof Player player)) return;

        new PlayerManager().maskUpdateMain(player);
        return;
    }

}
