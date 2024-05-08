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

    override val tabMain: ImageResource = R.drawable.ic_news_mode
    override val tabSearch: ImageResource = R.drawable.ic_search
    override val tabBrowse: ImageResource = R.drawable.ic_sports_esports
    override val tabCalendar: ImageResource = R.drawable.ic_calendar
    override val tabYourList: ImageResource = R.drawable.ic_star

    override val gameActionWant: ImageResource = R.drawable.ic_add
    override val gameActionPlayed: ImageResource = R.drawable.ic_done
    override val gameActionFavorite: ImageResource = R.drawable.ic_heart

    override val hashTag: ImageResource = R.drawable.ic_tag
    override val chartPie: ImageResource = R.drawable.ic_pie_chart
    override val bullseye: ImageResource = R.drawable.ic_adjust
    override val thumbUp: ImageResource = R.drawable.ic_thumb_up

    override val xbox: ImageResource = R.drawable.ic_xbox
    override val playstation: ImageResource = R.drawable.ic_playstation

    override val home: ImageResource = R.drawable.ic_home
}