package pro.freeserver.plugin.alphakun.craftsupporter.classes

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import pro.freeserver.plugin.alphakun.craftsupporter.CraftSupporter.Companion.plugin
import java.lang.NumberFormatException

class Racipe(recipeName: String, itemName: String?, material: String?, amount: Int?, lore: List<String>?, enchant: List<String>?, customModelData: Int?, shapeless: Boolean, shape: List<String>, ingredients: List<Ingredient>) {

    private var recipeName: NamespacedKey
    private var itemAmount: Int?
    private var itemMaterial: Material
    private var itemLore: List<String>?
    private var itemEnchant: MutableMap<Enchantment, Int> = mutableMapOf()
    private var itemResult: ItemStack? = null
    private var isShaped: Boolean = false
    private var shape: List<String>
    private var ingredients: List<Ingredient>

    init {
        this.isShaped = shapeless
        this.itemAmount = amount
        this.itemMaterial = Material.getMaterial(material?:"STONE")?:Material.STONE
        this.itemLore = lore
        this.recipeName = NamespacedKey(plugin, recipeName)
        this.shape = shape
        this.ingredients = ingredients
        // エンチャントを整形
        if (enchant != null) {
           for (e in enchant) {
               var enchantRawList = e.split(":")
               var enchantName = enchantRawList[0]
               var enchantLevel = 0
               try {
                   enchantLevel = enchantRawList[1].toInt()
               } catch (e: NumberFormatException) {
                   enchantLevel = 1
               }
               val enchantData = Enchantment.getByKey(NamespacedKey.minecraft(enchantName))
               if (enchantData != null) {
                   itemEnchant[enchantData] = enchantLevel
               }
           }
        }
        // 結果アイテムのインスタンスを作成
        this.itemResult = ItemStackAPI(itemMaterial, itemAmount, itemName, itemLore, itemEnchant, customModelData).getItemStack()
    }

    // 材料の設定、レシピの登録
    fun registerRecipe() {
        var recipe: Recipe
        var item: ItemStackAPI
        if(isShaped) {
            recipe = itemResult?.let { ShapedRecipe(recipeName, it) }!!
            recipe as ShapedRecipe
            recipe.shape(shape[0], shape[1], shape[2])
            for (i in ingredients) {
                item = ItemStackAPI(material =  i.getMaterial()?:Material.STONE ,amount= i.getAmount(), customModelData = i.getCustomModelData())
                recipe.setIngredient(i.getIngredientKey(), item.getItemStack())
            }
        } else {
            recipe = itemResult?.let { ShapelessRecipe(recipeName, it) }!!
            recipe as ShapelessRecipe
            for (i in ingredients) {
                item = ItemStackAPI(material =  i.getMaterial()?:Material.STONE ,amount= i.getAmount(), customModelData = i.getCustomModelData())
                recipe.addIngredient(item.getItemStack())
            }
        }
        if (Bukkit.addRecipe(recipe)) {
            println("Recipe registered: $recipeName")
        } else {
            println("Recipe Failed: $recipeName")
        }
    }
}