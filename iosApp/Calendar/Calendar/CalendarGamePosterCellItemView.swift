//
//  CalendarGamePosterCellItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 06/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Views

struct CalendarGamePosterCellItemView: View {
    let item: CalendarGamePosterCellItem
    
    var body: some View {
        CachedAsyncImage(
            url: URL(string: item.posterImageUrl ?? ""),
            urlCache: .imageCache
        ) { image in
            image.resizable()
        } placeholder: {
            NoGamePosterView()
                .aspectRatio(10 / 16, contentMode: .fit)
        }
        .aspectRatio(10 / 16, contentMode: .fit)
        .onTapGesture { item.onClick() }
    }
}

#Preview {
    CalendarGamePosterCellItemView(
        item: CalendarGamePosterCellItemKt.CalendarGamePosterCellItem_PreviewData()
    )
}
