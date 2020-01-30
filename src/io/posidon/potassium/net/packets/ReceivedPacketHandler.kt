package io.posidon.potassium.net.packets

import io.posidon.potassium.Console
import io.posidon.potassium.net.Player

object ReceivedPacketHandler {
    operator fun invoke(player: Player, string: String) = when {
        string.startsWith("mov&") -> {
            val data = string.substring(4).split('&')
            for (thing in data) {
                if (thing.startsWith("coords:")) {

                }
            }
        }
        string.startsWith("join&") -> Console.println("Yo WTF!")
        else -> {
            Console.println(player.name + " sent an unknown packet: $string")
        }
    }
}