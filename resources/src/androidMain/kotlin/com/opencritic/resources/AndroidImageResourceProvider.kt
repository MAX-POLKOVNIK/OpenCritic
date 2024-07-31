package com.opencritic.resources

class AndroidImageResourceProvider : ImageResourceProvider {
    override val weakHead: ImageResource = R.drawable.weak_head
    override val fairHead: ImageResource = R.drawable.fair_head
    override val strongHead: ImageResource = R.drawable.strong_head
    override val mightyHead: ImageResource = R.drawable.mighty_head

    override val weakMan: ImageResource = R.drawable.weak_man
    override val fairMan: ImageResource = R.drawable.fair_man
    override val strongMan: ImageResource = R.drawable.strong_man
    override val mightyMan: ImageResource = R.drawable.mighty_man

    override val googleLogoImage: ImageResource = R.drawable.google
    override val facebookLogoImage: ImageResource = R.drawable.facebook
    override val twitchLogoImage: ImageResource = R.drawable.twitch
    override val steamLogoImage: ImageResource = R.drawable.steam
}