//
//  LoadingItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 09/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LoadingItemView: View {
    let item: LoadingItem
    
    var body: some View {
        ProgressView()
    }
}

#Preview {
    LoadingItemView(
        item: LoadingItem.shared
    )
}
