package io.posidon.potassium.net

import io.posidon.potassium.Console
import io.posidon.potassium.net.packets.Packet
import io.posidon.potassium.print
import io.posidon.potassium.running
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.SocketException
import java.net.URL

class Server : Runnable {

    override fun run() = try {
        Console.beforeCmdLine {
            Console.println("Starting server...")
        }
        while (running) {
            try { Player(socket.accept()).start() }
            catch (e: SocketException) {}
            catch (e: Exception) { e.print() }
        }
    } catch (e: IOException) { e.print() }

    companion object {

        private const val port = 2512

        val socket = ServerSocket(port)

        fun sendToAllPlayers(packet: Packet) {
            for (player in PlayerHandler)
                player.send(packet)
        }

        val extIP: String?
            get() {
                var out: String? = null
                try {
                    val ipUrl = URL("http://checkip.amazonaws.com")
                    var input: BufferedReader? = null
                    try {
                        input = BufferedReader(InputStreamReader(ipUrl.openStream()))
                        out = input.readLine()
                    } catch (e: Exception) {}
                    input?.close()
                } catch (e: Exception) {}
                return out
            }
    }
}