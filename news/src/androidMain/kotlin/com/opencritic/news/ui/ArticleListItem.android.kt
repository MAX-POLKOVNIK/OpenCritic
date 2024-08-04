package com.opencritic.news.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.SubcomposeAsyncImage
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.smallPadding
import com.opencritic.resources.text.text

@Composable
fun ArticleListItem(
    item: ArticleListItem,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        Card {
            Box(
                contentAlignment = Alignment.BottomStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            ) {
                SubcomposeAsyncImage(
                    model = item.bannerImageUrl,
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
        }

        Text(
            text = item.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = defaultPadding)
        )

        Text(
            text = item.summary,
            modifier = Modifier
                .padding(vertical = defaultPadding)
        )

        if (item.isOutletVisible) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = smallPadding)
            ) {
                Text(text = item.outletTitleText.text())
                TextButton(
                    onClick = item.onOutletClick,
                ) {
                    Text(text = item.outletText)
                }
            }
        }

        Text(text = item.writtenBy.text())
        Text(
            text = item.publishedDateText.text(),
            modifier = Modifier
                .padding(top = smallPadding)
        )

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextButton(onClick = item.onReadMoreClick) {
                Text(text = item.readMoreText.text())
            }
        }
    }
}

@Preview
@Composable
fun ArticleListItem_Preview() {
    ArticleListItem(item = ArticleListItem_PreviewData())
}