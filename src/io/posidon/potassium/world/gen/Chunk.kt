package io.posidon.potassium.world.gen

class Chunk {

    var chunkX = 0f
    var chunkY = 0f
    var chunkZ = 0f
    private val blocks = arrayOfNulls<Block>(CUBE_SIZE)

    companion object {
        const val SIZE = 16
        const val CUBE_SIZE = SIZE * SIZE * SIZE
    }
}