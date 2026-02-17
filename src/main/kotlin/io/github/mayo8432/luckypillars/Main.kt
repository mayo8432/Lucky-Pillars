package io.github.mayo8432.luckypillars

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIPaperConfig
import io.github.mayo8432.luckypillars.commands.EndGameCommand
import io.github.mayo8432.luckypillars.commands.StartGameCommand
import io.github.mayo8432.luckypillars.commands.Test
import io.github.mayo8432.luckypillars.handlers.ConnectionHandler
import io.github.mayo8432.luckypillars.handlers.DeathHandler
import io.github.mayo8432.luckypillars.handlers.MovementHandler
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    companion object {
        lateinit var instance: Main
    }

    override fun onLoad() {
        instance = this

        CommandAPI.onLoad(
            CommandAPIPaperConfig(this)
                .silentLogs(true)
                .verboseOutput(false)
        )
    }

    override fun onEnable() {
        CommandAPI.onEnable()

        logger.info("[+] ${this.name} has been enabled!")

        ConnectionHandler()
        DeathHandler()
        MovementHandler()
        StartGameCommand
        EndGameCommand
        Test
    }

    override fun onDisable() {
        CommandAPI.onDisable()

        logger.info("[-] ${this.name} has been disabled!")
    }
}