package io.github.mayo8432.luckypillars.commands

import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import io.github.mayo8432.luckypillars.Main
import io.github.mayo8432.luckypillars.data.GameData
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object StartGameCommand {

    val command = commandTree("startgame") {
        withUsage("/startgame")
        withAliases("sg")
        withPermission("luckypillars.command.startgame")

        playerExecutor { player, args ->

            val blockedMaterials = Material.entries.filter { it in setOf(
                Material.BARRIER,
                Material.COMMAND_BLOCK,
                Material.CHAIN_COMMAND_BLOCK,
                Material.REPEATING_COMMAND_BLOCK,
                Material.COMMAND_BLOCK_MINECART,
                Material.STRUCTURE_BLOCK,
                Material.STRUCTURE_VOID,
                Material.JIGSAW,
                Material.LIGHT,
                Material.DEBUG_STICK,
                Material.BEDROCK,
                Material.END_PORTAL_FRAME,
                Material.END_PORTAL,
                Material.NETHER_PORTAL
            ) }

            var allowedMaterials = Material.entries.filter { it.isItem && it !in blockedMaterials }

            if (GameData.giveTaskID == null) {

                GameData.gameIsRunning = true

                val taskID = Bukkit.getScheduler().runTaskTimer(Main.instance, Runnable {
                    Bukkit.getOnlinePlayers().filter { it.gameMode === GameMode.SURVIVAL }.forEach{
                        val randomItem = ItemStack(allowedMaterials.random())
                        it.give(randomItem)
                    }
                },0L, 10L *20L).taskId

                GameData.giveTaskID = taskID

                player.sendMessage("The game has been started!")

            } else {

                player.sendMessage("The game is running already")
            }
        }
    }
}