package io.github.mayo8432.luckypillars.commands

import dev.jorel.commandapi.kotlindsl.commandTree

object Test {
    val command = commandTree("test") {
        withUsage("/test [feature] [optional argument]")    //Analog /t
        withAliases("t")
    }
}