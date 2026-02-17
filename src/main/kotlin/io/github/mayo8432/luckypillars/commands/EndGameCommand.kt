package io.github.mayo8432.luckypillars.commands

import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import io.github.mayo8432.luckypillars.data.GameData
import org.bukkit.Bukkit

object EndGameCommand {

    val command = commandTree("endgame") {
        withUsage("/endgame")
        withAliases("eg")
        withPermission("luckypillars.command.endgame")

        playerExecutor { player, _ ->

            if (GameData.giveTaskID != null) {
                Bukkit.getScheduler().cancelTask(GameData.giveTaskID!!)
                GameData.giveTaskID = null
                GameData.gameIsRunning = false

                player.sendMessage("The game has been stopped")

            } else {
                player.sendMessage("There is no game running")
            }
        }
    }
}