package pro.freeserver.plugin.alphakun.craftsupporter.commands.recipe

import io.papermc.paper.command.brigadier.CommandSourceStack
import org.bukkit.command.CommandSender
import pro.freeserver.plugin.alphakun.craftsupporter.CraftSupporter.Companion.plugin
import pro.freeserver.plugin.alphakun.craftsupporter.commands.CraftCommand
import pro.freeserver.plugin.alphakun.craftsupporter.utils.ComponentUtil.toComponent

class Recipe: CraftCommand {
    override val name: String = "craft"
    override val description: String = "CraftSupporterのメインコマンド"

    private val subCommands = mutableListOf(Show()) // Recipeのサブコマンドリスト

    override fun execute(commandSource: CommandSourceStack, args: Array<out String>?) {

        if (args?.isEmpty() != false) {
            sendHelp(commandSource.sender)
            return
        }
        subCommands.forEach {
            if (args[0].equals(it.name, ignoreCase = true)) it.execute(commandSource, args)
        }
    }

    private fun sendHelp(sender: CommandSender) {
        sender.sendMessage(toComponent("====== ${plugin.name} / §aヘルプ§r ======")) // header
        sender.sendMessage(toComponent("$name: $description"))
        subCommands.forEach {
            sender.sendMessage(toComponent("${it.name}: ${it.description}"))
        }
        sender.sendMessage(toComponent("=====================================")) // footer
    }
}