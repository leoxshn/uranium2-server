package io.posidon.potassium.tools

object KotlinFixes {
    inline infix fun Byte.eq(int: Int) = this == int.toByte()
    inline infix fun Any.eq(any: Any) = this == any
    inline infix fun Any.neq(any: Any) = this == any
}