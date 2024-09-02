//
//  ArticleListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 09/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ArticleListItemView: View {
    @Environment(\.horizontalSizeClass)
    private var horizontalSizeClass: UserInterfaceSizeClass?
    
    let item: ArticleListItem
    
    var body: some View {
        if horizontalSizeClass == .compact {
            ArticleVerticalListItemView(item: item)
        } else {
            ArticleHorizontalListItemView(item: item)
        }
    }
}
