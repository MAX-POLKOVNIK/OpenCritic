package com.opencritic.about.ui

import com.opencritic.about.domain.GetAppVersionInteractor
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.navigation.UrlRoute
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource

class AboutViewModel(
    private val getAppVersionInteractor: GetAppVersionInteractor,
) : BaseContentViewModel<AboutContent>() {
    override fun initialState(): CommonViewModelState<AboutContent> =
        CommonViewModelState.content(
            title = StringRes.str_about_title.asTextSource(),
            content = AboutContent(
                appVersion = getAppVersionInteractor()
            ) {
                onClick(it)
            }
        )

    private fun onClick(url: String) {
        requireRouter()
            .navigateTo(
                UrlRoute(url)
            )
    }
}