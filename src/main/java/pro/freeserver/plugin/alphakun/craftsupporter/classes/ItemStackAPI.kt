package pro.freeserver.plugin.alphakun.craftsupporter.classes

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

class ItemStackAPI(material: Material, amount: Int, itemName: String, lore: List<String>, enchant: List<Enchantment>, customModelData: Int) {
    var material: Material = Material.AIR
    var amount: Int = 0
    var itemName: String = ""
    var lore: List<String> = listOf()
    var enchant: List<Enchantment> = listOf()
    var customModelData: Int = 0

    init {
        this.material = material
        this.amount = amount
        this.itemName = itemName
        this.lore = lore
        this.enchant = enchant
        this.customModelData = customModelData
    }

    fun getItemStack(): ItemStack {
        val itemStack = ItemStack(material, amount)
        val itemMeta = itemStack.itemMeta
        itemMeta.setDisplayName(itemName)
        itemMeta.setLore(lore)
        itemMeta.setCustomModelData(customModelData)
        itemStack.itemMeta = itemMeta
        return itemStack
    }
}