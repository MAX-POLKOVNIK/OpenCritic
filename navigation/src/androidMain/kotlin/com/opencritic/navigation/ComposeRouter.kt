package com.opencritic.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

class ComposeRouter(
    private val navController: NavController,
    private val context: Context,
) : Router {
    override fun navigateTo(route: Route) {
        if (route is LinkShareRoute) {
            val share = Intent.createChooser(
                /* target = */ Intent().apply {
                    action = Intent.ACTION_SEND
                    setType("text/plain")
                    putExtra(Intent.EXTRA_TEXT, route.url)
                },
                /* title = */ "Share URL"
            )
            context.startActivity(share)

            return
        }

        if (route is UrlRoute) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(route.url))
            context.startActivity(intent)

            return
        }

        navController.navigate(route.id)
    }

    override fun navigateBack() {
        navController.popBackStack()
    }

    override fun popToRoot() {
        TODO("Not yet implemented")
    }
}

@Composable
fun NavController.router(): ComposeRouter =
    ComposeRouter(this, LocalContext.current)