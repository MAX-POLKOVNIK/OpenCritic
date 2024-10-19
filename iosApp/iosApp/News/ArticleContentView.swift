//
//  ArticleContentView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ArticleContentView: View {
    let content: ArticleContent
    
    @State private var webViewHeight: CGFloat = .zero
    
    var body: some View {
        ScrollView {
            VStack(alignment: .center) {
                CachedAsyncImage(
                    url: URL(string: content.bannerImageUrl),
                    urlCache: .imageCache
                ) { image in
                    image.resizable()
                        .scaledToFill()
                        .aspectRatio(16 / 9, contentMode: .fit)
                        .frame(minWidth: 0, maxWidth: .infinity)
                        .clipped()
                } placeholder: {
                    Rectangle()
                        .foregroundColor(.gray)
                        .background(.gray)
                        .aspectRatio(16 / 9, contentMode: .fit)
                }
                
                Text(content.title)
                    .font(.title)
                    .padding(.bottom)
                    .padding(.horizontal)
                    .multilineTextAlignment(.center)
                
                if content.isOutletVisible {
                    HStack {
                        Text(content.outletTitleText)
                        Button(content.outletText) {
                            content.onOutletClick()
                        }
                    }
                }
                
                Text(content.writtenBy)
                Text(content.publishedDateText)
                
                if content.isGameDiscussedVisible {
                    HStack {
                        Text(content.gamesTitleDiscussedText)
                        Button(content.gamesDiscussedText) {
                            content.onGameClick()
                        }
                    }
                }
            }
            .padding(.bottom)
            .card()
            .padding()
            
            Webview(
                html: content.htmlToRender,
                dynamicHeight: $webViewHeight
            )
            .padding(.horizontal)
            .frame(height: webViewHeight)
            
            if content.isSeeFullArticleVisible {
                Button(content.seeFullArticleText) {
                    content.onSeeFullArticleClick()
                }
            }
        }
        .toolbar {
            if content.isActionVisible {
                Button(
                    action: content.onAction,
                    label: { Image(iconRes: content.actionIconResource) }
                )
            }
        }
    }
}

#Preview {
    ArticleContentView(
        content: ArticleContentKt.ArticleContent_PreviewData()
    )
}
