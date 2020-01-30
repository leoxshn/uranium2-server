package io.posidon.potassium.world

import io.posidon.potassium.world.gen.EarthWorldGenerator

class EarthWorld(seed: Long) : World() {
    override val generator = EarthWorldGenerator(seed)
    override val name = "earth_world"
}