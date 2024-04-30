package com.opencritic.games

enum class Tier {
    Weak,
    Fair,
    Strong,
    Mighty;
}

fun Tier(str: String): Tier =
    when (str) {
        "Mighty" -> Tier.Mighty
        "Fair" -> Tier.Fair
        "Strong" -> Tier.Strong
        "Weak" -> Tier.Weak
        else -> error("Unknown Tier: $str")
    }