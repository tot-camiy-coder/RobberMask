package lhost.manager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import lhost.globals.globals;
import java.util.List;

import static lhost.utils.StringUtils.getStr;

public class RobberMask
{
    public ItemStack getItem()
    {
        ItemStack item = new ItemStack(globals.mask_material); // any material
        ItemMeta meta = item.getItemMeta();
        if (meta != null)
        {
            meta.setDisplayName(getStr(globals.mask_name));
            meta.setLore(List.of(getStr(globals.mask_desc)));
            item.setItemMeta(meta);
        }
        return item;
    }

    public void initCraft(Plugin plugin)
    {
        ItemStack item = getItem();
        NamespacedKey key = new NamespacedKey(plugin, plugin.getConfig().getString("craft-key", "robber_mask"));
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        List<String> shape = plugin.getConfig().getStringList("shape");
        recipe.shape(shape.toArray(new String[0]));

        ConfigurationSection ingredients = plugin.getConfig().getConfigurationSection("ingredients");
        if (ingredients != null)
        {
            for (String _char : ingredients.getKeys(false))
            {
                String stringMat = ingredients.getString(_char);
                Material mat = Material.matchMaterial(stringMat);
                if (mat != null)
                {
                    recipe.setIngredient(_char.charAt(0), mat);
                }
                else
                {
                    plugin.getLogger().severe("[manager->RobberMask.java] - Unknown material: "+stringMat+" in ingredients!");
                }
            }
        }
        Bukkit.addRecipe(recipe);
    }

}
