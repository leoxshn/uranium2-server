package io.posidon.potassium.net

import java.util.*

object PlayerHandler {

    private var players = HashMap<Int, Player>()
    private var ids = HashMap<String?, Int>()

    fun getName(id: Int): String? = players[id]!!.playerName

    operator fun get(id: Int?): Player? = players[id]
    operator fun get(name: String?): Player? = players[ids[name]]

    operator fun set(id: Int, player: Player) {
        players[id] = player
        ids[player.playerName] = id
    }

    fun remove(id: Int) = players.remove(id)
    fun remove(name: String?) = players.remove(ids[name])

    val isEmpty: Boolean get() = players.isEmpty()
    val keys: Set<Int> get() = players.keys
    val values: Collection<Player> get() = players.values

    operator fun iterator(): Iterator<Player> {
        return players.values.iterator()
    }
}