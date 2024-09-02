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
    override fun <InitArgs : Any> navigateTo(destination: Destination<InitArgs>) {
        val route = destination.route
        if (route is ShareLinkRoute) {
            val args = destination.args as ShareLinkRoute.InitArgs

            val share = Intent.createChooser(
                /* target = */ Intent().apply {
                    action = Intent.ACTION_SEND
                    setType("text/plain")
                    putExtra(Intent.EXTRA_TEXT, args.url)
                },
                /* title = */ "Share URL"
            )
            context.startActivity(share)

            return
        }

        if (route is UrlRoute) {
            val args = destination.args as UrlRoute.InitArgs
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(args.url))
            context.startActivity(intent)

            return
        }

        navController.navigate(route.navigationPath(destination.args))
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