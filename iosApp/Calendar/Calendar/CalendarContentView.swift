//
//  CalendarContentView.swift
//  iosApp
//
//  Created by Max Polkovnik on 06/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CalendarContentView: View {
    let content: CalendarContent
    
    var body: some View {
        List {
            Text(content.description_)
                .listRowSeparator(.hidden)
            
            ForEach(content.cards, id: \.id) { card in
                CalendarGameMonthCardView(item: card)
                    .listRowSeparator(.hidden)
            }
        }
        .listStyle(.plain)
    }
}

#Preview {
    CalendarContentView(
        content: CalendarContentKt.CalendarContent_PreviewData()
    )
}
