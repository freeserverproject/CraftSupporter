package pro.freeserver.plugin.alphakun.craftsupporter

import org.bukkit.plugin.java.JavaPlugin

class CraftSupporter : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        plugin = this
        this.saveDefaultConfig()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    companion object {
        lateinit var plugin: CraftSupporter
    }
}