package pro.freeserver.plugin.alphakun.craftsupporter.classes

import org.bukkit.Material

class Ingredient(ingredientKey: Char, material: String, amount: Int, customModelData: Int) {
    private var ingredientmKey: Char
    private var material: String
    private var amount: Int
    private var customModelData: Int

    init {
        this.ingredientmKey = ingredientKey
        this.material = material
        this.amount = amount
        this.customModelData = customModelData
    }

    fun getIngredientKey(): Char {
        return ingredientmKey
    }

    fun getMaterial(): Material? {
        return Material.getMaterial(this.material)
    }

    fun getAmount(): Int {
        return amount
    }

    fun getCustomModelData(): Int {
        return customModelData
    }
}