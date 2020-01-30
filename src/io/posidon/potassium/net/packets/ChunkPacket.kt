package io.posidon.potassium.net.packets

import io.posidon.potassium.world.gen.Chunk

abstract class ChunkPacket(chunk: Chunk) : Packet("position") {

    var chunkX = 0f
    var chunkY = 0f
    var chunkZ = 0f

    override fun packToString() = "coords:$chunkX, $chunkY, $chunkZ"
}