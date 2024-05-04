package com.opencritic.resources

interface ImageResourceProvider {
    val weakHead: ImageResource
    val fairHead: ImageResource
    val strongHead: ImageResource
    val mightyHead: ImageResource

    val weakMan: ImageResource
    val fairMan: ImageResource
    val strongMan: ImageResource
    val mightyMan: ImageResource

    val tabMain: ImageResource
    val tabSearch: ImageResource
    val tabBrowse: ImageResource
    val tabCalendar: ImageResource
    val tabYourList: ImageResource

    val gameActionWant: ImageResource
    val gameActionPlayed: ImageResource
    val gameActionFavorite: ImageResource

    val hashTag: ImageResource
    val chartPie: ImageResource
    val bullseye: ImageResource
    val thumbUp: ImageResource
}