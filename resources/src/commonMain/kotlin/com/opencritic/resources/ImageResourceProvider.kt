package com.opencritic.resources

interface ImageResourceProvider {
    val weakHead: ImageResource
    val fairHead: ImageResource
    val strongHead: ImageResource
    val mightyHead: ImageResource

    val tabMain: ImageResource
    val tabSearch: ImageResource
    val tabBrowse: ImageResource
    val tabCalendar: ImageResource
    val tabYourList: ImageResource
}