package pro.freeserver.plugin.alphakun.craftsupporter

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
    }

    companion object {
        lateinit var plugin: CraftSupporter
    }
}