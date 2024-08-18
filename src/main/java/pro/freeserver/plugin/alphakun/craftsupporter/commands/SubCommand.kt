package pro.freeserver.plugin.alphakun.craftsupporter.commands

import io.papermc.paper.command.brigadier.CommandSourceStack

interface SubCommand {
    val name: String
    val description: String

    fun execute(commandSource: CommandSourceStack, args: Array<out String>?): Boolean
}