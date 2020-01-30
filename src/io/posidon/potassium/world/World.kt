package io.posidon.potassium.world

import io.posidon.potassium.world.gen.WorldGenerator

abstract class World {
    abstract val generator: WorldGenerator
    abstract val name: String
}