package io.github.mayo8432.luckypillars.data

import io.github.mayo8432.luckypillars.Main
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Player

object GameData {

    val playerCount: Int
        get() {
            return Bukkit.getOnlinePlayers().count { it.gameMode == GameMode.SURVIVAL }  // Amount of all online players still within the game
        }

    var gameIsRunning = false

    fun handleGameFinish() {
        Bukkit.getOnlinePlayers().first { it.gameMode == GameMode.SURVIVAL }.showTitle(
            Title.title(
                Component.text("#${GameData.playerCount}")
                    .decorate(TextDecoration.ITALIC)
                    .color(NamedTextColor.GOLD),
                Component.text("You won!")
                    .decoration(TextDecoration.ITALIC, false)
                    .color(NamedTextColor.YELLOW),
                15, 30, 15
            )
        )
    }

    fun toSpectator(player: Player) {
        player.gameMode = GameMode.SPECTATOR
        Bukkit.getScheduler().runTaskLater(Main.instance, Runnable {
            player.teleport(Location(Bukkit.getWorld("world"),6.5, 32.0, 79.5, 0f, 90f))
        }, 1L)
    }
}