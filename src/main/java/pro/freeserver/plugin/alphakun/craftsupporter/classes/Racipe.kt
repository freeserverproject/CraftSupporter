package pro.freeserver.plugin.alphakun.craftsupporter.classes

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import pro.freeserver.plugin.alphakun.craftsupporter.CraftSupporter.Companion.plugin

class Racipe(recipeName: String, itemName: String, material: String, amount: Int, lore: List<String>, enchant: List<String>?, customModelData: Int, shapeless: Boolean, shape: List<String>, ingredients: Map<Char, String>) {

    private var recipeName: NamespacedKey
    private var itemAmount: Int
    private var itemMaterial: Material
    private var itemLore: List<String>
    private var itemEnchant: List<Enchantment> = listOf()
    private var itemResult: ItemStack? = null
    private var isShaped: Boolean = false
    private var shape: List<String>
    private var ingredients: Map<Char, String>


    init {
        this.isShaped = shapeless
        this.itemAmount = amount
        this.itemMaterial = Material.getMaterial(material)?:Material.STONE
        this.itemLore = lore
        this.recipeName = NamespacedKey(plugin, recipeName)
        this.shape = shape
        this.ingredients = ingredients
        if (enchant != null) {
           for (e in enchant) {
               val enchantData = Enchantment.getByKey(NamespacedKey.minecraft(e))
               if (enchantData != null) {
                   itemEnchant += enchantData
               }
           }
        }
        this.itemResult = ItemStackAPI(itemMaterial, itemAmount, itemName?:"", itemLore, itemEnchant, customModelData).getItemStack()
    }

    fun registerData() {
        var recipe: Recipe
        if(isShaped) {
            recipe = itemResult?.let { ShapedRecipe(recipeName, it) }!!
            recipe as ShapedRecipe
            for (i in ingredients) {
                recipe.setIngredient(i.key, Material.getMaterial(i.value)?:Material.STONE)
            }
            recipe.shape(shape[0], shape[1], shape[2])
        } else {
            recipe = itemResult?.let { ShapelessRecipe(recipeName, it) }!!
            recipe as ShapelessRecipe
            for (i in ingredients) {
                recipe.addIngredient(Material.getMaterial(i.value)?:Material.STONE)
            }
        }
    }
}