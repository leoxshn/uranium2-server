package io.posidon.potassium.net.packets

import io.posidon.potassium.Console

object ReceivedPacketHandler {
    operator fun invoke(string: String) = when {
        string.startsWith("mov&") -> {
            val data = string.substring(4).split('&')
            for (thing in data) {
                if (thing.startsWith("coords:")) {

                }
            }
        }
        string.startsWith("join&") -> Console.println("Yo WTF!")
        else -> {
            Console.println("what is this? -> " + string)
        }
    }
}