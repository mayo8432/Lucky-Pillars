package io.github.mayo8432.luckypillars

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIPaperConfig
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
    }

    override fun onDisable() {
        CommandAPI.onDisable()

        logger.info("[-] ${this.name} has been disabled!")
    }
}