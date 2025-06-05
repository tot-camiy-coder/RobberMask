package lhost;

import lhost.listener.EventsListener;
import lhost.manager.PlayerManager;
import lhost.manager.RobberMask;
import lhost.globals.globals;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public final class plugin extends JavaPlugin
{
    public static globals gl = new globals();

    @Override
    public void onEnable()
    {
        saveDefaultConfig();

        globals.mask_material = Material.matchMaterial(getConfig().getString("mask-material", "BLACK_WOOL"));
        globals.mask_desc = getConfig().getString("mask-desc");
        globals.mask_name = getConfig().getString("mask-name");

        new RobberMask().initCraft(this);
        new PlayerManager().initTeams();
        getServer().getPluginManager().registerEvents(new EventsListener(), this);
    }

    @Override
    public void onDisable()
    {

    }
}
