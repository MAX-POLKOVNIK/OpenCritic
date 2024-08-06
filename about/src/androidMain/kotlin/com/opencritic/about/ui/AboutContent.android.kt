package com.opencritic.about.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.largePadding
import com.opencritic.resources.text.text

@Composable
fun AboutContent(
    content: AboutContent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),

    ) {
        Text(
            text = content.disclosureTitleText.text(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = defaultPadding)
                .padding(start = defaultPadding)
        )

        Text(
            text = content.disclosureText.text(),
            modifier = Modifier
                .padding(horizontal = defaultPadding)
        )

        Text(
            text = content.linksTitleText.text(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = largePadding)
                .padding(bottom = defaultPadding)
                .padding(start = defaultPadding)
        )

        content.links.forEachIndexed { index, link ->
            HorizontalDivider(
                modifier = Modifier
                    .padding(horizontal = defaultPadding)
            )

            Text(
                text = link.title.text(),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { link.onClick() }
                    .padding(defaultPadding)
            )
            if (content.links.lastIndex == index) {
                HorizontalDivider(
                    modifier = Modifier
                        .padding(horizontal = defaultPadding)
                )
            }
        }

        Text(
            text = content.appVersionText.text(),
            modifier = Modifier
                .padding(defaultPadding)
                .padding(vertical = defaultPadding)
        )
    }
}

@Preview
@Composable
fun AboutContent_Preview() {
    AboutContent(
        content = AboutContent_PreviewData()
    )
}