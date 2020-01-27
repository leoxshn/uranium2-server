package io.posidon.potassium

import io.posidon.potassium.net.PlayerHandler
import io.posidon.potassium.net.Server
import java.io.IOException

var running = true

fun main() {
	Thread(Console()).start()
	Thread(Server()).start()
}

fun stop() {
	running = false
	Console.println("Stopping server...")
	for (player in PlayerHandler) player.disconnect()
	try { Server.socket.close() }
	catch (e: IOException) { e.print() }
}

fun Exception.print() = Console.beforeCmdLine {
	print(Console.colors.RED)
	printStackTrace()
	print(Console.colors.RESET)
}