package com.opencritic.news.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.SubcomposeAsyncImage
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.smallPadding
import com.opencritic.resources.text.text

@Composable
fun ArticleContent(
    content: ArticleContent,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            onClick = {},
            modifier = Modifier
                .padding(defaultPadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.BottomStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                ) {
                    SubcomposeAsyncImage(
                        model = content.bannerImageUrl,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        loading = {
                            Box(
                                modifier = Modifier
                                    .background(Color.Gray)
                                    .fillMaxSize()
                            )
                        },
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                Text(
                    text = content.title,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(defaultPadding)
                )

                if (content.isOutletVisible) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = content.outletTitleText.text())
                        Text(
                            text = content.outletText,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .padding(start = smallPadding)
                                .clickable {
                                    content.onOutletClick()
                                }
                        )
                    }
                }

                Text(text = content.writtenBy.text())
                Text(text = content.publishedDateText.text())

                if (content.isGameDiscussedVisible) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(bottom = smallPadding)
                    ) {
                        Text(text = content.gamesTitleDiscussedText.text())
                        Text(
                            text = content.gamesDiscussedText,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .padding(start = smallPadding)
                                .clickable {
                                    content.onGameClick()
                                }
                        )
                    }
                }
            }
        }

        ArticleWevView(
            html = content.htmlToRender,
            modifier = Modifier
                .padding(defaultPadding)
        )

        if (content.isSeeFullArticleVisible) {
            TextButton(
                onClick = content.onSeeFullArticleClick,
                modifier = Modifier
                    .padding(horizontal = defaultPadding)
            ) {
                Text(text = content.seeFullArticleText.text())
            }
        }
    }
}

@Preview
@Composable
fun ArticleContent_Preview() {
    ArticleContent(
        content = ArticleContent_PreviewData()
    )
}