package pro.freeserver.plugin.alphakun.craftsupporter

import org.bukkit.Bukkit.recipeIterator
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin
import pro.freeserver.plugin.alphakun.craftsupporter.init.LoadRecipe

class CraftSupporter : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        plugin = this
        this.saveDefaultConfig()
        LoadRecipe().loadRecipe()
    }

    override fun onDisable() {
        // Plugin shutdown logic
        removeRecipe()
    }

    companion object {
        lateinit var plugin: CraftSupporter
        var recipes: MutableList<NamespacedKey> = mutableListOf()
    }

    private fun removeRecipe() {
        for (r in recipes) {
            if (server.removeRecipe(r)) {
                println("Recipe: " + r.key + "is now removed!!")
            }
        }
    }
}