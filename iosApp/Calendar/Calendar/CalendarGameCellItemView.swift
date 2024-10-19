//
//  CalendarGameCellItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 06/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CalendarGameCellItemView: View {
    let item: CalendarGameCellItem
    
    var body: some View {
        let backgroundColor = item.isBackgroundVisible ? Color.gray : Color.clear
        
        ZStack {
            if let poster = item.currentPoster {
                CalendarGamePosterCellItemView(item: poster)
            }
            
            VStack {
                HStack {
                    Spacer()
                    Text(item.dayText)
                }
                
                Spacer()
                
                if item.isDotsVisible {
                    HStack(spacing: 2) {
                        ForEach(0..<item.dotsCount, id: \.self) { i in
                            let color = item.currentPosterIndex == KotlinInt(value: i) ? Color.cyan : Color.gray
                            
                            Circle()
                                .fill(color)
                                .frame(width: 4, height: 4)
                        }
                    }
                    .padding(.bottom, 2)
                }
            }
        }
        .aspectRatio(10 / 16, contentMode: .fit)
        .background(backgroundColor)
    }
}

#Preview {
    CalendarGameCellItemView(
        item: CalendarGameCellItemKt.CalendarGameCellItem_PreviewData()
    )
}
