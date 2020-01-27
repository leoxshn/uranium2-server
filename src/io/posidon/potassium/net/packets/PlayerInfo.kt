package io.posidon.potassium.net.packets

abstract class PlayerInfo : Packet("playerInfo") {

    var x = 0f
    var y = 0f
    var z = 0f

    override fun packToString() = "coords:$x, $y, $z"
}