package io.posidon.potassium.world.gen

abstract class World {
    abstract val generator: WorldGenerator
    abstract val name: String
}