package io.posidon.potassium.world

class Block(val type: Type) {

    enum class Type {
        GRASS,
        STONE,
        MOONSTONE,
        MOONSTONE_BRICKS
    }
}