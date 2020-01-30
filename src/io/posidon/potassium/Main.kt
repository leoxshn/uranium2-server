package io.posidon.potassium

import io.posidon.potassium.net.Players
import io.posidon.potassium.net.Server
import java.io.IOException

var running = true
inline fun loop(methods: () -> Unit) { while (running) methods() }

fun main() {
	Thread(Console()).start()
	Thread(Server()).start()
}

fun stop() {
	running = false
	Console.println("Stopping server...")
	for (player in Players) player.kick()
	try { Server.socket.close() }
	catch (e: IOException) { e.print() }
}

fun Throwable.print() = Console.beforeCmdLine {
	print(Console.colors.RED)
	printStackTrace()
	print(Console.colors.RESET)
}