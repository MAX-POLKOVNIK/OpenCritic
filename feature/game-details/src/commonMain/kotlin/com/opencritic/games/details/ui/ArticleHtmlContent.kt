package com.opencritic.games.details.ui

fun articleHtmlContent(
    html: String,
    color: String,
): String {
    val htmlStart = "<!DOCTYPE html><HTML><HEAD><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\"></HEAD><BODY><font color=\"$color\" size=\"4\" face=\"sans-serif\">"
    val htmlEnd = "</font></BODY></HTML>"
    return "$htmlStart$html$htmlEnd"
}