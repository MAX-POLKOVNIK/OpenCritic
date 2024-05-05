package com.opencritic.resources

object Colors {
    val Orange = Color(255, 165, 0)
    val Purple = Color(128, 0, 128)
    val Cyan = Color(0, 255, 255)

    val White = Color(255, 255, 255)
    val Black = Color(0, 0, 0)

    val MightyTopCriticGradient = listOf(
        Color(231, 85, 39)
    )
    val MightyCriticsRecommendGradient = listOf(
        Color(224, 83, 38),
        Color(248, 218, 123),
        Color(224, 83, 38),
    )
    val StrongTopCriticGradient = listOf(
        Color(143, 28, 175),
    )
    val StrongCriticsRecommendGradient = listOf(
        Color(112, 109 , 222),
        Color(236, 119 , 194),
        Color(112, 109 , 222),
    )
    val FairTopCriticGradient = listOf(
        Color(98, 159, 203),
    )
    val FairCriticsRecommendGradient = listOf(
        Color(89, 138, 120),
        Color(139, 197, 251),
    )
    val WeakTopCriticGradient = listOf(
        Color(139, 175, 111),
    )
    val WeakCriticsRecommendGradient = listOf(
        Color(106, 128, 85),
    )

    val CircleIndicatorBackground = Color(46, 46, 46)

    val WantedGameColor = Color(143, 28, 175)
    val PlayedGameColor = Color(231, 85, 39)
    val FavoriteGameColor = Color(196, 47, 73)

    val Transparent = Color(0, 0, 0, 0f)
}