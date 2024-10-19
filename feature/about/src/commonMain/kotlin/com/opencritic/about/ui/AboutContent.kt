package com.opencritic.about.ui

import com.opencritic.mvvm.ScreenContent
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import dev.icerock.moko.resources.StringResource

data class AboutContent(
    val disclosureTitleText: TextSource,
    val disclosureText: TextSource,
    val linksTitleText: TextSource,
    val links: List<LinkListItem>,
    val appVersionText: TextSource,
    val onLinkClick: (String) -> Unit,
) : ScreenContent

fun AboutContent(
    appVersion: String,
    onLinkClick: (String) -> Unit,
): AboutContent =
    AboutContent(
        disclosureTitleText = StringRes.str_about_disclosure_title.asTextSource(),
        disclosureText = StringRes.str_about_disclosure.asTextSource(),
        linksTitleText = StringRes.str_about_links_title.asTextSource(),
        links = listOf(
            "https://github.com/MAX-POLKOVNIK/OpenCritic" named StringRes.str_about_link_title_author onClick onLinkClick,
            "https://opencritic.com/faq" named StringRes.str_about_link_title_faq onClick onLinkClick,
            "https://opencritic.com/submit-review" named StringRes.str_about_link_title_submit_review onClick onLinkClick,
            "https://opencritic.com/partners" named StringRes.str_about_link_title_partners onClick onLinkClick,
            "https://opencritic.com/contact" named StringRes.str_about_link_title_contacts onClick onLinkClick,
            "https://opencritic.com/supporters" named StringRes.str_about_link_title_supporters onClick onLinkClick,
            "https://opencritic.com/privacy" named StringRes.str_about_link_title_privacy onClick onLinkClick,
            "https://opencritic.com/terms" named StringRes.str_about_link_title_terms onClick onLinkClick,
            "https://c.opencritic.com/press-kit.zip" named StringRes.str_about_link_title_press onClick onLinkClick,
            "https://www.patreon.com/OpenCritic" named StringRes.str_about_link_title_donate onClick onLinkClick,
        ),
        appVersionText = StringRes.str_about_version.asTextSource(appVersion),
        onLinkClick = onLinkClick,
    )

@Suppress("FunctionName")
fun AboutContent_PreviewData(): AboutContent =
    AboutContent(
        appVersion = "1.2",
        onLinkClick = {}
    )

private infix fun String.named(str: StringResource): LinkListItem =
    LinkListItem(
        title = str.asTextSource(),
        url = this,
        onClick = {}
    )

private infix fun LinkListItem.onClick(block: (String) -> Unit): LinkListItem =
    copy(onClick = block)