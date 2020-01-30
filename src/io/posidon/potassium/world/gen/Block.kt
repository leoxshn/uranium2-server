package io.posidon.potassium.world.gen

class Block(val type: Type) {

    enum class Type {
        GRASS,
        STONE,
        MOONSTONE,
        MOONSTONE_BRICKS
    }
}