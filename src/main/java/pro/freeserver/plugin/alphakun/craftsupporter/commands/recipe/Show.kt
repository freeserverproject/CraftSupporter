package pro.freeserver.plugin.alphakun.craftsupporter.commands.recipe

import io.papermc.paper.command.brigadier.CommandSourceStack
import org.bukkit.command.CommandSender
import pro.freeserver.plugin.alphakun.craftsupporter.CraftSupporter
import pro.freeserver.plugin.alphakun.craftsupporter.CraftSupporter.Companion.plugin
import pro.freeserver.plugin.alphakun.craftsupporter.commands.SubCommand
import pro.freeserver.plugin.alphakun.craftsupporter.utils.ComponentUtil.toComponent

class Show() : SubCommand {
    override val name = "show"
    override val description = "レシピを一覧で表示する"

    override fun execute(commandSource: CommandSourceStack, args: Array<out String>?): Boolean {
        if (CraftSupporter.recipes.isEmpty()) {
            commandSource.sender.sendMessage(toComponent("§ccraft§f: §cレシピが登録されていません"))
            return true
        }

        sendRecipes(commandSource.sender)
        return true
    }

    private fun sendRecipes(sender: CommandSender) {
        sender.sendMessage(toComponent("====== ${plugin.name} / §a登録済みレシピ§r ======")) // header
        CraftSupporter.recipes.forEach {
            sender.sendMessage(toComponent("§aレシピ§r: §a${it.key}"))
        }
        sender.sendMessage(toComponent("=====================================")) // footer
    }
}