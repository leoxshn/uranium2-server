package io.posidon.potassium.world.gen

abstract class WorldGenerator {
    protected abstract fun genChunk(chunkX: Int, chunkY: Int, chunkZ: Int)
}