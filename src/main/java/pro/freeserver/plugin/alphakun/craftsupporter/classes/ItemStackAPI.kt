package pro.freeserver.plugin.alphakun.craftsupporter.classes

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import java.lang.NullPointerException

class ItemStackAPI(material: Material, amount: Int? = null, itemName: String? = "", lore: List<String>? = null, enchant: Map<Enchantment, Int>? = null, customModelData: Int? = null) {
    var material: Material?
    var amount: Int
    var itemName: String
    var lore: List<String>?
    var enchant: Map<Enchantment, Int>
    var customModelData: Int?
    // customModelDataは何も指定されない場合nullになるので注意

    init {
        this.material = material
        this.amount = amount?:1
        this.itemName = itemName?:""
        this.lore = lore
        this.enchant = enchant?: mapOf()
        if (customModelData == 0) {
            this.customModelData = null
        } else {
            this.customModelData = customModelData
        }
    }

    fun getItemStack(): ItemStack {
        val itemStack = ItemStack(material!!,amount)
        val itemMeta = itemStack.itemMeta
        itemMeta.setDisplayName(itemName)
        itemMeta.setLore(lore)
        itemMeta.setCustomModelData(customModelData)
        for (e in enchant) {
            itemMeta.addEnchant(e.key,e.value,true)
        }
        itemStack.setItemMeta(itemMeta)
        return itemStack
    }
}