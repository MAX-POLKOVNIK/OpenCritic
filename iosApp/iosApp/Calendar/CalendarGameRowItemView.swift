//
//  CalendarGameRowItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 06/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CalendarGameRowItemView: View {
    let item: CalendarGameRowItem
    
    var body: some View {
        HStack(spacing: 2) {
            ForEach(item.cells, id: \.self) { cell in
                CalendarGameCellItemView(item: cell)
            }
        }
    }
}

#Preview {
    CalendarGameRowItemView(
        item: CalendarGameRowItemKt.CalendarGameRowItem_PreviewData()
    )
}
