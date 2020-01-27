package io.posidon.potassium.net

import io.posidon.potassium.Console
import io.posidon.potassium.net.packets.Packet
import io.posidon.potassium.net.packets.ReceivedPacketHandler
import io.posidon.potassium.print
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket

class Player(private val socket: Socket) : Thread(socket.inetAddress.hostAddress) {
    private var output: OutputStream = socket.getOutputStream()
    private var input: InputStream = socket.getInputStream()
    private var running = true
    var playerName: String? = null
    var id = 0
    var x = 0f
    var y = 0f
    var z = 0f
    var moveSpeed = 0.5f
    var jumpHeight = 0.5f

    fun send(packet: Packet) {
        try {
            output.write(packet.toString().toByteArray())
            output.flush()
        } catch (e: Exception) { e.print() }
    }

    override fun run() {
        while (running) {
            var string = ""
            try { string = input.bufferedReader(Charsets.US_ASCII).readLine() }
            catch (ignore: Exception) {}
            if (string == "") disconnect()
            else ReceivedPacketHandler(string)
        }
    }

    fun disconnect() {
        running = false
        try {
            output.close()
            input.close()
            socket.close()
        } catch (ignore: Exception) {}
        PlayerHandler.remove(id)
        Console.beforeCmdLine {
            Console.printInfo(playerName!!, " left the server")
        }
    }

    init {
        try {
            var tmp = ""
            do try {
                tmp = input.bufferedReader(Charsets.US_ASCII).readLine()
            } catch (e: Exception) { e.print() }
            while (!tmp.startsWith("join&"))
            val packet = tmp.split("&")
            id = packet[1].hashCode()
            playerName = packet[2]
            PlayerHandler[id] = this
            Console.beforeCmdLine {
                Console.printInfo(playerName!!, " joined the server")
            }
        } catch (e: IOException) { e.print() }
    }
}