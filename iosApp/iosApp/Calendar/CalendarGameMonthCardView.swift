//
//  CalendarGameMonthCardView.swift
//  iosApp
//
//  Created by Max Polkovnik on 06/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CalendarGameMonthCardView: View {
    let item: CalendarGameMonthCard
    
    var body: some View {
        VStack(spacing: 2) {
            Text(item.nameText)
                .font(.title)
                .padding(.vertical)
            
            ForEach(item.rows, id: \.self) { row in
                CalendarGameRowItemView(item: row)
            }
        }
        .card()
    }
}

#Preview {
    CalendarGameMonthCardView(
        item: CalendarGameMonthCardKt.CalendarGameMonthCard_PreviewData(nameText: "January")
    )
}
