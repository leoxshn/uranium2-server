package posidon.potassium.net

import posidon.potassium.Console
import kotlin.math.cos
import kotlin.math.sin

object ReceivedPacketHandler {
    operator fun invoke(player: Player, string: String) { when {
        string.startsWith("mov&") -> {
            val data = string.substring(4).split('&')
            player.triggerTickEvent {
                val direction = data[0].toDouble()
                val run = data[1].toInt() == 1
                val keys = arrayOf(
                    data[2].toInt() == 1,
                    data[3].toInt() == 1,
                    data[4].toInt() == 1,
                    data[5].toInt() == 1)
                val fly = data[6].toInt().let {
                    when { it > 0 -> 1; it < 0 -> -1; else -> 0 }
                }
                var movX = sin(Math.toRadians(direction)).toFloat() * player.moveSpeed
                var movZ = cos(Math.toRadians(direction)).toFloat() * player.moveSpeed
                if (run) { movX *= 1.8f; movZ *= 1.8f }
                if (keys[0]) { player.position.x -= movX; player.position.z -= movZ }
                if (keys[1]) { player.position.x += movX; player.position.z += movZ }
                if (keys[2]) { player.position.x -= movZ; player.position.z += movX }
                if (keys[3]) { player.position.x += movZ; player.position.z -= movX }
                player.position.y += fly * player.jumpHeight
                Console.beforeCmdLine {
                    Console.printInfo(player.playerName!!, if (run) " (run) -> " else " -> " + player.position.x + " / " + player.position.y + " / " + player.position.z)
                }
            }
        }
        else -> Console.printProblem(player.name, " sent an unknown packet: $string")
    }}
}