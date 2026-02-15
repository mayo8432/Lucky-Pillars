package io.github.mayo8432.luckypillars.handlers

import io.github.mayo8432.luckypillars.Main
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.UUID

class ConnectionHandler: Listener {

    private val pillarCoordinatesList: List<Location> = listOf(
        Location(Bukkit.getWorld("world"),23.5 ,23.0, 83.5, 90f, 0f),
        Location(Bukkit.getWorld("world"),23.5 ,23.0, 76.5, 90f, 0f),
        Location(Bukkit.getWorld("world"),18.5 ,23.0, 67.5, 45f, 0f),
        Location(Bukkit.getWorld("world"),10.5 ,23.0, 63.5, 0f, 0f),
        Location(Bukkit.getWorld("world"),1.5 ,23.0, 63.5, 0f, 0f),
        Location(Bukkit.getWorld("world"),-6.5 ,23.0, 67.5, -45f, 0f),
        Location(Bukkit.getWorld("world"),-11.5 ,23.0, 76.5, -90f, 0f),
        Location(Bukkit.getWorld("world"),-11.5 ,23.0, 83.5, -90f, 0f),
        Location(Bukkit.getWorld("world"),-6.5 ,23.0, 92.5, -135f, 0f),
        Location(Bukkit.getWorld("world"),2.5 ,23.0, 97.5, -180f, 0f),
        Location(Bukkit.getWorld("world"),9.5 ,23.0, 97.5, -180f, 0f),
        Location(Bukkit.getWorld("world"),18.5 ,23.0, 92.5, 135f, 0f)
    )

    private val occupiedSlots = mutableMapOf<Int, UUID>()

    init {
        Bukkit.getPluginManager().registerEvents(this, Main.instance)
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {

        val player = event.player

        player.gameMode = GameMode.SURVIVAL
        player.saturation = 14f


        // Searching free Slot
        val freeSlot = (0 until pillarCoordinatesList.size).firstOrNull { it !in occupiedSlots.keys }

        if (freeSlot == null) {
            player.sendMessage("No free slot available")
            return
        }

        occupiedSlots[freeSlot] = player.uniqueId
        player.teleport(pillarCoordinatesList[freeSlot])

    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        val uuid = event.player.uniqueId
        val slot = occupiedSlots.entries.find { it.value == uuid }?.key
        if (slot != null) {
            occupiedSlots.remove(slot)
        }
    }
}