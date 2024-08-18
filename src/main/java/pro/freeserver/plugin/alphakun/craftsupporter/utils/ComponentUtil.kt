package pro.freeserver.plugin.alphakun.craftsupporter.utils

import net.kyori.adventure.text.Component

object ComponentUtil {
    fun toComponent(value: String): Component {
        return Component.text(value)
    }
}