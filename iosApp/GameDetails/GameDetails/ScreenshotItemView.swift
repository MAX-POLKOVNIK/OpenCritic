//
//  ScreenshotItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 02/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Views
internal import NukeUI

struct ScreenshotItemView: View {
    let item: ScreenshotItem
    
    var body: some View {
        LazyImage(url: URL(string: item.thumbnailUrl)) { state in
            if let image = state.image {
                image.resizable()
            } else {
                Color.gray
            }
        }
        .aspectRatio(1.77, contentMode: .fit)
        .card()
    }
}

#Preview {
    ScreenshotItemView(
        item: ScreenshotItem(thumbnailUrl: "https://i.stack.imgur.com/A5oSb.png") { _ in }
    )
}
