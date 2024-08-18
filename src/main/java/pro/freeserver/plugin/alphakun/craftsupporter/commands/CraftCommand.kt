package pro.freeserver.plugin.alphakun.craftsupporter.commands

import io.papermc.paper.command.brigadier.BasicCommand

interface CraftCommand: BasicCommand {
    val name: String
    val description: String
}