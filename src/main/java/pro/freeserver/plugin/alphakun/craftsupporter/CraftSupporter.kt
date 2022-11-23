package pro.freeserver.plugin.alphakun.craftsupporter

import org.bukkit.Bukkit.recipeIterator
import org.bukkit.Material
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
        for (r in recipes) {
            removeRecipe(r)
        }
    }

    companion object {
        lateinit var plugin: CraftSupporter
        var recipes: MutableList<Recipe> = mutableListOf()
    }

    private fun removeRecipe(recipe: Recipe) {
        var it = server.recipeIterator()
        while (it.hasNext()) {
            var itRecipe = it.next()
            if (itRecipe is ShapedRecipe && recipe is ShapedRecipe) {
                var m = itRecipe.ingredientMap
                var n = recipe.ingredientMap

                if (m.values.containsAll(n.values)) {
                    var itShape = itRecipe.shape
                    var itShapeString = itShape[0] + itShape[1] + itShape[2]
                    var shape = recipe.shape
                    var shapeString = shape[0] + shape[1] + shape[2]

                    for (i in itShapeString.indices) {
                        if (m[itShapeString[i]]!! != n[shapeString[i]]) {
                            return
                        }
                    }
                    it.remove()
                    return
                }
            }
        }
    }
}