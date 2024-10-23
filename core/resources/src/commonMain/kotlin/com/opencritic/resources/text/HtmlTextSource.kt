package com.opencritic.resources.text

data class HtmlTextSource(
    val htmlString: String,
)

fun String.asHtmlTextSource(): HtmlTextSource =
    HtmlTextSource(this)