package io.github.mayo8432.luckypillars.handlers

import io.github.mayo8432.luckypillars.Main
import io.github.mayo8432.luckypillars.data.GameData
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class MovementHandler: Listener {

    init {
        Bukkit.getPluginManager().registerEvents(this, Main.instance)
    }

    @EventHandler
    fun onMove(event: PlayerMoveEvent) {
        val player = event.player

        if (!GameData.gameIsRunning)  {
            event.isCancelled = true
            return
        }
    }
}