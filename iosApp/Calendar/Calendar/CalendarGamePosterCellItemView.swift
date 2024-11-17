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
internal import NukeUI

struct CalendarGamePosterCellItemView: View {
    let item: CalendarGamePosterCellItem
    
    var body: some View {
        LazyImage(url: URL(string: item.posterImageUrl ?? "")) { state in
            if let image = state.image {
                image.resizable().aspectRatio(10 / 16, contentMode: .fill)
            } else {
                NoGamePosterView()
                    .aspectRatio(10 / 16, contentMode: .fit)
            }
        }
        .aspectRatio(10 / 16, contentMode: .fit)
        .onTapGesture { item.click() }
    }
}

#Preview {
    CalendarGamePosterCellItemView(
        item: CalendarGamePosterCellItemKt.CalendarGamePosterCellItem_PreviewData()
    )
}
