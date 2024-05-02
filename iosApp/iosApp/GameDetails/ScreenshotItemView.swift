//
//  ScreenshotItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 02/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ScreenshotItemView: View {
    let item: ScreenshotItem
    
    var body: some View {
        CachedAsyncImage(
            url: URL(string: item.thumbnailUrl),
            urlCache: .imageCache
        ) { image in
            image.resizable()
        } placeholder: {
            Color.gray
        }
            .aspectRatio(1.77, contentMode: .fit)
            .clipShape(.rect(cornerRadius: 8))
            .background(
                RoundedRectangle(cornerRadius: 8)
                    .fill(Color.white)
                    .shadow(color: .gray, radius: 2, x: 0, y: 2)
            )
    }
}

#Preview {
    ScreenshotItemView(
        item: ScreenshotItem(thumbnailUrl: "https://i.stack.imgur.com/A5oSb.png") { _ in }
    )
}
