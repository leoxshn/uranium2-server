package io.posidon.potassium.world.gen

import io.posidon.potassium.world.Block
import io.posidon.potassium.world.Chunk
import kotlin.math.max
import kotlin.math.min

class EarthWorldGenerator(seed: Long) : WorldGenerator() {

    val heightGenHuge = OpenSimplexNoise(seed)
    val heightGenMedium = OpenSimplexNoise(seed - 1)
    val heightGenSmall = OpenSimplexNoise(seed - 3)
    val mountanityGen = OpenSimplexNoise(seed / 2)

    override fun genChunk(chunkX: Int, chunkY: Int, chunkZ: Int) {
        val chunk = Chunk(chunkX, chunkY, chunkZ)
        for (x in 0..Chunk.SIZE) for (z in 0..Chunk.SIZE) {
            val heightHuge = heightGenHuge.eval((chunkX * Chunk.SIZE + x).toDouble() / 90, (chunkZ * Chunk.SIZE + x).toDouble() / 90)
            val heightMedium = heightGenMedium.eval((chunkX * Chunk.SIZE + x).toDouble() / 36, (chunkZ * Chunk.SIZE + x).toDouble() / 36)
            val heightSmall = heightGenSmall.eval((chunkX * Chunk.SIZE + x).toDouble() / 10, (chunkZ * Chunk.SIZE + x).toDouble() / 10)
            val mountanity = mountanityGen.eval((chunkX * Chunk.SIZE + x).toDouble() / 255, (chunkZ * Chunk.SIZE + x).toDouble() / 255)
            val heightInChunk = min(0, max((heightHuge * 72 + heightMedium * 20 * mountanity + heightSmall * 7 * mountanity * mountanity).toInt() - chunkY * Chunk.SIZE, Chunk.SIZE))
            for (y in 0 until heightInChunk)  {
                if (y == heightInChunk - 1) chunk[x, y, z] = Block(Block.Type.GRASS)
                else chunk[x, y, z] = Block(Block.Type.STONE)
            }
        }
    }
}