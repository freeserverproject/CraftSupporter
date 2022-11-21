package pro.freeserver.plugin.alphakun.craftsupporter.init

import pro.freeserver.plugin.alphakun.craftsupporter.CraftSupporter.Companion.plugin
import pro.freeserver.plugin.alphakun.craftsupporter.classes.Ingredient
import pro.freeserver.plugin.alphakun.craftsupporter.classes.Racipe

class LoadRecipe {
    fun loadRecipe() {
        val config = plugin.config
        val recipeList = config.getConfigurationSection("recipe")?.getKeys(false)
        if (recipeList != null) {
            for (recipe in recipeList) {

                val itemName = config.getString("recipe.$recipe.item-name")
                val material = config.getString("recipe.$recipe.material")
                val amount = config.getInt("recipe.$recipe.amount")
                val lore = config.getStringList("recipe.$recipe.lore")
                val enchant = config.getStringList("recipe.$recipe.enchant")
                val customModelData = config.getInt("recipe.$recipe.custom-model-data")
                val shapeless = config.getBoolean("recipe.$recipe.is-shaped")
                val shape = config.getStringList("recipe.$recipe.shape")
                val ingredients = config.getConfigurationSection("recipe.$recipe.ingredients")?.getKeys(false)
                val ingredientsMap: MutableList<Ingredient> = mutableListOf()

                if (ingredients != null) {
                    for (ingredient in ingredients) {
                        val ingredientPath = "recipe.$recipe.ingredients.$ingredient."
                        ingredientsMap += Ingredient(
                            ingredient[0],
                            config.getString(ingredientPath+"material")?:return,
                            config.getInt(ingredientPath+"amount"),
                            config.getInt(ingredientPath+"custom-model-data")
                        )
                    }
                }

                try {
                    Racipe(recipe, itemName!!, material!!, amount, lore, enchant, customModelData, shapeless, shape, ingredientsMap).registerRecipe()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}