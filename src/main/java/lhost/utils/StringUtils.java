package lhost.utils;

import org.bukkit.ChatColor;

public class StringUtils
{
    public static String getStr(String string)
    {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
