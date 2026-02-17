package io.github.mayo8432.luckypillars.handlers

import io.github.mayo8432.luckypillars.Main
import io.github.mayo8432.luckypillars.data.GameData
import io.papermc.paper.configuration.type.Duration
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRespawnEvent

class DeathHandler: Listener {

    init {
        Bukkit.getPluginManager().registerEvents(this, Main.instance)
    }

    @EventHandler
    fun onRespawn(event: PlayerRespawnEvent) {

        if (!GameData.gameIsRunning) {  // Checking whether the game is running
            return
        }

        val player = event.player

        GameData.toSpectator(player)

        if (GameData.playerCount == 1) {
            GameData.handleGameFinish()
        }

        player.showTitle(
            Title.title(
                Component.text("#${GameData.playerCount + 1}")
                    .decorate(TextDecoration.ITALIC)
                    .color(NamedTextColor.GOLD),
                Component.text("You died!")
                    .decoration(TextDecoration.ITALIC, false)
                    .color(NamedTextColor.YELLOW),
                15, 30, 15
            )
        )

        player.sendActionBar(Component.text("Use the scrollwheel to change speed").color(NamedTextColor.YELLOW))

    }
}