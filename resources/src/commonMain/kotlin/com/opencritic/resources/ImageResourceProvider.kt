package com.opencritic.resources

interface ImageResourceProvider {
    val weakHead: ImageResource
    val fairHead: ImageResource
    val strongHead: ImageResource
    val mightyHead: ImageResource
}