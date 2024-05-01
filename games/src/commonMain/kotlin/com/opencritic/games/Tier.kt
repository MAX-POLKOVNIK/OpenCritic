package com.opencritic.games

enum class Tier {
    Mighty,
    Strong,
    Fair,
    Weak,
}

fun Tier(str: String): Tier =
    when (str) {
        "Mighty" -> Tier.Mighty
        "Fair" -> Tier.Fair
        "Strong" -> Tier.Strong
        "Weak" -> Tier.Weak
        else -> error("Unknown Tier: $str")
    }