package pro.freeserver.plugin.alphakun.craftsupporter

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin
import pro.freeserver.plugin.alphakun.craftsupporter.commands.CraftCommand
import pro.freeserver.plugin.alphakun.craftsupporter.init.LoadRecipe

class CraftSupporter : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        plugin = this
        this.saveDefaultConfig()
        LoadRecipe().loadRecipe()
        this.registerCommands()
    }

    override fun onDisable() {
        // Plugin shutdown logic
        removeRecipe()
    }

    companion object {
        lateinit var plugin: CraftSupporter
        var recipes: MutableList<NamespacedKey> = mutableListOf()
    }

    private fun registerCommands() {
        val commandList: MutableList<CraftCommand> = mutableListOf(pro.freeserver.plugin.alphakun.craftsupporter.commands.recipe.Recipe()) // コマンドリスト
        val manager = this.lifecycleManager
        manager.registerEventHandler(LifecycleEvents.COMMANDS) { event ->
            val commands = event.registrar()
            commandList.forEach {
                println("Command ${it.name} is registered")
                commands.register(it.name, it.description, it)
            }
        }
    }

    private fun removeRecipe() {
        for (r in recipes) {
            if (server.removeRecipe(r)) {
                println("Recipe: ${r.key} is now removed!!")
            }
        }
    }
}