//
//  DashboardTitleListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DashboardTitleListItemView: View {
    let item: DashboardTitleListItem
    
    var body: some View {
        VStack(
            alignment: .leading,
            content: {
                Text(item.titleText)
                    .font(.title)
                if item.isDescriptionVisible {
                    Text(item.subtitleText)
                }
            }
        )
        .padding(.horizontal)
    }
}

struct DashboardTitleListItemView_Previews: PreviewProvider {
    static var previews: some View {
        DashboardTitleListItemView(
            item: DashboardTitleListItem(
                title: "Hello",
                subtitle: "Second string"
            )
        )
        .previewLayout(.fixed(width: 300, height: 600))
    }
}
