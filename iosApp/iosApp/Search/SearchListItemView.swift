//
//  SearchListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 02/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SearchListItemView: View {
    let item: SearchListItem
    
    var body: some View {
        HStack {
            Text(item.kindText)
                .bold()
                .foregroundStyle(.white)
                .padding(8)
                .clipShape(.rect(cornerRadius: 8))
                .background(
                    RoundedRectangle(cornerRadius: 8)
                        .fill(item.kindColor.toUIColor().toColor())
                        .shadow(color: .gray, radius: 2, x: 0, y: 2)
                )
            
            Text(item.nameText)
            
            Spacer()
        }
        .contentShape(Rectangle())
        .onTapGesture {
            item.click()
        }
        
    }
}

#Preview {
    SearchListItemView(
        item: SearchListItem(
            id: 1,
            nameText: "Game name",
            kindText: "Game".asTextSource(),
            kindColor: Colors.shared.Cyan,
            onClick: { _ in }
        )
    )
}
