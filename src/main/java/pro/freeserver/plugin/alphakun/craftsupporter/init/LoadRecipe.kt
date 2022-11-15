package pro.freeserver.plugin.alphakun.craftsupporter.init

import pro.freeserver.plugin.alphakun.craftsupporter.CraftSupporter.Companion.plugin
import pro.freeserver.plugin.alphakun.craftsupporter.classes.Racipe

class LoadRecipe {
    fun loadRecipe() {
        val config = plugin.config
        var recipeList = config.getConfigurationSection("recipe")?.getKeys(false)
        if (recipeList != null) {
            for (recipe in recipeList) {
                var itemName = config.getString("recipe.$recipe.item-name")
                var material = config.getString("recipe.$recipe.material")
                var amount = config.getInt("recipe.$recipe.amount")
                var lore = config.getStringList("recipe.$recipe.lore")
                var enchant = config.getStringList("recipe.$recipe.enchant")
                var customModelData = config.getInt("recipe.$recipe.custom-model-data")
                var shapeless = config.getBoolean("recipe.$recipe.is-shaped")
                var shape = config.getStringList("recipe.$recipe.shape")
                var ingredients = config.getConfigurationSection("recipe.$recipe.ingredients")?.getKeys(false)
                var ingredientsMap = mutableMapOf<Char, String>()
                if (ingredients != null) {
                    for (ingredient in ingredients) {
                        ingredientsMap[ingredient[0]] = config.getString("recipe.$recipe.ingredients.$ingredient")!!
                    }
                }
                try {
                    print("RegisterdRecipeName: " + recipe)
                    Racipe(recipe, itemName!!, material!!, amount, lore, enchant, customModelData, shapeless, shape, ingredientsMap).registerData()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}