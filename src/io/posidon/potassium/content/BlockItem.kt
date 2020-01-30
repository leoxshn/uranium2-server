package io.posidon.potassium.content

import io.posidon.potassium.world.Block

class BlockItem(block: Block) : Item(block.type.name.toLowerCase()) {

}